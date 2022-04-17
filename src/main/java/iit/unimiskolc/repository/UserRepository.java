package iit.unimiskolc.repository;


import iit.unimiskolc.domain.ROLES;
import iit.unimiskolc.domain.SellerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

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
                        rs.getString("name"),
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
                            rs.getString("name"),
                            rs.getString("password"),
                            rs.getString("hvlink"),
                            rs.getString("email")
                    );
                }
        );

    }

    public SellerImpl findByEmail(String email){
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            return jdbc.queryForObject(
                    sql,
                    new Object[]{email},
                    (rs, rowNum) -> {
                        SellerImpl user = new SellerImpl(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("password"),
                                rs.getString("hvlink"),
                                rs.getString("email")
                        );
                        return user;
                    }
                    );
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }


    public Long getIdByEmail(String email){
        try {
            String sql = "SELECT id FROM users WHERE email = ?";
            return jdbc.queryForObject(sql, new Object[]{email}, Long.class);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return (long)-1;
    }

    public String getPasswordByEmail(String email){
        String sql = "SELECT users.password FROM users WHERE users.email = ?";

        return  (String) jdbc.queryForObject(
                sql,
                new Object[]{email}, String.class
        );
    }

    public void addRoleToUser(Long userID,ROLES role){
        String sql = "SELECT count(*) FROM userroles WHERE userID = ? AND roleID = ?";
        int rows = jdbc.queryForObject(sql, new Object[]{userID,role.roleNum}, int.class);
        if (rows == 0){
            sql = "INSERT INTO userroles (userid,roleid) VALUES (?,?)";
            jdbc.update(sql,userID,role.roleNum);
        }
    }

    public boolean registerNewUser(SellerImpl user){
        try {
            jdbc.update("INSERT INTO users (name,hvlink,email,password) VALUES(?,?,?,?)",
                    user.getName(),user.getHardveraproUrl(),user.getEmail(),user.getPassword());
            Long id = getIdByEmail(user.getEmail());
            Set<ROLES> roles = user.getRoles();
            for (ROLES role: roles) {
                addRoleToUser(id,role);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
