package apilipen.crutchfield.model;

public class AddressData {


    private String firstName ;
    private String lastName ;
    private String fullName;

    private String address;    //   private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;

    private String email;

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public String getFullName(){return fullName; }
    public String getEmail(){return email; }



    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }









    public AddressData withFirstName(String fullName) {
        this.firstName = fullName;
        return this;
    }
    public AddressData withLastName(String fullName) {
        this.lastName = fullName;
        return this;
    }

    public AddressData withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public  AddressData withAddress(String address) {
        this.address = address;
        return this;
    }

    public AddressData withCity(String city) {
        this.city = city;
        return this;
    }

    public AddressData withState(String state) {
        this.state = state;
        return this;
    }

    public AddressData withZip(String zip) {
        this.zip = zip;
        return this;
    }

    public AddressData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public AddressData withEmail(String email) {
        this.email = email;
        return this;
    }

}
