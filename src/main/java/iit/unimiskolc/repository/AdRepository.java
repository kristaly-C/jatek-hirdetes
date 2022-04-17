package iit.unimiskolc.repository;


import iit.unimiskolc.domain.Ad;
import iit.unimiskolc.domain.GameAd;
import iit.unimiskolc.domain.GameImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
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
                (int)ad.getSeller(),ad.getGameID(),ad.getPrice(),1,ad.getAdCreated(),ad.getLastModified(),ad.getStatus());
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

    public List<Ad> pageAbleAds(long gameID) {
        String sql = "SELECT item.id AdID,item.price AdPrice,users.id UserID,users.name UserName,users.hvlink UserLink FROM `item` INNER JOIN users ON item.usser = users.id WHERE item.game = ";
        sql += String.valueOf(gameID);
        try {
            return jdbc.query(sql, (rs, i) ->
                    new Ad(
                            rs.getLong("UserID"),
                            rs.getString("UserName"),
                            rs.getString("UserLink"),
                            rs.getInt("AdID"),
                            rs.getFloat("AdPrice")));
        } catch (Exception e) {
            System.err.println(e);
        }
        return null;
    }

    public Boolean deleteAd(long gameID){
        String sql = "DELETE FROM item WHERE item.id = ?";
        try{
            jdbc.update(sql,gameID);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
