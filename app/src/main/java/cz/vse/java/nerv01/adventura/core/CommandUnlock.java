package cz.vse.java.nerv01.adventura.core;

/**
 * Instance třídy {@code CommandUnlock} představují ...
 * The {@code CommandUnlock} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class CommandUnlock implements ICommand{
    private static final String NAME = "odemkni";
    private GamePlan plan;
    /**
     * Konstruktor třídy CommandUnlock
     */
    public CommandUnlock(GamePlan plan) {
        this.plan = plan;
    }
    
    /**
     * Metoda která vytváří příkaz 'odemkni'.
     * 
     */
    @Override
    public String process(String... parameters) {
        // Kontrola počtu parametrů
        if (parameters.length == 0) {
            return "Nezadal jsi co se má odemknout, napiš to ve tvaru - [odemkni] [parametr]";
        } else if (parameters.length > 1) {
            return "Za příkaz nelze zadat víc než jeden parametr, napíš to ve tvaru - [odemkni] [parametr]";
        }

        // Zapsání parametru do proměnné
        String toUnlock = parameters[0];
        Area currentArea = plan.getCurrentArea();
        if (currentArea.getName().equals("portal")) {
            if (toUnlock.equals("portal")) {
                if (plan.getBackpack().isIncludingItem("klic")) {
                    Area emptySpace = plan.getEmptySpace();
                    plan.setCurrentArea(emptySpace);
                    addDemandedItems();
                    return plan.getCurrentArea().getFullDescription();
                    
                } else {
                    return "Nemáš u sebe klíč, abys ho mohl odemknout.";
                    
                }
                
            } else {
                return "Tohle tady odemknout nejde.";
                
            }
        } else {
            return "Tady není nic k odemknutí.";
            
        }
        
        
    }
    
    /**
     * Getter pro název příkazu
     */
    @Override
    public String getName() {
        return NAME;
    }
    
    private void addDemandedItems() {
        plan.getBackpack().deleteContentInInventory();
        Item milk = new Item("mleko", "Kartón mléka", 1f, 20, true);
        Item eggs = new Item("vejce", "Plato vajec", 1.5f, 30, true);
        Item juice = new Item("dzus", "Jablečný džus", 2f, 40, true);
        Item pizza = new Item("pizza", "Balení mražených pizz", 5f, 300, true);
        Item toiletPaper = new Item("papir", "Balení toaletního papíru", 5f, 100, true);
        Item dogFood = new Item("granule", "Psí granule", 3f, 150, true);
        Item lightBulbs = new Item("zarovky", "Žárovky", 1f, 70, true);
        Item bookJava = new Item("java", "Java bez předchozích znalostí", 0.5f, 300, true);
        Item bookEconomy = new Item("ekonomie","Učebnice Ekonomie", 1f, 900, true);
        Item miniture = new Item("figurka", "STAR WARS: Kylo Ren figurka", 4f, 1000, true);
        plan.getCar().addItemToInventory(milk);
        plan.getCar().addItemToInventory(eggs);
        plan.getCar().addItemToInventory(juice);
        plan.getCar().addItemToInventory(pizza);
        plan.getCar().addItemToInventory(toiletPaper);
        plan.getCar().addItemToInventory(dogFood);
        plan.getCar().addItemToInventory(lightBulbs);
        plan.getCar().addItemToInventory(bookJava);
        plan.getCar().addItemToInventory(bookEconomy);
        plan.getCar().addItemToInventory(miniture);
        
    }
}
