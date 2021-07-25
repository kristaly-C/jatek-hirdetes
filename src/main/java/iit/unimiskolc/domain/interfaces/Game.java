package iit.unimiskolc.domain.interfaces;

import iit.unimiskolc.domain.Site;

public interface Game {

    public String getUrl();
    public String getPictureUrl();
    public String getGameName();
    public Site getKeyActivationSite();
    public String toString();
}
