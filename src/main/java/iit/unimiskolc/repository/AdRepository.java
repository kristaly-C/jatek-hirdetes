package iit.unimiskolc.repository;


import iit.unimiskolc.domain.GameAd;
import iit.unimiskolc.domain.GameImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AdRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public AdRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<GameAd> findAll(){
        String sql = "SELECT * FROM item";
        return jdbc.query(sql,(rs,i)->
                new GameAd(
                        rs.getInt("id"),
                        rs.getInt("game"),
                        rs.getFloat("price"),
                        rs.getInt("usser"),
                        rs.getTimestamp("adCreated").toLocalDateTime(),
                        rs.getTimestamp("adModified").toLocalDateTime(),
                        rs.getInt("status")
                )
        );
    }

    public void addNew(GameAd ad){
        jdbc.update("INSERT INTO item (usser,game,price,quantity,adCreated, adModified,status) VALUES (?,?,?,?,?,?,?)",
                ad.getSeller(),ad.getGameID(),ad.getPrice(),1,ad.getAdCreated(),ad.getLastModified(),ad.getStatus());
    }

    public List<GameAd> findListOf(String sql){
        return jdbc.query(sql,(rs,i)->
                new GameAd(
                        rs.getInt("id"),
                        rs.getInt("game"),
                        rs.getFloat("price"),
                        rs.getInt("usser"),
                        rs.getTimestamp("adCreated").toLocalDateTime(),
                        rs.getTimestamp("adModified").toLocalDateTime(),
                        rs.getInt("status")
                )
        );
    }
}
