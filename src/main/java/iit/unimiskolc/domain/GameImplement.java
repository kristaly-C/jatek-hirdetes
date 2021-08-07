package iit.unimiskolc.domain;

import iit.unimiskolc.domain.interfaces.Game;

public class GameImplement implements Game {
    private int id;
    private String url;
    private String pictureUrl;
    private String gameName;
    private Site keyActivationSite;

    public GameImplement(){ }

    public  GameImplement(int id){
        this.id = id;
    }

    public GameImplement( String url, String pictureUrl, String gameName, int keyActivationSite) {
        this.url = url;
        this.pictureUrl = pictureUrl;
        this.gameName = gameName;
        this.keyActivationSite = codeToSite(keyActivationSite);
    }
    public GameImplement(int id, String url, String pictureUrl, String gameName, int keyActivationSite) {
        this.id = id;
        this.url = url;
        this.pictureUrl = pictureUrl;
        this.gameName = gameName;
        this.keyActivationSite = codeToSite(keyActivationSite);
    }

    public Site codeToSite(int keyActivationSite) {
        switch (keyActivationSite){
            case 1 : return Site.STEAM;
            case 2 : return Site.EPIC_GAMES;
            case 3 : return Site.ORIGIN;
            case 4 : return Site.GOG;
            case 5 : return Site.UPLAY;
            case 6 : return Site.BATTLENET;
            default: return Site.NOTDEFINIED;
        }
    }

    public int siteToInt(){
        switch (keyActivationSite){
            case STEAM:         return 1;
            case EPIC_GAMES:    return 2;
            case ORIGIN:        return 3;
            case UPLAY:         return 4;
            case BATTLENET:     return 5;
            default:            return 6;
        }
    }

    @Override
    public String getUrl() {
        return url;
    }

    public long getId(){return id;}


    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    @Override
    public Site getKeyActivationSite() {
        return keyActivationSite;
    }

    public void setKeyActivationSite(Site keyActivationSite) {
        this.keyActivationSite = keyActivationSite;
    }

    @Override
    public String toString() {
        return "GameImplement{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", gameName='" + gameName + '\'' +
                ", keyActivationSite=" + keyActivationSite +
                '}';
    }

}
