package cz.vse.java.nerv01.adventura.core;

/**
 * Třída Wallet slouží k vytvoření peněženky (instance třídy Wallet),
 * která bude uchovávat informaci o tom, kolik peněz u sebe hráč má.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class Wallet {
    private int balance;
    
    /**
     * Konstruktor pro peněženku
     * 
     * @param balance nastavuje úvodní stav peněz
     */
    public Wallet(int balance) {
        this.balance = balance;
    }
    
    /**
     * Metoda typu setter, která upravuje stav peněz.
     * 
     * @param newBalance nově nastavený stav peněz
     */
    public void setBalance (int newBalance) {
        this.balance = newBalance;
    }
    
    /**
     * Metoda pro přidání peněz do peněženky.
     * 
     * @param additionalBalance počet peněz, které se přidají do peněženky
     * @return výpis kolik peněz bylo přidáno a jaký je aktualizovaný stav peněženky
     */
    public String addBalance(int additionalBalance) {
        balance = balance + additionalBalance;
        return "Přidáno " + additionalBalance + ", celkově nyní " + balance;
    }
    
    /**
     * Metoda pro odebrání peněz z peněženky.
     * 
     * @param deductedBalance počet peněz, které se odeberou z peněženky
     * @return výpis kolik peněz bylo odebráno a jaký je aktualizovaný stav peněženky
     */
    public String deductBalance(int deductedBalance) {
        balance = balance - deductedBalance;
        return "Odebráno " + deductedBalance + ", celkově nyní " + balance;
    }
    
    /**
     * Metoda pro zjištění současného stavu peněz v peněžence.
     * 
     * @return současná suma peněz v peněžence
     */
    public int currentBalance() {
        return balance;
    }
}
