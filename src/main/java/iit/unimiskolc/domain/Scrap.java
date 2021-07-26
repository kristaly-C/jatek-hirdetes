package iit.unimiskolc.domain;

import iit.unimiskolc.domain.interfaces.Game;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrap {

    Document doc;

    public GameImplement scrapSteamGameByURL(String url){
        GameImplement game = new GameImplement(url,scrapPicture(url),scrapGameName(url),
                1);
        return game;
    }

    public GameImplement scrapSteamGameByName(String name){
        String searchUrl = getGameSearchName(name);
        String url = getSiteUrl(searchUrl);
        try {
            GameImplement game = new GameImplement(url,scrapPicture(url),scrapGameName(url),
                    1);
            return game;
        }catch (Exception e){
            System.err.println(e);
        }
        return null;
    }


    public String scrapPicture(String url) {
        try {
            doc = Jsoup.connect(url).get().normalise();
            //KEP link megszerzese
            Elements content = doc.getElementsByClass("game_header_image_full");
            if (!content.isEmpty()) {
                return content.get(0).attr("src");
            } else {
                content = doc.getElementsByClass("img_ctn");
                if (!content.isEmpty()) {
                    Elements png = content.select("img");
                    return png.attr("src");
                } else {
                    throw new Exception("Nem talalhato");
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public String scrapGameName(String url){
        try {
            doc = Jsoup.connect(url).get().normalise();
            //JATEKNEV megszerzese
            String[] namearr = url.split("/");
            String GameName = namearr[namearr.length-2];
            GameName = GameName.replaceAll("_"," ");
            return GameName;
        }catch (Exception e){
            System.err.println(e);
        }
        return null;
    }


    public void Hardverapro(String url) {
        try {
            doc = Jsoup.connect(url).get().normalise();
            Elements content = doc.getElementsByClass("uad-content");
            System.out.print(content.get(0).text());
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public String getGameSearchName(String gameName){
        String urlBasic = "https://store.steampowered.com/search/?term=";
        String searchstylename = gameName.replaceAll(" ","+");
        urlBasic = urlBasic.concat(searchstylename);
        return urlBasic;
    }

    public String getSiteUrl(String url){
        try {
            doc = Jsoup.connect(url).get().normalise();
            //KEP link megszerzese
            Element content = doc.getElementById("search_resultsRows");
            Elements childrens = content.children();
            if (!childrens.isEmpty()){
                return childrens.first().attr("href");
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }
}
