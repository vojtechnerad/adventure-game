package cz.vse.java.nerv01.adventura.core;

/**
 * Hlavní třída logiky (core) aplikace.
 * Třída vytváří instanci třídy GamePlan, která iniciaizuje lokace hry,
 * přechody mezi lokacema, předměty, vytváří seznam platných příkazů
 * a instance tříd provádějících jednotlivé příkazy.
 * Během hry třída vypisuje uvítací a ukončovací texty, a vyhodnocuje
 * jednotlivé příkazy zadané uživatelem (s vyjímkou minihry hádání PINu).
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class Game implements IGame {
    private ListOfCommands listOfCommands;
    private GamePlan gamePlan;
    private boolean gameOver;
    
    /**
     * Konstruktor třídy Game.
     * Vytvoří hru, inicializuje herní plán udržující aktuální stav hry,
     * a seznam platných příkazů.
     */
    public Game()
    {
        gameOver = false;
        gamePlan = new GamePlan();
        listOfCommands = new ListOfCommands();
        
        listOfCommands.addCommand(new CommandHelp(listOfCommands));
        listOfCommands.addCommand(new CommandTerminate(this));
        listOfCommands.addCommand(new CommandMove(gamePlan));
        listOfCommands.addCommand(new CommandPick(gamePlan));
        listOfCommands.addCommand(new CommandContent(gamePlan));
        listOfCommands.addCommand(new CommandLoad(gamePlan));
        listOfCommands.addCommand(new CommandPay(gamePlan));
        listOfCommands.addCommand(new CommandWithdraw(gamePlan));
        listOfCommands.addCommand(new CommandPutOn(gamePlan));
        listOfCommands.addCommand(new CommandSearch(gamePlan));
        listOfCommands.addCommand(new CommandUnlock(gamePlan));
    }
    
    /**
     * Metoda, která vrátí úvodní zprávu pro hráče.
     * 
     * @return vrací řetězec, který se vypíše na CLI
     */
    @Override
    public String getPrologue() {
        return "******************************* KARANTÉNNÍ NÁKUP *******************************\n"
                + "Píše se rok 2020, právě se dozvídáš z mimořádné vysílací relace,\n"
                + "že kvůli se koronavirové epidemii zavádí vážné restrikce ohledně\n"
                + "vycházení a prakticky uzavírá celý stát.\n"
                + "Se zděšeným výrazem se pootočíš na poloprázdnou lednici a začneš přemýšlet.\n"
                + "Po chvilce racionálního uvažování tě napadne\n"
                + "skvělá věc – půjdeš na nákup zásob na půl roku dopředu.\n"
                + "Zvedáš se ze židle, hledáš nákupní seznam, připisuješ na něj nový věci,\n"
                + "oblíkáš se, vycházíš z bytu, sedáš do auta a vyrážíš na výlet.\n"
                + "Po 10minutové cestě a 30minutovém hledání volného místa v podzemní garáží\n"
                + "obchodního centra z auta vystoupíš a tady hra začíná.\n"
                + "CÍLEM HRY je nakoupit všechny položky z poskytnutého\n"
                + "nákupního seznamu, přenést je do auta a odjet pryč.\n"
                + "V případě že by ses zasekl a potřeboval bys nápovědu\n"
                + "nebo vypsat příkazy této hry, stačí napsat 'pomoc'.\n"
                + "*****************************************************************************\n\n"
                + gamePlan.getCurrentArea().getFullDescription();

    }
    
    /**
     * Metoda, která vrátí závěrečnou zprávu pro hráče.
     * 
     * @return vrací řetězec, který se vypíše na CLI
     */
    public String getEpilogue() {
        return "Doufám že sis hru užil.\n"
                + "Hra se nyní ukončí.";

    }
    
    /**
     * Metoda, která vrací informaci o tom, jestli hra skončila.
     * Je jedno jestli výhrou, prohrou nebo příkazem konec.
     * 
     * @return vrací hodnotu true, pokud hra skončila
     */
    @Override
    public boolean isGameOver() {
        return gameOver;
        
    }
    
    /**
     * Metoda, která zpracuje řetězec zapsaný do CLI. Řetězec rozdělí
     * na část příkazu a část parametru - [příkaz] [parametr].
     * Dále se otestuje, zdali je zadaný příkaz skutečně předdefinovaným
     * příkazem, pokud ano, příkaz se provede.
     * 
     * @param line text, který zadal uživatel jako příkaz (popřípadě i parametr) do hry.
     * @return vrací se řetězec, který se má vypsat na CLI
     */
    @Override
    public String processCommand(String line) {
        String[] words = line.split("[ \t]+");
        
        String cliName = words[0];
        String[] cliParameters = new String[words.length - 1];
        
        for (int i = 0; i < cliParameters.length; i++) {
            cliParameters[i] = words[i + 1];
        }
        
        String result = null;
        if (listOfCommands.checkCommand(cliName)) {
            ICommand command = listOfCommands.getCommand(cliName);
            result = command.process(cliParameters);
        } else {
            result = "Špatný příkaz, zkus zadat 'pomoc' a podívat se na validní příkazy.";
        }
        
        if (gamePlan.isVictorious()) {
            gameOver = true;
        }
        
        return result;
        
    }
    
    /**
     * Metoda, která vrací odkaz na herní plán.
     * Je využita převážně v testech, kde se jejím
     * prostřednictvím získává aktuální místnost hry.
     * 
     * @return odkaz na herní plán
     */
    @Override
    public GamePlan getGamePlan() {
        return gamePlan;
        
    }
    
    /**
     * Metoda nastaví příznak indikující, že nastal konec hry.
     * Metodu využívá třída CommandTerminate,
     * mohou ji ale použít i další implementace rozhraní ICommand.
     * 
     * @param gameOver nastavení konce hry
     */
    void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        
    }
}
