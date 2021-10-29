package Prestige_Banking.domain;
public class Address {
    private int address_Id ;
    private String street ;
    private String city ;
    private int pin ;
    

    /**
     * @return int return the address_Id
     */
    public int getAddress_Id() {
        return address_Id;
    }

    /**
     * @param address_Id the address_Id to set
     */
    public void setAddress_Id(int address_Id) {
        this.address_Id = address_Id;
    }

    /**
     * @return String return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return String return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return int return the pin
     */
    public int getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

}
