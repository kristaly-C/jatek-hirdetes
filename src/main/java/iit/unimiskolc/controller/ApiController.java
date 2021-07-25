package iit.unimiskolc.controller;


import iit.unimiskolc.domain.GameAd;
import iit.unimiskolc.domain.GameImplement;
import iit.unimiskolc.domain.SellerImpl;
import iit.unimiskolc.domain.interfaces.Game;
import iit.unimiskolc.services.AdService;
import iit.unimiskolc.services.GamesService;
import iit.unimiskolc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    private GamesService gamesService;
    private UserService userService;
    private AdService adService;

    @Autowired
    public void setAdService(AdService adService){this.adService = adService;}

    @Autowired
    public void setGamesService(GamesService gamesService){
        this.gamesService = gamesService;
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/api/games")
    public List<GameImplement> games(){
        return gamesService.getGames();
    }

    @RequestMapping("/api/adID/{id}")
    public List<GameAd> addGame(@PathVariable(value = "id") int id){
        return adService.SearchByID(id);
    }
    @RequestMapping("/api/name/{title}")
    public GameImplement FindGameByName(@PathVariable(value = "title") String title){
        return gamesService.getGameByName(title);

    }

}
