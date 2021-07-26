package iit.unimiskolc.controller;


import iit.unimiskolc.domain.GameAd;
import iit.unimiskolc.domain.GameImplement;
import iit.unimiskolc.domain.Scrap;
import iit.unimiskolc.domain.SellerImpl;
import iit.unimiskolc.domain.interfaces.Game;
import iit.unimiskolc.services.AdService;
import iit.unimiskolc.services.GamesService;
import iit.unimiskolc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/picfor/{id}")
    @ResponseBody
    public String bannerImage(@PathVariable(value = "id") int id){
        return gamesService.getGameById(id).getPictureUrl();
    }

    @RequestMapping("/api/newgame/{gamename}")
    @ResponseBody
    public ResponseEntity<String> addnewGame(@PathVariable(value = "gamename") String gamename){
        if(gamesService.getGameByName(gamename) == null){
            try {
                Scrap valami = new Scrap();
                GameImplement game = valami.scrapSteamGameByName(gamename);
                gamesService.addNewGame(game);
                return ResponseEntity.status(HttpStatus.OK).body("New game added\n");
            }catch (Exception e){
                System.err.println(e);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("Már szerepel a listában\n");
    }

}
