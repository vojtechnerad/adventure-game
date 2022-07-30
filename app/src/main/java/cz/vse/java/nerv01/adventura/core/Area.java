package cz.vse.java.nerv01.adventura.core;

import java.util.*;

/**
 * Třída vytváří nové lokace, v případě této hry prostory obchodního domu.
 * Každá lokace má název, který ji jednoznačně identifikuje.
 * Lokace vždy má jednu či více sousedních lokací, do kterých lze odejít.
 * Odkazy na všechny sousední lokace jsou uložené v kolekci.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class Area {
    private String name;        // Název lokace
    private String description; // Popis lokace
    private Set<Area> exits;    // Kolekce obsahující sousední lokace, do kterých lze z dané lokace odejít
    private boolean canStepInWithoutMask;   // Atribut říkající, jestli hráč může vstoupit do obchodu s roužkou nebo ne
    private Map<String, Item> items;
    
    /**
     * Konstruktor třídy Area, který vytvoří lokaci se zadaným názvem,
     * zadaným popisem, zadanými předměty se seznamem východů z lokace.
     * 
     * @param name název lokace, jednoznačný identifikátor, musí se jednat o text bez mezer
     * @param description popis lokace
     * @param canStepInWithoutMask možnost vstoupit do lokace bez nasazené roušky
     */
    public Area(String name, String description, boolean canStepInWithoutMask) {
        this.name = name;
        this.description = description;
        this.canStepInWithoutMask = canStepInWithoutMask;
        this.exits = new HashSet<>();
        this.items = new HashMap<>();
    }
    
    /**
     * Metoda vrací název lokace, který byl zadán při vytváření instance jako
     * parametr konstruktoru. Jendá se o jednoznačný identifikátorlokace.
     * Aby hra správně fungovala, název lokace NESMÍ obsahovat mezery.
     * 
     * @return název lokace
     */
    public String getName() {
        return name;
        
    }
    
    /**
     * Metoda vrací boolean hodnotu, jestli hráč může vstoupit do lokace
     * bez nasazené roušky {@code true}, nebo ne {@code false}.
     * 
     * return stav, jestli hráč může vstoupit do obchodu bez roušky
     */
    public boolean canStepInWithoutMask() {
        return canStepInWithoutMask;
        
    }
    
    /**
     * Metoda přidá další východ z této lokace do lokace předané v parametru.
     * 
     * @param area lokace, do které bude vytvořen východ z aktuální lokace
     */
    public void addExit(Area area) {
        exits.add(area);
        
    }
    
    /**
     * Metoda vrací detailní informace o lokaci.
     * Výsledek volání obsahuje název lokace, podrobnější popis, seznam
     * sousedních lokací, do kterých lze odejít a seznam předmětů,
     * které se v dané lokaci nachází.
     * 
     * @return detailní informace o lokaci
     */
    public String getFullDescription() {
        String exitNames = "Východy: ";
        for (Area exitArea : exits) {
            exitNames = exitNames + " " + exitArea.getName();
        }
        
        String itemNames = "Předměty:\n";
        for (Item item : items.values()) {
            itemNames = itemNames + "(" + item.getName() + ") " + item.getFullName() + " [" + item.getPrice() + " CZK | " + item.getWeight() + " cm^3]\n";
        }
        
        return "Momentálně se nacházíš v lokaci: " + name + "\n"
               + description + "\n"
               + exitNames + "\n"
               + itemNames;
    }
    
    /**
     * Metoda vrací lokaci/lokace, která/é sousedí s aktuální lokací, a jejíž název
     * je předán v parametru. Pokud lokace s daným jménem nesousedí s aktuální lokací,
     * vrací se hodnota {@code null}.
     * Metoda je implementována pomocí lambda výrazu.
     * 
     * @param areaName jméno sousední lokace (východu)
     * @return lokace, která se nachází za příslušným východem; {@code null}, pokud aktuální lokace s touto nesousedí
     */
    public Area getExitArea(String areaName) {
        return exits.stream()
                    .filter(exit -> exit.getName().equals(areaName))
                    .findAny().orElse(null); 
    }

    public HashSet<String> getItemsInArea() {
        HashSet<String> itemNames= new HashSet<String>(items.keySet());
        return itemNames;
    }

    public Set getAjdacentAreas() {
        return exits;
    }

    /**
     * Metoda porovnává dvě lokace (objekty).
     * Lokace jsou shodné, pokud mají stejný název (atribut {@link #name}).
     * Tato metoda je důležitá pro správné fungování seznamu východů do sousedních lokací.
     * 
     * @param o objekt, který bude porovnáván s aktuálním objektem
     * @return {@code ture}, pokud mají obě lokace stejný název, jinak {@code false}
     */
    @Override
    public boolean equals(Object o) {
        // Ověření, že parametru není null
        if (o == null) {
            return false;
        }
        
        // Ověříme, že se nejedná o stejnou instanci (objekt)
        if (this == o) {
            return true;
        }
        
        // Ověříme, že parametr je typu (objekt třídy) Area
        if (!(o instanceof Area)) {
            return false;
        }
        
        // Provede se 'tvrdé' přetypování
        Area area = (Area) o;
        
        /*
         * Provedeme porovnání názvů, statická metoda equals
         * z pomocné třídy java.util.Objects porovná hodnoty obou parametrů a vrátí true
         * pro stejné názvy, a to i v případě že jsou oba názvy null; jinak vrátí false
         */
        return Objects.equals(this.name, area.name);
        
    }
    
    /**
     * Metoda vrací číselný identifikátor instance, který se používá pro optimalizaci
     * v ukládání dynamických datových strukturách (např. {@linkplain HashSet}).
     * Při překrytí metody {@link #equals(Object) equals} je vždy nutné překrýt i tuto metodu.
     * 
     * @return celočíselný identifikátor instance
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    
    /**
     * Metoda, která přidá předmět (objekt třídy {@link Item} do lokace.
     * 
     * @param item předmět, který bude do lokace přidán
     */
    public void addItem(Item item) {
        items.put(item.getName(), item);
        
    }
    
    /**
     * Metoda která maže předmět z prostoru
     * 
     * @return smaže předmět z dané lokace
     */
    public Item removeItem(String itemName) {
        return items.remove(itemName);
        
    }
    
    /**
     * Metoda typu getter, pro předmět z prostoru
     * 
     * @return odešle předmět
     */
    public Item getItem(String itemName) {
        return items.get(itemName);
        
    }
    
    /**
     * Metoda vrací bool hodnotu jestli prostor obsahuje předmět nebo ne
     * 
     * @return vrací boolean hodnotu, jestli lokace obsahuje dotazovaný předmět nebo ne
     */
    public boolean containsItem(String itemName) {
        return items.containsKey(itemName);
        
    }

    @Override
    public String toString() {
        return getName();
    }
}
