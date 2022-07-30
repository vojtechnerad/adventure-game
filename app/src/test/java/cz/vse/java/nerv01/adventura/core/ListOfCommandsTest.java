
package cz.vse.java.nerv01.adventura.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testovací třída {@code ListOfCommandsTest} slouží ke komplexnímu otestování
 * třídy {@link ListOfCommandsTest}.
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class ListOfCommandsTest {
    private CommandTerminate commTerm;
    private CommandMove commMove;
    private CommandWithdraw commWthdr;
    private CommandContent commCont;
    private CommandPick commPick;
    private CommandHelp commHelp;
    private CommandLoad commLoad;
    private CommandPay commPay;
    private CommandPutOn commPutOn;
    private ListOfCommands list;

    
    /**
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @BeforeEach
    public void setUp() {
        Game game = new Game();
        list = new ListOfCommands();

        commTerm = new CommandTerminate(game);
        commMove = new CommandMove(game.getGamePlan());
        commWthdr = new CommandWithdraw(game.getGamePlan());
        commCont = new CommandContent(game.getGamePlan());
        commPick = new CommandPick(game.getGamePlan());
        commHelp = new CommandHelp(list);
        commLoad = new CommandLoad(game.getGamePlan());
        commPay = new CommandPay(game.getGamePlan());
        commPutOn = new CommandPutOn(game.getGamePlan());
        
        
    }
    
    @Test
    public void testAddCommands() {
        list.addCommand(commTerm);
        list.addCommand(commMove);
        list.addCommand(commWthdr);
        list.addCommand(commCont);
        list.addCommand(commPick);
        list.addCommand(commHelp);
        list.addCommand(commLoad);
        list.addCommand(commPay);
        list.addCommand(commPutOn);
        
        assertEquals(commTerm, list.getCommand("konec"));
        assertEquals(commMove, list.getCommand("jdi"));
        assertEquals(commWthdr, list.getCommand("vyber_penize"));
        assertEquals(commCont, list.getCommand("obsah"));
        assertEquals(commPick, list.getCommand("vloz"));
        assertEquals(commHelp, list.getCommand("pomoc"));
        assertEquals(commLoad, list.getCommand("naloz"));
        assertEquals(commPay, list.getCommand("zaplat"));
        assertEquals(commPutOn, list.getCommand("oblec"));
    }
    
    @Test
    public void testIsCommandValid() {
        list.addCommand(commTerm);
        list.addCommand(commMove);
        list.addCommand(commWthdr);
        list.addCommand(commCont);
        list.addCommand(commPick);
        list.addCommand(commHelp);
        list.addCommand(commLoad);
        list.addCommand(commPay);
        list.addCommand(commPutOn);
        
        assertEquals(true, list.checkCommand("konec"));
        assertEquals(true, list.checkCommand("jdi"));
        assertEquals(true, list.checkCommand("vyber_penize"));
        assertEquals(true, list.checkCommand("obsah"));
        assertEquals(true, list.checkCommand("vloz"));
        assertEquals(true, list.checkCommand("pomoc"));
        assertEquals(true, list.checkCommand("naloz"));
        assertEquals(true, list.checkCommand("zaplat"));
        assertEquals(true, list.checkCommand("oblec"));
        
    }
    
    @Test
    public void testNamesOfCommands() {
        list.addCommand(commTerm);
        list.addCommand(commMove);
        list.addCommand(commWthdr);
        list.addCommand(commCont);
        list.addCommand(commPick);
        list.addCommand(commHelp);
        list.addCommand(commLoad);
        list.addCommand(commPay);
        list.addCommand(commPutOn);
        
        String completeList = list.getNames();
        assertEquals(true, completeList.contains("konec"));
        assertEquals(true, completeList.contains("jdi"));
        assertEquals(true, completeList.contains("vyber_penize"));
        assertEquals(true, completeList.contains("obsah"));
        assertEquals(true, completeList.contains("vloz"));
        assertEquals(true, completeList.contains("pomoc"));
        assertEquals(true, completeList.contains("naloz"));
        assertEquals(true, completeList.contains("zaplat"));
        assertEquals(true, completeList.contains("oblec"));
    }
}
