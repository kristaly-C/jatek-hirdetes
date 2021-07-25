package iit.unimiskolc.repository;


import iit.unimiskolc.domain.SellerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public UserRepository(JdbcTemplate jdbc){ this.jdbc = jdbc;}

    public List<SellerImpl> findAll(){
        String sql = "SELECT * FROM users";
        return jdbc.query(sql,(rs,i) ->
                new SellerImpl(
                        rs.getLong("id"),
                        rs.getString("hvname"),
                        rs.getString("email")
                ));
    }

    public SellerImpl findByID(long id){
        String sql = "SELECT * FROM users WHERE users.id = ?";
        return jdbc.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) -> {
                    return new SellerImpl(
                            rs.getLong("id"),
                            rs.getString("hvname"),
                            rs.getString("password"),
                            rs.getString("hvlink"),
                            rs.getString("email")
                    );
                }
        );

    }

    public String getPasswordByEmail(String email){
        String sql = "SELECT users.password FROM users WHERE users.email = ?";

        return  (String) jdbc.queryForObject(
                sql,
                new Object[]{email}, String.class
        );
    }

}
