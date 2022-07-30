package cz.vse.java.nerv01.adventura.core;

import java.util.Scanner;
import java.util.regex.*;

/**
 * Třída CommandWithdraw vytvoří příkaz 'vyber_penize', který
 * za předpokladu, že hráč stojí u bankomatu, nechá hráče uhádnout 
 * čtyřmístný pin kód, aby si mohl vybrat všechny peníze z účtu.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class CommandWithdraw implements ICommand {
    private static final String NAME = "vyber_penize";
    private GamePlan plan;
    private String playersGuess;
    private boolean alreadyWithrawn;
    private int generatedPin;
    Scanner scanner = new Scanner(System.in);
    /**
     * Konstruktor třídy CommandWithdraw
     */
    public CommandWithdraw(GamePlan plan) {
        this.plan = plan;
        alreadyWithrawn = false;
        generatedPin = plan.getPinCode();
    }
    
    /**
     * V případě, že je metoda zavolána bez parametrů pouze příkazem 'vyber_penize',
     * a hráč se nachází v lokaci 'bankomat', spustí se hádací hra.
     * Hra se předčasně ukončí za předpokladu, že hráč si již peníze vybral dříve.
     * Po zadání textového řetězce hráčem, se nejprve zkontroluje jestli nezadal slovo 'konec',
     * které ukončuje tuto hádací minihru.
     * Pro jakýkoliv jiný textový řetězec, se nejprve zkontroluje pomocí regex
     * 
     * 
     */
    @Override
    public String process(String... parameters) {
        // Kontrola jestli za příkazem není zadán žádný parametr
        if (parameters.length > 0) {
            return "Příkaz 'vyber_penize' se píše bez parametru.";
            
        } else {
            // Kontrolam jestli se hráč nachází v lokaci bankomat
            if (plan.getCurrentArea().getName().equals("bankomat")) {
                // Kontrola, jestli si hráč již peníze nevybíral
                if (alreadyWithrawn) {
                    return "Už sis tuhle hru vybral, víc peněz nepotřebuješ.";
                }
                
                // Vypíše úvodní informace k vybraní peněz z bankomatu
                System.out.println(getAtmInfo());
                
                // Pomocné proměnné
                byte attempt = 0;
                boolean accessGranted = false;
                String REGEX_NUMBER_CHECK = "[0-9]+";
                //Pattern pattern = Pattern.compile(regexNumberCheck);
                
                while (!accessGranted) {
                    System.out.print("> ");
                    playersGuess = scanner.nextLine();
                    
                    // Vyjímka pro slovo 'konec', kterým se hádání ukončí a hráč se navratí do lokace 'bankomat'
                    if (playersGuess.equals("konec")) {
                        return "Končíš hádání.\n" + plan.getCurrentArea().getFullDescription() + "\n";
                        
                    } else {
                        // Kontrola regexem, jestli se zadaný text skládá pouze z čísel
                        if(playersGuess.matches(REGEX_NUMBER_CHECK)) {
                            // Kontrola, jestli je zadaná sekvence čísel čtyřmístná
                            if (playersGuess.length() == 4) {
                                attempt++;
                                int parsedGuess =  Integer.parseInt(playersGuess);
                                
                                if (parsedGuess > generatedPin) {
                                    System.out.print(playersGuess + " to není, zkus zadat menší číslo.");
                                    if (attempt > 5) {
                                        String lastDigit = Integer.toString(generatedPin);
                                        System.out.print(" A myslím že poslední číslo je " + lastDigit.charAt(3));
                                        
                                    }
                                    System.out.println("\n");
                                    
                                } else if (parsedGuess < generatedPin) {
                                    System.out.print(playersGuess + " to není, zkus zadat větší číslo.");
                                    if (attempt > 5) {
                                        String lastDigit = Integer.toString(generatedPin);
                                        System.out.print(" A myslím že poslední číslo je " + lastDigit.charAt(3));
                                        
                                    }
                                    System.out.println("\n");
                                    
                                } else {
                                    accessGranted = true;
                                    
                                }
                            } else {
                                System.out.println("PIN kód musí být čtyřmístný.");
                                
                            }
                        } else {
                            System.out.println("PIN kód se musí skládat pouze z čísel.");
                            
                        }
                        
                    }
                }
                int addedMoney = 5000;
                plan.getWallet().addBalance(addedMoney);
                setAlreadyWithrawn();
                return "PIN jsi uhodl a vybral sis " + addedMoney + " Kč.\n"
                        + "Momentálně máš v peněžence " + plan.getWallet().currentBalance() + " Kč.\n\n"
                        + plan.getCurrentArea().getFullDescription();
                        
            } else {
                return "K výběru peněz musíš stát u bankomatu.";
                
            }
            
        }
        
    }
    
    /**
     * {@inheritDoc}
     */
    public String getName() {
        return NAME;
    }
    
    /**
     * 
     * Metoda, která vrací úvodní textaci po použití příkazu 'vyber_penize'
     * 
     * @return getAtmInfo() úvodní textace k výběru peněz
     */
    private String getAtmInfo() {
        return "V tomhle příšerným stresu si nedokážeš vzpomenout\n"
               + "na svůj PIN kód k bankovnímu účtu.\n"
               + "Budeš muset improvizovat a uhodnout ho.\n"
               + "PIN kód ke kartě je čtyřmístný, hodně štěstí.\n";
    }
    
    /**
     * Metoda, která zajišťuje že se z bankomatu dá vybrat pouze jednou
     */
    private void setAlreadyWithrawn() {
        alreadyWithrawn = true;
    }
}   