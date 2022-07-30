package cz.vse.java.nerv01.adventura.core;

import java.util.Random;

/**
 * Třída PinCode slouží k vygenerování náhodného čtyřmístného pin kódu,
 * který je využit při výběru peněz z bankomatu.
 * Původně byla funkce vygenerování čísla ve třídě CommandWithdraw,
 * kvůli testovacím třídám jsem ovšem musel vytvořit tuto separátní třídu,
 * jednak abych mohl na toto číslo v testu nějakým způsobem odkázat,
 * a druhak abych mohl vytvořit jednu instanci této třídy, protože kód by
 * se pak generoval přo běhu programu opakovaně.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class PinCode
{
    private int pinCode;
    /**
     * Konstruktor pro vytvoření instance třídy PinCode.
     * Po vytvoření se v instanci vygeneruje náhodný pin kód.
     */
    public PinCode() {
        generatePinCode();
    }
    
    /**
     * Metoda, která vygeneruje náhodné celé číslo s hodnotou
     * od 0 do 9999 a zapíše jej do proměnné pinCode.
     * Za datový typ proměnné pinCode je zvolen int, který sice
     * neumí vytvořit pin kód ve správném slova smyslu (tj. neumí
     * nuly na začátku čísla - jako třeba 0052 zapíše pouze jako 52),
     * ale lépe se s ním bude pracovat při porovnávání zadaného
     * a vygenerovaného kódu.
     * 
     * @return pinCode již vygenerovaný pin kód
     */
    private int generatePinCode () {
        Random randomNumber = new Random();
        pinCode = randomNumber.nextInt(10000);
        return pinCode;
    }
    
    /**
     * Metoda typu getter, která vrátí vygenerovaný pin kód.
     * 
     * return pinCode vrátí vygenerovaný pin kód
     */
    public int getPinCode() {
        return pinCode;
    }
}
