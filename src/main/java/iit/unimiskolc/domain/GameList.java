package iit.unimiskolc.domain;

import iit.unimiskolc.domain.interfaces.Game;
import iit.unimiskolc.exception.EmptyListException;
import iit.unimiskolc.exception.GameIsNotStoredException;
import iit.unimiskolc.exception.GameIsStoredException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GameList {
    private HashSet<Game> storedGames;

    public GameList() {
        storedGames = new HashSet<Game>();
    }



    public void addGameToList(Game game) throws GameIsStoredException {
        if(!storedGames.isEmpty()){
            if (storedGames.contains(game)){
                throw new GameIsStoredException();
            }
        }
        storedGames.add(game);
    }



    public List<Game> getAllStored() throws EmptyListException {
        if(!storedGames.isEmpty()){
            return new ArrayList<>(storedGames);
        }
        throw new EmptyListException();
    }



    public List<String> getAllGameName() throws EmptyListException{
        if(!storedGames.isEmpty()){
        List<String> gameNames = new ArrayList<>();
            for (Game storedGame : storedGames) {
                gameNames.add(storedGame.getGameName());
            }
        return gameNames;
        }
        throw new EmptyListException();
    }

    public Game findGameByName(String gameName) throws EmptyListException, GameIsNotStoredException {
        if (storedGames.isEmpty()){ throw new EmptyListException();}
        for (Game games: storedGames) {
            if (games.getGameName().equals(gameName)){
                return games;
            }
        }
        throw new GameIsNotStoredException();
    }

    public List<Game> findGamesBySite(Site site) throws GameIsNotStoredException {
        List<Game> Goodgames = new ArrayList<>();
        for (Game games: storedGames) {
            if (games.getKeyActivationSite().equals(site)){
                Goodgames.add(games);
            }
        }
        if (Goodgames.isEmpty()){
            throw new GameIsNotStoredException();
        }
        return Goodgames;
    }

    public void CleanGameList(){
        storedGames.clear();
    }

    public int SizeOfGameList(){
        return storedGames.size();
    }

}
