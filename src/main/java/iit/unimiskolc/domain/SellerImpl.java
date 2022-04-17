package iit.unimiskolc.domain;

import com.google.common.hash.Hashing;
import iit.unimiskolc.domain.interfaces.MailService;
import iit.unimiskolc.domain.interfaces.Seller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;


public class SellerImpl implements Seller {

    private long ID;
    private String name;
    private String password;
    private String hardveraproUrl;
    private String email;
    private Set<ROLES>  roles = new HashSet<>();


    public SellerImpl(long ID, String name, String passwd, String hardveraproUrl, String email) {
        this.ID = ID;
        this.name = name;
        this.password = passwd;
        this.hardveraproUrl = hardveraproUrl;
        this.email = email;
        this.roles.add(ROLES.USER);
    }

    public SellerImpl(String email,String passwd){
        this.email = email;
        this.password = passwd;
        this.roles.add(ROLES.USER);
    }
    public SellerImpl(long id, String username,String email){
        this.ID=id;
        this.name=username;
        this.email =email;
        this.roles.add(ROLES.USER);
    }

    public SellerImpl(long id, String username, String url, String email){
        this.ID=id;
        this.name=username;
        this.hardveraproUrl= url;
        this.email =email;
        this.roles.add(ROLES.USER);
    }


    public SellerImpl(long id){
        this.ID = id;
        this.roles.add(ROLES.USER);
    }

    public SellerImpl(){this.roles.add(ROLES.USER);}




    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder bcrypto = new BCryptPasswordEncoder();
        this.password = bcrypto.encode(password);
                /*Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

                 */
    }

    public String getHardveraproUrl() {
        return hardveraproUrl;
    }

    public void setHardveraproUrl(String hardveraproUrl) {
        String addressBase = "https://hardverapro.hu/tag/";
        String addressEnd = ".html";
        this.hardveraproUrl = addressBase + hardveraproUrl + addressEnd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ROLES> getRoles() {
        return roles;
    }

    public void setRoles(Set<ROLES> roles) {
        this.roles = roles;
    }

    private String generatePassword(int lenght){
        int max;
        int min;
        int range;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            int random = (int) (Math.random() * 3);
            switch (random){
                case 0 : max = 90; min=65;
                    break;
                case 1: max = 57; min = 48;
                    break;
                case 2: max = 122; min = 97;
                    break;
                default: max = 122; min = 97;
            }
            range = max - min + 1;
            int rand = (int) (Math.random() * range) + min;
            sb.append(Character.toString((char)rand));
        }
        return sb.toString();
    }


    @Override
    public void changePassword(int lenght, MailService mail) {
        mail.sendPass(this.email,generatePassword(lenght));
    }

    @Override
    public String toString() {
        return "SellerImpl{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", passwd='" + password + '\'' +
                ", hardveraproUrl='" + hardveraproUrl + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
