package iit.unimiskolc.controller;

import iit.unimiskolc.domain.*;
import iit.unimiskolc.exception.EmptyListException;
import iit.unimiskolc.services.AdService;
import iit.unimiskolc.services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    private GamesService gamesService;
    private AdService adServ;


    @Autowired
    public void setAdService(AdService adService){this.adServ = adService;}
    @Autowired
    public void setGamesService(GamesService gamesService){
        this.gamesService = gamesService;
    }

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("pagetitle","olcsojatekok");
        model.addAttribute("games",getAvailableGameList());
        return "newindex";
    }


    @GetMapping("/addAd")
    public String newAd(Model model){
        model.addAttribute("allgame",allListedGame());
        model.addAttribute("ad", new GameAd());
        return "newAd";
    }

    @PostMapping("/addAd")
    public String addNewAd(@ModelAttribute GameAd ad, Model model){
        int SellerTest = 1;         //TEST
        try {
            adServ.addNew(new GameAd(ad.getGameID(),ad.getPrice(),SellerTest));
        }catch (Exception e)
        {
            System.err.println(e);
        }
        model.addAttribute("allgame",allListedGame());
        model.addAttribute("ad", new GameAd());
        return "newAd";
    }

    @RequestMapping("/game/{gameName}")
    public String selectedAds(@PathVariable(value = "gameName") String gameName,Model model){
        model.addAttribute("game",selectedGame(gameName));
        model.addAttribute("ads", selectedAds(gameName));
        return "listOfAds";
    }


    private GameImplement selectedGame(String game){
        return gamesService.getGameByName(game);
    }

    private List<Ad> selectedAds(String game){
        long gameID = gamesService.getGameByName(game).getId();
        return adServ.ListableAd(gameID);
    }

    private List<GameImplement> allListedGame(){
        return gamesService.getGames();
    }
    private List<GameImplement> getAvailableGameList(){
        return gamesService.getAvailableGames();
    }
}
