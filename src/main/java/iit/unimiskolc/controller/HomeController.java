package iit.unimiskolc.controller;

import iit.unimiskolc.domain.*;
import iit.unimiskolc.exception.EmptyListException;
import iit.unimiskolc.services.AdService;
import iit.unimiskolc.services.GamesService;
import iit.unimiskolc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private GamesService gamesService;
    private AdService adServ;
    private UserService userServ;


    @Autowired
    public void setAdService(AdService adService){this.adServ = adService;}
    @Autowired
    public void setGamesService(GamesService gamesService){
        this.gamesService = gamesService;
    }
    @Autowired
    public void setUserServ(UserService userServ){this.userServ = userServ;}

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
    public String addNewAd(@ModelAttribute GameAd ad, Model model, @AuthenticationPrincipal UserDetailsImp user){
        try {
            adServ.addNew(new GameAd(ad.getGameID(),ad.getPrice(),user.getprifID()));
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

    @RequestMapping("/registration")
    public String registration(Model model){
        model.addAttribute("SellerImpl", new SellerImpl());
        return "registration";
    }


    @PostMapping("/reg")
    public String greetingSubmit(@ModelAttribute SellerImpl user, Model model) {
        if(userServ.registerUser(user)){
            System.out.println("UJ USER");
            return "auth/login";
        }else{
            model.addAttribute("SellerImpl", new SellerImpl());
            return "registration";
        }

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
