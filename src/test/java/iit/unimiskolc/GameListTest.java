package iit.unimiskolc;

import iit.unimiskolc.domain.GameImplement;
import iit.unimiskolc.domain.GameList;
import iit.unimiskolc.domain.Site;
import iit.unimiskolc.domain.interfaces.Game;
import iit.unimiskolc.exception.EmptyListException;
import iit.unimiskolc.exception.GameIsNotStoredException;
import iit.unimiskolc.exception.GameIsStoredException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class GameListTest {

    GameList jateklista = new GameList();


    @Test
    @DisplayName("Új játék a listához adása")
    void addGameToList() throws GameIsStoredException {
        Game jatek = new GameImplement("url","kepurl","jatek", 1);
        jateklista.addGameToList(jatek);
        assertEquals(1,jateklista.SizeOfGameList());

    }

    @Test
    void addDuplicatedGameToList() throws GameIsStoredException {
        Game jatek = new GameImplement("url","kepurl","jatek",1);
        jateklista.addGameToList(jatek);
        Assertions.assertThrows(GameIsStoredException.class, () -> jateklista.addGameToList(jatek));
    }


    @Test
    void uresLista() throws GameIsStoredException {
        Game jatek = new GameImplement("url","kepurl","jatek",1);
        jateklista.addGameToList(jatek);
        jateklista.CleanGameList();
        assertEquals(0,jateklista.SizeOfGameList());
    }

    @Test
    @DisplayName("Minden jatek adata")
    void getAllStored() throws GameIsStoredException, EmptyListException {
        Game jatek = new GameImplement("url","kepurl","jatek",1);
        Game jatek2 = new GameImplement("url2","kepurl2","jatek2",2);
        jateklista.addGameToList(jatek);
        jateklista.addGameToList(jatek2);
        assertTrue(jateklista.getAllStored().contains(jatek2));
        assertTrue(jateklista.getAllStored().contains(jatek));
    }

    @Test
    void getAllGameName() throws GameIsStoredException, EmptyListException {
        Game jatek = new GameImplement("url","kepurl","jatek",1);
        Game jatek2 = new GameImplement("url","kepurl","jatek2",2);
        Game jatek3 = new GameImplement("url","kepurl","jatek3",4);
        jateklista.addGameToList(jatek);
        jateklista.addGameToList(jatek2);
        jateklista.addGameToList(jatek3);
        System.out.println(jateklista.getAllGameName());
        assertTrue(jateklista.getAllGameName().contains(jatek.getGameName()));
        assertTrue(jateklista.getAllGameName().contains(jatek2.getGameName()));
        assertTrue(jateklista.getAllGameName().contains(jatek3.getGameName()));
    }

    @Test
    void findGameByName() throws GameIsStoredException, GameIsNotStoredException, EmptyListException {
        Game jatek = new GameImplement("url","kepurl","jatek",4);
        Game jatek2 = new GameImplement("url","kepurl","jatk",5);
        Game jatek3 = new GameImplement("url","kepurl","jtek",5);
        jateklista.addGameToList(jatek);
        jateklista.addGameToList(jatek2);
        jateklista.addGameToList(jatek3);
        assertEquals(jatek,jateklista.findGameByName("jatek"));
    }

    @Test
    void findGamesBySite() throws GameIsStoredException, GameIsNotStoredException {
        Game jatek = new GameImplement("url","kepurl","jatek",1);
        jateklista.addGameToList(jatek);
        assertTrue(jateklista.findGamesBySite(Site.STEAM).contains(jatek));
    }
    @Test
    void findGamesBySiteEmptyList() throws GameIsStoredException, GameIsNotStoredException {
        Game jatek = new GameImplement("url","kepurl","jatek",1);
        jateklista.addGameToList(jatek);
        jateklista.CleanGameList();
        Assertions.assertThrows(GameIsNotStoredException.class, () -> jateklista.findGamesBySite(Site.STEAM));
    }
}