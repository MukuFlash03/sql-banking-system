package Prestige_Banking.domain;

import java.sql.Timestamp;


public class PasswordDetails {
    private int passwordId ;
    private int custID ;
    private int accountNumber ;
    private String oldLoginPassword ;
    private String newLoginPassword ;

    // ^*^ private LocalDateTime loginPasswordCreationDateTime ;
    // ^*^ Changed to Timestamp...SQL getTimestamp() compatibility
    private Timestamp loginPasswordCreationDateTime;
    private String oldTransactionPassword ;
    private String newTransactionPassword ;
    //  private LocalDateTime TransactionPasswordCreationDateTime ;
    // 'T'ransaction -> 't'ransaction
    private Timestamp transactionPasswordCreationDateTime;



    /**
     * @return int return the passwordId
     */
    public int getPasswordId() {
        return passwordId;
    }

    /**
     * @param passwordId the passwordId to set
     */
    public void setPasswordId(int passwordId) {
        this.passwordId = passwordId;
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
     * @return String return the oldLoginPassword
     */
    public String getOldLoginPassword() {
        return oldLoginPassword;
    }

    /**
     * @param oldLoginPassword the oldLoginPassword to set
     */
    public void setOldLoginPassword(String oldLoginPassword) {
        this.oldLoginPassword = oldLoginPassword;
    }

    /**
     * @return String return the newLoginPassword
     */
    public String getNewLoginPassword() {
        return newLoginPassword;
    }

    /**
     * @param newLoginPassword the newLoginPassword to set
     */
    public void setNewLoginPassword(String newLoginPassword) {
        this.newLoginPassword = newLoginPassword;
    }

    /**
     * @return Timestamp return the loginPasswordCreationDateTime
     */
    public Timestamp getLoginPasswordCreationDateTime() {
        return loginPasswordCreationDateTime;
    }

    /**
     * @param loginPasswordCreationDateTime the loginPasswordCreationDateTime to set
     */
    public void setLoginPasswordCreationDateTime(Timestamp loginPasswordCreationDateTime) {
        this.loginPasswordCreationDateTime = loginPasswordCreationDateTime;
    }

    /**
     * @return String return the oldTransactionPassword
     */
    public String getOldTransactionPassword() {
        return oldTransactionPassword;
    }

    /**
     * @param oldTransactionPassword the oldTransactionPassword to set
     */
    public void setOldTransactionPassword(String oldTransactionPassword) {
        this.oldTransactionPassword = oldTransactionPassword;
    }

    /**
     * @return String return the newTransactionPassword
     */
    public String getNewTransactionPassword() {
        return newTransactionPassword;
    }

    /**
     * @param newTransactionPassword the newTransactionPassword to set
     */
    public void setNewTransactionPassword(String newTransactionPassword) {
        this.newTransactionPassword = newTransactionPassword;
    }

    /**
     * @return Timestamp return the TransactionPasswordCreationDateTime
     */
    public Timestamp getTransactionPasswordCreationDateTime() {
        return transactionPasswordCreationDateTime;
    }

    /**
     * @param transactionPasswordCreationDateTime the transactionPasswordCreationDateTime to set
     */
    public void setTransactionPasswordCreationDateTime(Timestamp transactionPasswordCreationDateTime) {
        this.transactionPasswordCreationDateTime = transactionPasswordCreationDateTime;
    }

}
