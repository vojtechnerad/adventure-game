package cz.vse.java.nerv01.adventura.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testovací třída {@code ItemTest} slouží ke komplexnímu otestování
 * třídy {@link ItemTest}.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class ItemTest {
    /***************************************************************************
     * Test věcí, které jdou vzít do batohu, tj. jsou na seznamu a jsou potřeba
     * získat pro vyhrání hry.
     */
    @Test
    public void testPickableItems() {
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
        Item catFood = new Item("granule_kocky", "Granule pro kočky", 4f, 200, false);
        Item fishFood = new Item("krmivo", "Krmivo pro ryby", 2f, 150, false);
        Item protectiveFaceMask = new Item("rouska", "Předražená rouška", 0.1f, 50, false);
        Item bananas = new Item("banany", "Trs banánů", 1.0f, 50, false);
        Item bookMath = new Item("matika", "Matematika pro studenty VŠE", 0.5f, 400, false);
        Item bookCzech = new Item("cestina", "Čeština pro střední školy", 0.5f, 600, false);
        Item bookMaths2 = new Item("matika2", "Matematika pro základní školy", 0.5f, 200, false);
        Item samsungPhone = new Item("s10e", "Samsung S10e", 0.5f, 15999, false);
        Item applePhone = new Item("iphone", "Apple iPhone Pro Max XD Edge 5G", 0.5f, 35000, false);
        Item appleComputer = new Item("mac", "Apple Mac Pro", 10f, Integer.MAX_VALUE, false);
        Item tshirt = new Item("tricko", "MARVEL tričko", 0.5f, 500, false);
        Item poster = new Item("plakat", "Stranger Things plakát", 0.5f, 200, false);
        
        assertEquals(true, milk.isPickable());
        assertEquals(true, eggs.isPickable());
        assertEquals(true, juice.isPickable());
        assertEquals(true, pizza.isPickable());
        assertEquals(true, toiletPaper.isPickable());
        assertEquals(true, dogFood.isPickable());
        assertEquals(true, lightBulbs.isPickable());
        assertEquals(true, bookJava.isPickable());
        assertEquals(true, bookEconomy.isPickable());
        assertEquals(true, miniture.isPickable());
        assertEquals(false, catFood.isPickable());
        assertEquals(false, fishFood.isPickable());
        assertEquals(false, protectiveFaceMask.isPickable());
        assertEquals(false, bananas.isPickable());
        assertEquals(false, bookMath.isPickable());
        assertEquals(false, bookCzech.isPickable());
        assertEquals(false, bookMaths2.isPickable());
        assertEquals(false, samsungPhone.isPickable());
        assertEquals(false, applePhone.isPickable());
        assertEquals(false, appleComputer.isPickable());
        assertEquals(false, tshirt.isPickable());
        assertEquals(false, poster.isPickable());
        
    }

}
