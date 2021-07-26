package iit.unimiskolc.controller;

import iit.unimiskolc.domain.*;
import iit.unimiskolc.services.AdService;
import iit.unimiskolc.services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/addgame")
    public String addnewgame(Model model){
        model.addAttribute("game", new GameImplement());
        return "addgame";
    }


    @PostMapping("/newGame")
    public String newGame(@ModelAttribute GameImplement game, Model model){
        try {
            System.out.println("Új jatek hozzaadva");
            Scrap scrapper = new Scrap();
            game = scrapper.scrapSteamGameByURL(game.getUrl());
            gamesService.addNewGame(game);
        }catch (Exception e){
            System.err.println(e);
        }
        model.addAttribute("pagetitle","olcsojatekok");
        model.addAttribute("gameads",getAvailableGameList());
        return "index";
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

    private List<GameImplement> allListedGame(){
        return gamesService.getGames();
    }
    private List<GameImplement> getAvailableGameList(){
        return gamesService.getAvailableGames();
    }
}
