package Prestige_Banking.domain;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
public class TransactionDetails {
    private int transactionId ;
    private String referenceNumber ;
    private int accountNumber ;
    private String transactionType ;
    private LocalDateTime transactionTime ;
    private int bankId ;

    

    /**
     * @return int return the transactionId
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * @return String return the referenceNumber
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * @param referenceNumber the referenceNumber to set
     */
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
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
     * @return String return the transactionType
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * @param transactionType the transactionType to set
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * @return LocalDateTime return the transactionTime
     */
    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    /**
     * @param transactionTime the transactionTime to set
     */
    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    /**
     * @return int return the bankId
     */
    public int getBankId() {
        return bankId;
    }

    /**
     * @param bankId the bankId to set
     */
    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

}
