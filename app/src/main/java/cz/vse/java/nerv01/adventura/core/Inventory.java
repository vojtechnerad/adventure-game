package cz.vse.java.nerv01.adventura.core;

import cz.vse.java.nerv01.adventura.main.Observer;
import cz.vse.java.nerv01.adventura.main.Subject;

import java.util.*;

/**
 * Třída Inventory slouží pro vytvoření instancí inventářů.
 * Inventářů je ve hře vícero, konkrétně čtyři.
 * Při hraní se předměty přesouvají mezi jednotlivými inventáři.
 * Prvním inventářem je 'obleceni', do kterého se dá vložit
 * pouze rouška, která se vkládá speciálním příkazem 'oblec'.
 * Druhým inventářem je 'kosik', do kterého se vkládají předměty
 * příkazem 'vloz'. V košíku předměty čekají na přesunutí.
 * Třetím inventářem je 'batoh', do kterého se přesouvají předměty
 * příkazem 'zaplat', který přesune předměty z košíku do batohu.
 * Čtvrtým inventářem je 'auto', do kterého se přesouvají předměty
 * příkazem 'naloz', který přesnune předměty z batohu do auta.
 * 
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class Inventory implements Subject {
    private String name;            // Název inventáře
    private float capacity;         // Kapacita inventáře
    private Set<Item> storedItems;  // Kolekce předmětů uložených v daném inventáři
    float capacityOfItems;          // Počet kapacity, kterou zabírají předměty v inventáři

    private Set<Observer> observerList;
    
    /**
     * Konstruktor pro vytvoření nového inventáře (instanci třídy Inventory)
     * 
     * @param name název inventáře
     * @param capacity kapacita inventáře
     */
    public Inventory(String name, float capacity) {
        this.name = name;
        this.capacity = capacity;
        storedItems = new HashSet<Item>();
        observerList = new HashSet<>();
        
    }
    
    /**
     * Metoda typu getter, která vrátí název inventáře.
     * 
     * @return vrátí název inventáře
     */
    public String getName() {
        return name;
        
    }    
    
    /**
     * Metoda typu getter, která vrací celkovou kapacitu inventáře.
     * 
     * @return celková kapacita inventáře
     */
    public float getCapacity() {
        return capacity;
        
    }
    
    /**
     * Metoda typu getter, která vrací použitou kapacitu inventáře.
     * 
     * @return použitá kapacita inventáře
     */
    public float getUsedCapacity() {
        float usedCapacity = 0;
        for (Item item : storedItems) {
            usedCapacity = usedCapacity + item.getWeight();
        }
        return usedCapacity;
        
    }
    
    /**
     * Metoda typu getter, která vrací volnou kapacitu inventáře.
     * 
     * @return volná kapacita inventáře
     */
    
    public float getFreeCapacity() {
        float freeCapacity = capacity;
        for (Item item : storedItems) {
            freeCapacity = freeCapacity - item.getWeight();
        }
        return freeCapacity;
    }
    
    /**
     * Metoda typu getter, která vrací součet cen všech předmětů vložených v inventáři.
     * 
     * @return suma cen všech předmětů v inventáři
     */
    public int getTotalPriceOfItems() {
        int totalPrice = 0;
        for (Item item : storedItems) {
            totalPrice = totalPrice + item.getPrice();
        }
        return totalPrice;
        
    }
    
    /**
     * Metoda typu getter, pro získání prvního předmětu z inventáře
     * Využívá se pouze pro inventář 'obleceni', kde se skrz ní zjišťuje,
     * zdali se v inventáři nachází rouška.
     * 
     * @return první předmět v inventáři
     */
    public Item getFirstItem() {
        return storedItems.iterator().next();
    
    }
    
    /**
     * Metoda která vrací kolekci HashSet se všemy předměty v daném inventáři
     * 
     * @return storedItems kolekce HashSet obsahující předměty v inventáři
     */
    public Set storedItems() {
        return storedItems;
        
    }

    public Set<String> namesOfStoredItems() {
        Set items = new HashSet<>();
        for (Item item : storedItems) {
            items.add(item.getName());
        }
        return items;
    }
    
    /**
     * Metoda, která maže všechny předměty z daného inventáře
     */
    public void deleteContentInInventory() {
        storedItems.clear();
    }
    
    /**
     * Metoda, která vypisuje string všech uložených věcí
     * 
     * @return seznam všech uložených předmětů v daném inventáři
     */
    public String listOfStoredItems() {
        String list = "";
        for (Item item : storedItems) {
            list = list + item.getName() + " ";
        }   
        return "Předměty v inventáři " + name + ": " + list;
    }
    
    /**
     * Metoda, která kontroluje zdali se požadovaný předmět
     * nachází v daném inventáři.
     * 
     * @param name jméno hledaného předmětu
     * @return true, pokud se předmět v inventáři nachází, jinak false
     */
    public boolean isIncludingItem(String name) {
        for (Item item : storedItems) { 
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
        
    }
    
    /**
     * Metoda, která přidává předmět do inventáře.
     * 
     * @param name jméno přidávaného předmětu
     * @return true, za předpokladu že je v inventáři místo a předmět se přidá, jinak false
     */
    public void addItemToInventory(Item name) {
        this.storedItems.add(name);
        //notifyObservers();
    }
    
    /**
     * Metoda, která zjistí jestli je v inventáři místo, pokud ano přidá předmět
     * 
     */
    public boolean checkAndAddItem(Item item) {

        if (item.getWeight() < this.getFreeCapacity()) {
            storedItems.add(item);
            notifyObservers();
            return true;
        }
        return false;
        
    }

    @Override
    public void register(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update();
        }

    }
}