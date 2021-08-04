package iit.unimiskolc.domain;

public class Ad {
    private long userId;
    private String userName;
    private String url;
    private int adId;
    private float price;

    public Ad(long userId, String userName, String url, int adId, float price) {
        this.userId = userId;
        this.userName = userName;
        this.url = url;
        this.adId = adId;
        this.price = price;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUrl() {
        return url;
    }

    public int getAdId() {
        return adId;
    }

    public float getPrice() {
        return price;
    }
}
