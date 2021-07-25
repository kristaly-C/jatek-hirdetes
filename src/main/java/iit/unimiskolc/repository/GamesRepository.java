package iit.unimiskolc.repository;


import iit.unimiskolc.domain.GameImplement;
import iit.unimiskolc.domain.interfaces.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GamesRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public GamesRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public List<GameImplement> findAll(){
        String sql = "SELECT * FROM games";
        return jdbc.query(sql,(rs,i)->
                    new GameImplement(
                            rs.getInt("id"),
                            rs.getString("storepage"),
                            rs.getString("picture"),
                            rs.getString("name"),
                            rs.getInt("siteID")
                    )
                );
    }

    public void addNew(GameImplement game){
        jdbc.update("INSERT INTO games (name,picture,storepage,siteID) VALUES (?,?,?,?)",
                game.getGameName(),game.getPictureUrl(),game.getUrl(),game.siteToInt());
    }

    public GameImplement findByName(String name){
        String sql = "SELECT * FROM games WHERE games.name = ?";
        return jdbc.queryForObject(
                sql,
                new Object[]{name},
                (rs, rowNum) -> {
                    GameImplement game = new GameImplement(
                            rs.getInt("id"),
                            rs.getString("storepage"),
                            rs.getString("picture"),
                            rs.getString("name"),
                            rs.getInt("siteID")
                    );
                    return game;
                }
        );
    }

    public List<GameImplement> getAvailabled(){
        String sql = "SELECT games.id,games.name,games.picture,games.storepage,games.siteID FROM games INNER JOIN item ON item.game = games.id GROUP BY games.name";
        return jdbc.query(sql,(rs,i)->
                new GameImplement(
                        rs.getInt("id"),
                        rs.getString("storepage"),
                        rs.getString("picture"),
                        rs.getString("name"),
                        rs.getInt("siteID")
                )
        );
    }

}
