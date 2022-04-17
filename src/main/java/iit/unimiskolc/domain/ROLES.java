package iit.unimiskolc.domain;

public enum ROLES {
    ADMIN(1),
    MODERATOR(2),
    USER(3);

    public final int roleNum;

    private ROLES(int roleNum){
        this.roleNum = roleNum;
    }
}
