package Prestige_Banking.domain;

import java.sql.Date;

public class Customer {
    private int custID ;
    private String customerName ;
    private Address address ;
    // ^*^ Changed to loginPassword from login_Password
    private String loginPassword ;
    // ^*^ Changed Date type to sql.Date from util.Date
    // private LocalDate dob;
    private Date dob;

    
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return int return the custID
     */
    public int getCustID() {
        return custID;
    }

    /**
     * @param custID the custID to set
     */
    public void setCustID(int custID) {
        this.custID = custID;
    }

    /**
     * @return String return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return Address return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return String return the loginPassword
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * @param loginPassword the loginPassword to set
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

}