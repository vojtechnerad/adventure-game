package cz.vse.java.nerv01.adventura.core;

import java.util.*;

/**
 * Třída CommandLoad pro vytvoření příkatu 'naloz'
 * 
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class CommandLoad implements ICommand {
    private static final String NAME = "naloz";
    private GamePlan plan;
    private Set<Item> itemsInBackpack;
    byte missingItems;
    boolean itemCheck;
    /**
     * Konstruktor pro třídu CommandLoad.
     * 
     * @param
     */
    public CommandLoad(GamePlan plan) {
        this.plan = plan;
    }
    
    
    /**
     * Metoda pro zpracování příkazu 'naloz'
     * 
     * Metoda vezme předměty z batohu a přesune je do auta.
     * Hráč se musí nacházet v prostoru 'garaz'.
     */
    @Override
    public String process(String... parameters) {
        // Kontrola, jestli za příkazem není parametr 
        if (parameters.length  > 0) {
            return "Za tento příkaz se parametr nepíše.";
        } else {
            // Kontrola že hráč stojí v garáži
            String currentArea = plan.getCurrentArea().getName();
            if (currentArea.equals("garaz")) {
                byte itemCount = 0;
                itemsInBackpack = plan.getBackpack().storedItems();
                for (Item item : itemsInBackpack) {
                    plan.getCar().addItemToInventory(item);
                    
                    itemCount++;
                }
                plan.getBackpack().deleteContentInInventory();
                
                
                
                if (plan.getCar().storedItems().size() == 10) {
                    plan.setVictorious();
                    return "VÝHRA!\n"
                            + "Podařilo se ti sehnat všech 10 věcí z nákupního seznamu.";
                }
                return "Nové předměty (" + itemCount + ") jsi naložil do auta.\n" + "celkově je zde " + plan.getCar().storedItems().size() + " předmětů.";
            } else {
                return "Příkaz 'naloz' lze použít pouze v garáži.";
            }
        }
    }
    
    /**
     * Getter pro název příkazu
     */
    
    @Override
    public String getName() {
        return NAME;
    }
}