package iit.unimiskolc.controller;


import ch.qos.logback.core.encoder.EchoEncoder;
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
@RequestMapping("/api")
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

    @RequestMapping("/games")
    public List<GameImplement> games(){
        return gamesService.getGames();
    }

    @RequestMapping("/adID/{id}")
    public List<GameAd> addGame(@PathVariable(value = "id") long id){
        return adService.SearchByID(id);
    }
    @RequestMapping("/name/{title}")
    public GameImplement FindGameByName(@PathVariable(value = "title") String title){
        return gamesService.getGameByName(title);

    }

    @GetMapping("/picfor/{id}")
    @ResponseBody
    public String bannerImage(@PathVariable(value = "id") int id){
        return gamesService.getGameById(id).getPictureUrl();
    }

    @RequestMapping("/deleteAd/{adId}")
    public String deleteAdFromList(@PathVariable(value = "adId") long id){if(adService.deleteAd(id)){return "Deleted";}else{return"Error";}}

    @RequestMapping("/newgame/{gamename}")
    @ResponseBody
    public ResponseEntity<String> addnewGame(@PathVariable(value = "gamename") String gamename){
        boolean Okay = false;
        try{
            Scrap scrapper = new Scrap();
            GameImplement game = scrapper.scrapSteamGameByName(gamename);
            if(gamesService.getGameByName(game.getGameName()) == null){
                gamesService.addNewGame(game);
                Okay = true;
            }
        }catch (Exception e){
            System.err.println(e);
        }


        if (Okay){
            return ResponseEntity.status(HttpStatus.CREATED).body("New game added\n");
        }
        return ResponseEntity.status(HttpStatus.OK).body("The game is found the collection\n");
    }

    @RequestMapping("/getUserByEmail/{email}")
    public String userByEmail(@PathVariable(value = "email") String email){
        if (userService.findByEmail(email) == null){
            return "Null";
        }
        return userService.findByEmail(email).toString();
    }
}
