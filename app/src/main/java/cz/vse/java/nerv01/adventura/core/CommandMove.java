package cz.vse.java.nerv01.adventura.core;

/**
 * Třída implementující příkaz pro pohyb mezi herními lokacemi.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class CommandMove implements ICommand {
    private static final String NAME = "jdi";
    private GamePlan plan;
    
    /**
     * Konstruktor třídy CommandMove.
     * 
     * @param plan odkaz na herní plán s aktuálním stavem hry
     */
    public CommandMove(GamePlan plan) {
        this.plan = plan;
    }
    
    /**
     * Metoda se pokusí přesunout hráče do jiné lokace.
     * Nejprve zkontroluje počet parametrů. Pokud nebyl zadán žádný parametr,
     * nebo jich bylo zadáno více,  vrátí chybové hlášení.
     * Pokud byl zadán právě jeden parametr, zkontroluje zda s aktuální lokací
     * sousedí jiná lokace s daným názvem. Pokud vrátí ne, vrátí chybové hlášení.
     * Pokud všechny kontroly proběhnou v pořádku, provede přesun hráče
     * do cílové lokace a vrátí její popis.
     * 
     * @param parameters parametry příkazu, očekává se pole s jedním prvkem
     * @return informace pro hráče, které se vypíší na konzoli
     */
    @Override
    public String process(String... parameters) {
        if (parameters.length == 0) {
            return "Musíš zadat kam chceš jít, napiš to znovu ve tvaru [jdi] [místo kam chceš jít].";
        } else if (parameters.length > 1) {
            return "Nemůžeš jít na více lokací zároveň, napiš příkaz znovu ve tvaru [jdi] [kam chceš jít].";
        }
        
        if (plan.getShoppingCart().storedItems().size() > 0) {
            return "Máš u sebe nezaplacený nákup, teď nemůžeš nikam odejít."
                    + "Pro zaplacení nákupu použij příkaz 'zaplat'.";
        } else {
            // Název cílové lokace se uloží do pomocné proměnné exitName
            String exitName = parameters[0];
            
            // Zkusíme získat odkaz na cílovou lokaci
            Area exitArea = plan.getCurrentArea().getExitArea(exitName);
            
            if (exitArea == null) {
                return "Do lokace " + exitName + " se jít nedá.";
            }
            
            boolean haveMask = plan.getClothing().isIncludingItem("rouska");
            if (!exitArea.canStepInWithoutMask() && haveMask) {
                // Změníme aktuální lokaci (přesuneme hráče) a vrátíme popis nové lokace
                plan.setCurrentArea(exitArea);
                return exitArea.getFullDescription();
            } else if (!exitArea.canStepInWithoutMask() && !haveMask) {
                return "Sekuriťák tě nechce pustit bez roušky, zaběhni si nějakou koupit do lékárny. ";
            } else {
                // Změníme aktuální lokaci (přesuneme hráče) a vrátíme popis nové lokace
                plan.setCurrentArea(exitArea);
                return exitArea.getFullDescription();
            }
        }
    }
    
    /**
     * Getter názvu příkazu
     */
    @Override
    public String getName() {
        return NAME;
    }
}
