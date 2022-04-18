package iit.unimiskolc.domain;

import iit.unimiskolc.domain.interfaces.Game;
import iit.unimiskolc.domain.interfaces.Seller;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

public class GameAd {
    private int id = 0;
    private int gameID;
    private float price;
    private long seller;
    private LocalDateTime adCreated;
    private LocalDateTime lastModified;
    private int status;

    public GameAd(){}

    public GameAd(int id,int gameid, float price, long seller, LocalDateTime adCreated
                  ,LocalDateTime lastMod, int status)
    {
        this.id = id;
        this.gameID = gameid;
        this.price = price;
        this.seller = seller;
        this.adCreated = adCreated;
        this.lastModified = lastMod;
        this.status = status;
    }

    public GameAd(int gameId, float price, long seller) {
        this.gameID = gameId;
        this.price = price;
        this.seller = seller;
        this.adCreated = LocalDateTime.now();
        this.lastModified = this.adCreated;
    }

    public GameAd(int ID,int gameId, float price, int status) {
        this.id = ID;
        this.gameID = gameId;
        this.price = price;
        this.status = status;
    }

    public int getGameID() {
        return gameID;
    }

    public int getId(){return id; }

    public float getPrice() {
        return price;
    }

    public String getPriceWithCurrency(String currency)
    {
        DecimalFormat df = new DecimalFormat("0.00");

        switch (currency){
            case "HUF" : int pricehuf = (int) price;
                return pricehuf + " Ft";
            case "USD" : float priceusd = price / 280;
                return df.format(priceusd) + " $";
            default: return String.valueOf(price);
        }


    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
        this.lastModified = LocalDateTime.now();
    }

    public long getSeller() {
        return seller;
    }

    public LocalDateTime getAdCreated() {
        return adCreated;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
        this.lastModified = LocalDateTime.now();
    }

    public void setPrice(float price) {
        this.price = price;
        this.lastModified = LocalDateTime.now();
    }

    public void setSeller(long seller) {
        this.seller = seller;
        this.lastModified = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "GameAd{" +
                "id=" + id +
                ", gameID=" + gameID +
                ", price=" + price +
                ", seller=" + seller +
                ", adCreated=" + adCreated +
                ", lastModified=" + lastModified +
                ", status=" + status +
                '}';
    }
}
