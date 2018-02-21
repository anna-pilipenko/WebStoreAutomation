package apilipen.crutchfield.model;

public class PaymentData {

    private String cardType ;
    private String cardNumber ;
    private String expMonth ;
    private String expYear ;
    private String secCod ;
    private String useremail ;
    private String expData ;



    public String getCardType() {
        return cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpData(){return expData;}

    public String getExpMonth() {
        return expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public String getSecCod() {
        return secCod;
    }

    public String getUseremail() {
        return useremail;
    }


    //    setters

    public PaymentData withCardType(String cardType) {
        this.cardType = cardType;

        return this;
    }

    public PaymentData withCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public PaymentData withExpData(String expData) {
        this.expData = expData;
        return this;
    }

//    public PaymentData withExpMonth(String expMonth) {
//        this.expMonth = expMonth;
//        return this;
//    }
//
//    public PaymentData withExpYear(String expYear) {
//        this.expYear = expYear;
//        return this;
//    }

    public PaymentData withSecCod(String secCod) {
        this.secCod = secCod;
        return this;
    }

    public PaymentData withUserEmail(String useremail) {
        this.useremail = useremail;
        return this;
    }


}
