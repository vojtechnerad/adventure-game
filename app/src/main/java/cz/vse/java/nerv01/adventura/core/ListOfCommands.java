package cz.vse.java.nerv01.adventura.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Třída ListOfCommands se stará o přidávání příkazů (instancí této třídy)
 * do adventury. Všechny nové příkazy se přidávají přes třídu GamePlan,
 * kam se zapíší třídy, na které tyto příkazy odkazují.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class ListOfCommands {
    private Map<String, ICommand> commands; // Mapa pro uložení přípustných příkazů
    
    /**
     * Konstruktor třídy ListOfCommands, který vytváří mapu pro
     * uložení přípustných příkazů.
     */
    public ListOfCommands() {
        this.commands = new HashMap<>();
        
    }
    
    /**
     * Metoda vkládá nový příkaz do seznamu.
     * 
     * @param command instance třídy implementující rozhraní ICommand, která představuje herní příkaz
     */
    public void addCommand(ICommand command) {
        commands.put(command.getName(), command);
        
    }
    
    /**
     * Metoda, která vrací odkaz na instanci třídy implementující příkaz s daným názvem.
     * 
     * @param name název příkazu, který chce hráž zavolat
     * @return instance třídy, představující implementaci daného příkazu, null pokud takový příkaz neexistuje
     */
    public ICommand getCommand(String name) {
        return commands.get(name);
        
    }
    
    /**
     * Metoda, která kontroluje, zda je možné ve hře použít příkaz se zadaným názvem.
     * 
     * @param name název příkazu
     * @return true, pokud je možné příkaz ve hře použít, jinak false
     */
    public boolean checkCommand(String name) {
        return commands.containsKey(name);
        
    }
    
    /**
     * Metoda, která vrací seznam názvů všech přípustných příkazů ve hře.
     * Jednotlivé příkazy jsou odděleny mezerou.
     * 
     * @return seznam názvů přípustných příkazů
     */
    public String getNames() {
        String list = "";
        for (String commandName : commands.keySet()) {
            list = list + commandName + " ";
        }
        return list;
        
    }
}