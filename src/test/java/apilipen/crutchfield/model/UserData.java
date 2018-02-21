package apilipen.crutchfield.model;

public class UserData {

    private String emailAddress;
    private String passToken;





    public String getEmailAddress() {
        return emailAddress;
    }
    public String getPassToken() {
        return passToken;
    }



    public UserData withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    public UserData withPassToken(String passToken) {
        this.passToken = passToken;
        return this;
    }

}
