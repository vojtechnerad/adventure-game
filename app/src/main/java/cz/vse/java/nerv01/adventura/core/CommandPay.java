package cz.vse.java.nerv01.adventura.core;

import java.util.*;

/**
 * Třída CommandPay vytváří příkaz "zaplat", který ve hře funguje způsobem,
 * že hráč zaplatí za předměty které si vložil do košíku,
 * odečtou se mu peníze z peněženky,
 * a předměty se mu automaticky přesunou do batohu.
 * Příkaz nesmí mít parametr.
 * Pokud se předměty 
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class CommandPay implements ICommand {
    private static final String NAME = "zaplat";
    private GamePlan plan;
    private Set<Item> itemsInCart;
    private int totalPrice;
    private byte amountOfItems = 0;
    /**
     * Konstruktor třídy CommandPay
     */
    public CommandPay(GamePlan plan) {
        this.plan = plan;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String process(String... parameters) {
        if (parameters.length > 0) {
            return "Za příkaz 'zaplat' se žádný parametr nepíše!";
        } else {
            itemsInCart = new HashSet<Item>();
            itemsInCart = plan.getShoppingCart().storedItems();
            if (itemsInCart.isEmpty()) {
                return "Tento příkaz teď použít nemůžeš.";
                
            } else {
                for (Item item : itemsInCart) {
                    totalPrice = totalPrice + item.getPrice();
                    plan.getBackpack().addItemToInventory(item);
                    amountOfItems++;
                }
                plan.getShoppingCart().deleteContentInInventory();
                String successPurchMsg = "Za nové předměty (" + amountOfItems + ") jsi zaplatil " + totalPrice + " Kč";
                plan.getWallet().deductBalance(totalPrice);
                
                // Nulování datových atributů pro další nákup
                amountOfItems = 0;
                totalPrice = 0;
                return successPurchMsg;
                
            }
        }
    }
    
    /**
     * Metoda vrací název příkazu.
     * Jedná se o slovo, které hráč používá pro vyvolání příkazu, například
     * 'napoveda', 'konec' a podobné.
     * 
     * @return název příkazu
     */
    public String getName() {
        return NAME;
    }
}
