package Prestige_Banking.domain;

import java.sql.Timestamp;

public class Account {
    private int accountNumber ;
    // ^*^ Added Customer ID;
    private int custID ;
    private String accountType ;
    private String ifsc ;
    private String transactionPassword ;
    // ^*^ private LocalDateTime accountCreationDate ;
    // ^*^ Changed to Timestamp...SQL getTimestamp() compatibility
    private Timestamp accountCreationDate;
    private float transferLimit ;
    private float balance ;

    

    /**
     * @return int return the accountNumber
     */
    public int getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return int return the custID
     */
    public int getCustId() {
        return custID;
    }

    /**
     * @param custID the custID to set
     */
    public void setCustId(int custID) {
        this.custID = custID;
    }


    /**
     * @return String return the accountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * @param accountType the accountType to set
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * @return String return the ifsc
     */
    public String getIfsc() {
        return ifsc;
    }

    /**
     * @param ifsc the ifsc to set
     */
    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    /**
     * @return String return the transactionPassword
     */
    public String getTransactionPassword() {
        return transactionPassword;
    }

    /**
     * @param transactionPassword the transactionPassword to set
     */
    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }

    /**
     * @return Timestamp return the accountCreationDate
     */
    public Timestamp getAccountCreationDate() {
        return accountCreationDate;
    }

    /**
     * @param accountCreationDate the accountCreationDate to set
     */
    public void setAccountCreationDate(Timestamp accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    /**
     * @return float return the transferLimit
     */
    public float getTransferLimit() {
        return transferLimit;
    }

    /**
     * @param transferLimit the transferLimit to set
     */
    public void setTransferLimit(float transferLimit) {
        this.transferLimit = transferLimit;
    }

    /**
     * @return float return the balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

}
