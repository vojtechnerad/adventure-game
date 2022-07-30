package cz.vse.java.nerv01.adventura.core;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testovací třída InventoryTest
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class InventoryTest
{
    private Item milk;
    private Item eggs;
    private Item juice;
    private Item pizza;
    private Item toiletPaper;
    private Item dogFood;
    private Item lightBulbs;
    private Item bookJava;
    private Item bookEconomy;
    
    /**
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @BeforeEach
    public void setUp() {        
        milk = new Item("mleko", "Kartón mléka", 1f, 20, true);
        eggs = new Item("vejce", "Plato vajec", 1.5f, 30, true);
        juice = new Item("dzus", "Jablečný džus", 2f, 40, true);
        pizza = new Item("pizza", "Balení mražených pizz", 5f, 300, true);
        toiletPaper = new Item("papir", "Balení toaletního papíru", 5f, 100, true);
        dogFood = new Item("granule", "Psí granule", 3f, 150, true);
        lightBulbs = new Item("zarovky", "Žárovky", 1f, 70, true);
        bookJava = new Item("java", "Java bez předchozích znalostí", 0.5f, 300, true);
        bookEconomy = new Item("ekonomie","Učebnice Ekonomie", 1f, 900, true);
    }

    /**
     * Testovací třída která testuje omezenost inventářů 'batoh' a 'kosik',
     * a neomezenost inventáře 'auto'.
     */
    @Test
    public void testInventoryLimitation() {
        Inventory backpack = new Inventory("batoh",10f);
        Inventory shoppingCart = new Inventory("kosik", 10f);
        Inventory car = new Inventory("auto", 100f);

        assertEquals(true, backpack.checkAndAddItem(milk));
        assertEquals(true, backpack.checkAndAddItem(eggs));
        assertEquals(true, backpack.checkAndAddItem(juice));
        assertEquals(true, backpack.checkAndAddItem(pizza));
        assertEquals(false, backpack.checkAndAddItem(toiletPaper));
        
        assertEquals(true, shoppingCart.checkAndAddItem(milk));
        assertEquals(true, shoppingCart.checkAndAddItem(eggs));
        assertEquals(true, shoppingCart.checkAndAddItem(juice));
        assertEquals(true, shoppingCart.checkAndAddItem(pizza));
        assertEquals(false, shoppingCart.checkAndAddItem(toiletPaper));
        
        assertEquals(true, car.checkAndAddItem(milk));
        assertEquals(true, car.checkAndAddItem(eggs));
        assertEquals(true, car.checkAndAddItem(juice));
        assertEquals(true, car.checkAndAddItem(pizza));
        assertEquals(true, car.checkAndAddItem(toiletPaper));
        assertEquals(true, car.checkAndAddItem(dogFood));
        assertEquals(true, car.checkAndAddItem(lightBulbs));
        assertEquals(true, car.checkAndAddItem(bookJava));
        assertEquals(true, car.checkAndAddItem(bookEconomy));
    }

}
