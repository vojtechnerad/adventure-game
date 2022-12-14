package cz.vse.java.nerv01.adventura.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testovací třída {@code AreaTest} slouží ke komplexnímu otestování
 * třídy {@link AreaTest}.
 *
 * @author  Vojtěch Nerad
 * @version 1.0.0
 */
public class AreaTest {
    @Test
    public void testPassThrough() {
        Area garage = new Area("garaz","Svoje auto si zaparkoval na pozici J7, nanos do něj nákup a pak rychle odjeď .", true);
        Area lobby = new Area(lobby(),"Nejnacpanější obchodní centrum, kam si mohl zajet. Rychle běž pro nákup .", true);
        Area groceries = new Area("potraviny","PŘIDAT POPISEK PRO GARÁŽ", false);
        Area atm = new Area("bankomat","Bankomat banky, kterou i vy můžete mít rádi. Zde můžeš použít příkaz pro výběr peněz.", true);
        Area secondFloor = new Area(secondFloor(),"Kolem jsou tuny obchodů .", true);
        Area pharmacy = new Area("lekarna","Dezinfekce a roušky jsou skoro vyprodaný .", true);
        Area petShop = new Area("zverimex","Smrdí to tu po psích granulích. Tuším že jsem chtěl vzít i něco pro psa. ", false);
        Area bookShop = new Area("knihkupectvi","Nejradši bych tu nic nekupoval, ale tyhle učebnice nejdou stáhnout z netu .", false);
        Area comicsShop = new Area("comicspoint","Všechno tady vypadá epicky, škoda že si nemůžu koupit všechno .", false);
        Area electronicsShop = new Area("elektro","PŘIDAT POPISEK PRO GARÁŽ", false);
        Area portal = new Area("portal","Zajímalo by mě kam tento portál vede, nejprve ho ale musím odemknout.", true);
        Area emptySpace = new Area("prazdnota", "Sem jsem se dostat fakt nechtěl.", true);
        

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
        
        secondFloor.addExit(portal);
        portal.addExit(secondFloor);
        emptySpace.addExit(garage);
        
        assertEquals(garage.getName(), lobby.getExitArea("garaz").getName());
        assertEquals(lobby.getName(), garage.getExitArea(lobby()).getName());
        assertEquals(lobby.getName(), groceries.getExitArea(lobby()).getName());
        assertEquals(lobby.getName(), atm.getExitArea(lobby()).getName());
        assertEquals(lobby.getName(), secondFloor.getExitArea(lobby()).getName());
        assertEquals(atm.getName(), lobby.getExitArea("bankomat").getName());
        assertEquals(groceries.getName(), lobby.getExitArea("potraviny").getName());
        assertEquals(secondFloor.getName(), lobby.getExitArea(secondFloor()).getName());
        assertEquals(secondFloor.getName(), pharmacy.getExitArea(secondFloor()).getName());
        assertEquals(secondFloor.getName(), petShop.getExitArea(secondFloor()).getName());
        assertEquals(secondFloor.getName(), bookShop.getExitArea(secondFloor()).getName());
        assertEquals(secondFloor.getName(), comicsShop.getExitArea(secondFloor()).getName());
        assertEquals(secondFloor.getName(), electronicsShop.getExitArea(secondFloor()).getName());
        assertEquals(pharmacy.getName(), secondFloor.getExitArea("lekarna").getName());
        assertEquals(petShop.getName(),  secondFloor.getExitArea("zverimex").getName());
        assertEquals(bookShop.getName(), secondFloor.getExitArea("knihkupectvi").getName());
        assertEquals(comicsShop.getName(), secondFloor.getExitArea("comicspoint").getName());
        assertEquals(electronicsShop.getName(), secondFloor.getExitArea("elektro").getName());
        assertEquals(secondFloor.getName(), portal.getExitArea(secondFloor()).getName());
        assertEquals(portal.getName(), secondFloor.getExitArea("portal").getName());
        assertEquals(garage.getName(), emptySpace.getExitArea("garaz").getName());
    }

    private String lobby() {
        return "vstupni_hala";
    }
    
    private String secondFloor() {
        return "patro2";
    }
}
