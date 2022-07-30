package cz.vse.java.nerv01.adventura.core;

/**
 * Třída, pro vytvoření předmětů do hry.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class Item {
    private String name;
    private String fullName;
    private float weight;
    private int price;
    private boolean isPickable;
    
    /**
     * Konstruktor pro vytvoření předmětů (instancí třídy).
     */
    public Item(String name, String fullName, float weight, int price, boolean isPickable) {
        this.name = name;
        this.fullName = fullName;
        this.weight = weight;
        this.price = price;
        this.isPickable = isPickable;
    }
    
    /**
     * Metoda typu getter, která vrací název předmětu.
     * Název slouží jako unikátni identifikátor předmětu, který je
     * zapsán bez diakritiky. Tento identifikátor se zároveň používá
     * v jako parametr příkazu 'vloz'.
     * 
     * @return název předmětu
     */
    public String getName() {
        return name;
    }
    
    /**
     * Metoda typu getter, která vrací celý názvu předmětu.
     * Celý název předmětu je několika slovný název předmětu
     * s diakritikou.
     * 
     * @return celý název předmětu.
     */
    public String getFullName() {
        return fullName;
    }
    
    /**
     * Metoda typu getter, která vrací váhu předmětu.
     * 
     * @return váha předmětu
     */
    public float getWeight() {
        return weight;
    }
    
    /**
     * Metoda typu getter, která vrací cenu předmětu.
     * 
     * @return cena předmětu
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * Metoda typu getter, která vrací boolean hodnotu
     * - true, pokud je možné předmět přendat do košíku (je na nákupním listu)
     * - false, pokud předmět není možné přidat do košíku.
     * 
     * @return je možné předmět vložit do nákupního košíku
     */
    public boolean isPickable() {
        return isPickable;
    }
}
