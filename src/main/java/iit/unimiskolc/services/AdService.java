package iit.unimiskolc.services;

import iit.unimiskolc.domain.GameAd;
import iit.unimiskolc.domain.GameImplement;
import iit.unimiskolc.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdService {

    private AdRepository adrepo;

    @Autowired
    public void setAdrepo(AdRepository adrepo) {
        this.adrepo = adrepo;
    }

    public void addNew(GameAd ad){
        adrepo.addNew(ad);
    }

    public List<GameAd> SearchByID(int ID){
        String sql = "SELECT * FROM item WHERE item.id = ";
        sql += ID;
        return adrepo.findListOf(sql);
    }

}
