
package cz.vse.java.nerv01.adventura.core;

import cz.vse.java.nerv01.adventura.main.Observer;
import cz.vse.java.nerv01.adventura.main.Subject;

import java.util.*;

/**
 * Třída představující aktuální stav hry.
 * Veškeré informace o stavu hry (mapa lokací, inventáře, úkoly apod.) by měly
 * být uložené zde v podobě datových atributů.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class GamePlan implements Subject {
    private Area garage;
    private Area lobby;
    private Area groceries;
    private Area atm;
    private Area secondFloor;
    private Area pharmacy;
    private Area petShop;
    private Area bookShop;
    private Area comicsShop;
    private Area electronicsShop;
    private Area currentArea;
    private Area portal;
    private Area emptySpace;
    private Inventory backpack;
    private Inventory shoppingCart;
    private Inventory car;
    private Inventory clothing;
    private Inventory chest;
    private Set<Item> collectionOfAllItems;
    private Set<Item> wantedItems;
    private Wallet wallet;
    private boolean isVictorious;
    private PinCode pinCode;

    private Set<Observer> observerList;

    /**
     * Konstruktor třídy GamePlan.
     * Pomocí metody {@link #prepareWorldMap() prepareWorldMap}
     */
    public GamePlan() {
        observerList = new HashSet<>();
        isVictorious = false;
        prepareWorldMap();
        prepareItems();
        prepareTask();
        
        // Inicialiazce peněženky a nastavení počátečního množství peněz
        final int InitialBalance = 1000;
        wallet = new Wallet(InitialBalance);
        
        // Vytvoření inventáře
        backpack = new Inventory("batoh",10f);
        shoppingCart = new Inventory("kosik", 10f);
        car = new Inventory("auto", 100f);
        clothing = new Inventory("obleceni", 0.1f);
        
        
        // Vygenerování pin kódu k bankomatu
        pinCode = new PinCode();
    }
    
    /**
     * Metoda, která vytváří jednotlivé lokace a propojuje je pomocí východů.
     * Výchozí lokací se nastavuje začáteční pozice.
     */
    private void prepareWorldMap() {
        // Vytvoření jednotlivích lokací
        garage = new Area("garaz","Svoje auto si zaparkoval na pozici J7, nanos do něj nákup a pak rychle odjeď .", true);
        lobby = new Area("vstupni_hala","Nejnacpanější obchodní centrum, kam si mohl zajet. Rychle běž pro nákup .", true);
        groceries = new Area("potraviny","Snad ještě lidi nevybrali všechno, abych měl co k jídlu.", false);
        atm = new Area("bankomat","Bankomat banky, kterou i vy můžete mít rádi. Zde můžeš použít příkaz pro výběr peněz.", true);
        secondFloor = new Area("patro2","Kolem jsou tuny obchodů .", true);
        pharmacy = new Area("lekarna","Dezinfekce a roušky jsou skoro vyprodaný .", true);
        petShop = new Area("zverimex","Smrdí to tu po psích granulích. Tuším že jsem chtěl vzít i něco pro psa. ", false);
        bookShop = new Area("knihkupectvi","Nejradši bych tu nic nekupoval, ale tyhle učebnice nejdou stáhnout z netu .", false);
        comicsShop = new Area("comicspoint","Všechno tady vypadá epicky, škoda že si nemůžu koupit všechno .", false);
        electronicsShop = new Area("elektro","Příšerně předražený elektro, proč jsem si to neobjednal přes Azlu.", false);

        // Nastavení sousedních lokací
        garage.addExit(lobby);
        lobby.addExit(garage);
        lobby.addExit(groceries);
        lobby.addExit(atm);
        lobby.addExit(secondFloor);
        atm.addExit(lobby);
        groceries.addExit(lobby);
        secondFloor.addExit(lobby);
        secondFloor.addExit(pharmacy);
        secondFloor.addExit(petShop);
        secondFloor.addExit(bookShop);
        secondFloor.addExit(comicsShop);
        secondFloor.addExit(electronicsShop);
        pharmacy.addExit(secondFloor);
        petShop.addExit(secondFloor);
        bookShop.addExit(secondFloor);
        comicsShop.addExit(secondFloor);
        electronicsShop.addExit(secondFloor);
        
        currentArea = garage;
    }
    
    /**
     * Metoda pro vygenerování předmětů a přidání jich do lokací.
     */
    private void prepareItems() {        
        Item milk = new Item("mleko", "Kartón mléka", 1f, 20, true);
        Item eggs = new Item("vejce", "Plato vajec", 1.5f, 30, true);
        Item juice = new Item("dzus", "Jablečný džus", 2f, 40, true);
        Item pizza = new Item("pizza", "Balení mražených pizz", 5f, 300, true);
        Item toiletPaper = new Item("papir", "Balení toaletního papíru", 5f, 100, true);
        Item dogFood = new Item("granule", "Psí granule", 3f, 150, true);
        Item catFood = new Item("granule_kocky", "Granule pro kočky", 4f, 200, false);
        Item fishFood = new Item("krmivo", "Krmivo pro ryby", 2f, 150, false);
        Item lightBulbs = new Item("zarovky", "Žárovky", 1f, 70, true);
        Item bookJava = new Item("java", "Java bez předchozích znalostí", 0.5f, 300, true);
        Item bookEconomy = new Item("ekonomie","Učebnice Ekonomie", 1f, 900, true);
        Item protectiveFaceMask = new Item("rouska", "Předražená rouška", 0.1f, 50, false);
        Item bananas = new Item("banany", "Trs banánů", 1.0f, 50, false);
        Item bookMath = new Item("matika", "Matematika pro studenty VŠE", 0.5f, 400, false);
        Item bookCzech = new Item("cestina", "Čeština pro střední školy", 0.5f, 600, false);
        Item bookMaths2 = new Item("matika2", "Matematika pro základní školy", 0.5f, 200, false);
        Item samsungPhone = new Item("s10e", "Samsung S10e", 0.5f, 15999, false);
        Item applePhone = new Item("iphone", "Apple iPhone Pro Max XD Edge 5G", 0.5f, 35000, false);
        Item appleComputer = new Item("mac", "Apple Mac Pro", 10f, Integer.MAX_VALUE, false);
        Item miniture = new Item("figurka", "STAR WARS: Kylo Ren figurka", 4f, 1000, true);
        Item tshirt = new Item("tricko", "MARVEL tričko", 0.5f, 500, false);
        Item poster = new Item("plakat", "Stranger Things plakát", 0.5f, 200, false);
        
        wantedItems = new HashSet<Item>();
        wantedItems.add(milk);
        wantedItems.add(miniture);
        wantedItems.add(bookEconomy);
        wantedItems.add(bookJava);
        wantedItems.add(lightBulbs);
        wantedItems.add(dogFood);
        wantedItems.add(toiletPaper);
        wantedItems.add(pizza);
        wantedItems.add(juice);
        wantedItems.add(eggs);
        
        groceries.addItem(milk);
        groceries.addItem(eggs);
        groceries.addItem(juice);
        groceries.addItem(pizza);
        groceries.addItem(toiletPaper);
        groceries.addItem(bananas);
        petShop.addItem(fishFood);
        petShop.addItem(dogFood);
        petShop.addItem(catFood);
        electronicsShop.addItem(lightBulbs);
        electronicsShop.addItem(samsungPhone);
        electronicsShop.addItem(applePhone);
        electronicsShop.addItem(appleComputer);
        bookShop.addItem(bookJava);
        bookShop.addItem(bookEconomy);
        bookShop.addItem(bookMath);
        bookShop.addItem(bookCzech);
        bookShop.addItem(bookMaths2);
        pharmacy.addItem(protectiveFaceMask);
        comicsShop.addItem(miniture);
        comicsShop.addItem(tshirt);
        comicsShop.addItem(poster);
        
    }
    
    /**
     * Metoda do které se píše řešení úkolu z obhajoby.
     */
    private void prepareTask() {
        Item key = new Item("klic", "Klíč k portálu", 0.0f, 0, false);
        chest = new Inventory("truhla", 100f);
        chest.addItemToInventory(key);
        
        portal = new Area("portal","Zajímalo by mě kam tento portál vede, nejprve ho ale musím odemknout.", true);
        secondFloor.addExit(portal);
        portal.addExit(secondFloor);
        
        emptySpace = new Area("prazdnota", "Sem jsem se dostat fakt nechtěl. I když počkej, proč mám pocit že mám celej nákup v autě.", true);
        emptySpace.addExit(garage);
    }
    
    /**
     * Metoda, která vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     * 
     * @return aktuální lokace
     */
    public Area getCurrentArea() {
        return currentArea;
    }
    
    /**
     * Metoda nastaví aktuální lokaci, používá jí příkaz {@link CommandMove}
     * při přechodu mezi lokacemi.
     * 
     * @param area lokace, která bude nastavena jako aktuální
     */
    public void setCurrentArea(Area area) {
        currentArea = area;
        notifyObservers();
    }
    
    /**
     * @return odkaz na inventář batoh
     */
    public Inventory getBackpack() {
        return backpack;
    }
    
    /**
     * @return odkaz na inventář košík
     */
    public Inventory getShoppingCart() {
        return shoppingCart;
    }
    
    /**
     * @return odkaz na peněženku
     */
    public Wallet getWallet () {
        return wallet;
    }
    
    /**
     * @return odkaz na inventář oblečení
     */
    public Inventory getClothing() {
        return clothing;
    }
    
    
    /**
     * @return odkaz na inventář auto
     */
    public Inventory getCar() {
        return car;
    }
    
    /**
     * Metoda, který vrací Set předmětů v určité lokaci
     * 
     * @param area určitá lokace
     * @return Set předmětů v lokaci
     */
    public Set<String> itemsInCertainArea(Area area) {
        Set itemsInCertainArea = new HashSet<Item>();
        for (Item item : collectionOfAllItems) {
            if (area.containsItem(item.getName())) {
                itemsInCertainArea.add(item);
            }
        }
        return itemsInCertainArea;
    }

    /**
     * Metoda typu getter, která vrací vygenerovaný pin kód jako celé číslo.
     * 
     * @return pinCode.getPinCode() pin kód v celočíselném zápisu
     */
    public int getPinCode() {
        return pinCode.getPinCode();
    }
    
    /**
     * Metoda, která udává zdali je hra vyhrána nebo ne
     * 
     * return false pokud se ještě hraje, true pokud je už vyhráno
     */
    public boolean isVictorious() {
        return isVictorious;
    }
    
    
    /**
     * Setter na výhru
     */
    public void setVictorious() {
        isVictorious = true;
    }
    
    /**
     * Getter na truhlu
     */
    public Inventory getChest() {
        return chest;
    }
    
    /**
     * Getter na prázdnou místnost
     */
    public Area getEmptySpace() {
        return emptySpace;
    }


    @Override
    public void register(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }
}

