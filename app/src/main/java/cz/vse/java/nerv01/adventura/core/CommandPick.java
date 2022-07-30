package cz.vse.java.nerv01.adventura.core;

import java.util.*;
/**
 * Instance třídy CommandPick pro vytvoření příkazu 'vloz'.
 * Nejdříve se zkontroluje, jestli je za příkazem zapsán
 * právě jeden parametr. Poté se zkontroluje, jestli se
 * vkládaný předmět (parametr) nachází v té samé místnosti,
 * v jaké se nachzí hráč. Následně se zkontroluje jestli se
 * vkládaný předmět nachází na nákupním seznamu, popřípadě
 * jestli vkládaný předmět není rouška, která je zde vyjímkou.
 * Poté se spočítá cena všech již vložených předmětů v košíku,
 * přičte se vkládaný předmět a zkontroluje se, jestli hráč
 * má u sebe dostatek peněz na zaplacení.
 * V posledním kroku se zkontroluje jestli má hráč v košíku
 * dostatečné místo pro předměty. Pokud tyto podmínky projdou,
 * předmět se vloží do nákuoního košíku a smaže se z dané lokace.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class CommandPick implements ICommand {
    private static final String NAME = "vloz";
    
    private GamePlan plan;
    /**
     * Konstruktor pro třídu CommandPick.
     * 
     * @param
     */
    public CommandPick(GamePlan plan) {
        this.plan = plan;
    }
    
    /**
     * Metoda která vytváří příkaz 'vloz'
     */
    @Override
    public String process(String... parameters) {
        // Kontrola počtu parametrů
        if (parameters.length == 0) {
            return "Nezadal jsi co se má sebrat, napiš to ve tvaru - [vloz] [parametr]";
        } else if (parameters.length > 1) {
            return "Za příkaz nelze zadat víc než jeden parametr, napíš to ve tvaru - [vloz] [parametr]";
        }

        // Zapsání parametru do proměnné
        String itemName = parameters[0];
        
        // Kontrola že se v aktuální místnosti nachází vkládaný předmět
        Area currentArea = plan.getCurrentArea();
        if (currentArea.containsItem(itemName)) {
            Item pickedItem = currentArea.getItem(itemName);
            
            // Kontrola jestli vkládaný předmět je na seznamu, popřípadě jestli se nejedná o výjimku (roušku)
            if (pickedItem.isPickable() || pickedItem.getName().equals("rouska")) {
                // Kontrola, jestli po přidání předmětu do košíku bude mít hráč dostatek peněz na věci v košíku plus nově přidanou věc
                int balance = plan.getWallet().currentBalance();
                int priceOfPickedItems = plan.getShoppingCart().getTotalPriceOfItems();
                priceOfPickedItems = priceOfPickedItems + pickedItem.getPrice();
                if (balance >= priceOfPickedItems) {
                    // Kontrola jestli má hráč dostatek místa jak v košíku, tak v batohu
                    float backpackCapacity = plan.getBackpack().getFreeCapacity();
                    float shoppingCartCapacity = plan.getShoppingCart().getFreeCapacity();
                    if (shoppingCartCapacity >= pickedItem.getWeight() && backpackCapacity >= (pickedItem.getWeight() + plan.getShoppingCart().getUsedCapacity())) {
                        plan.getShoppingCart().addItemToInventory(pickedItem);
                        currentArea.removeItem(itemName);
                        
                        return "Předmět " + itemName + " byl úspěšně přidán do nákupního košíku.\n"
                                + currentArea.getFullDescription();
                        
                    } else {
                        return "Na předmět " + itemName + " nemáš dostatek místa v jednom z inventářů.";
                        
                    }
                    
                } else {
                    return "Na předmět " + itemName + " nemáš dostatek peněz.";
                    
                }
                
            } else {
                return "Předmět " + itemName + " není na nákupním seznamu.";
                
            }
            
        } else {
            return "Předmět " + itemName + " se v současné místnosti (" + currentArea.getName() + ") nenachází.";
            
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
