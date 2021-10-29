package Prestige_Banking.domain;

public class Bank {
    private int bankId ;
    private String bankName ;
    private String ifsc ;
    private String branch ;
    private int numberOfCustomers ;


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

    /**
     * @return String return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
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
     * @return String return the branch
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * @return int return the numberOfCustomers
     */
    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    /**
     * @param numberOfCustomers the numberOfCustomers to set
     */
    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

}
