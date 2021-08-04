package iit.unimiskolc.domain;

import iit.unimiskolc.domain.interfaces.MailService;
import iit.unimiskolc.domain.interfaces.Seller;

public class SellerImpl implements Seller {

    private long ID;
    private String name;
    private String passwd;
    private String hardveraproUrl;
    private String email;


    public SellerImpl(long ID, String name, String passwd, String hardveraproUrl, String email) {
        this.ID = ID;
        this.name = name;
        this.passwd = passwd;
        this.hardveraproUrl = hardveraproUrl;
        this.email = email;
    }

    public SellerImpl(String email,String passwd){
        this.email = email;
        this.passwd = passwd;
    }
    public SellerImpl(long id, String username,String email){
        this.ID=id;
        this.name=username;
        this.email =email;
    }

    public SellerImpl(long id, String username, String url, String email){
        this.ID=id;
        this.name=username;
        this.hardveraproUrl= url;
        this.email =email;
    }


    public SellerImpl(long id){
        this.ID = id;
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
                ", passwd='" + passwd + '\'' +
                ", hardveraproUrl='" + hardveraproUrl + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
