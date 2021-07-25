package iit.unimiskolc.services;


import iit.unimiskolc.domain.GameImplement;
import iit.unimiskolc.domain.Site;
import iit.unimiskolc.domain.interfaces.Game;
import iit.unimiskolc.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GamesService {

    private GamesRepository gameRepo;


    @Autowired
    public void setGameRepo(GamesRepository gameRepo){
        this.gameRepo = gameRepo;
    }

    public List<GameImplement> getGames(){
        return gameRepo.findAll();
    }

    public void addNewGame( GameImplement game){
        gameRepo.addNew(game);
    }

    public GameImplement getGameByName(String name){
        return gameRepo.findByName(name);
    }

    public List<GameImplement> getAvailableGames(){
        return gameRepo.getAvailabled();
    }


}
