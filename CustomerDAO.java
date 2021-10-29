package Prestige_Banking;

import Prestige_Banking.domain.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

//add New Customer
    //add New Address
    //New account
    //update customer Details -- Vivek
    //show Transaction
    //execute Transaction ---

    // Done //show account Details ** 
    
    // Old_Pass = Null initially
    // New_Pass = Current Pass
    // Multiple Account Numbers for same CustID issue
    // Done //Change login password 
    // Done //change transaction password ** Mukul
    
    //Customer Login 
    //Customer Logout


public class CustomerDAO {
    public int addCustomer(Customer c) {
        
        Connection conn = DBConnection.getConnect();
        String sql = "insert into pb_customer(Customer_ID, Name, Date_Of_Birth, Login_Password) values(?,?,?,?)";
        int count = 0;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, c.getCustID());
		    pstmt.setString(2, c.getCustomerName());
		    pstmt.setDate(3, c.getDob()); // for java.sql.Date object
		    pstmt.setString(4, c.getLoginPassword());

            count = pstmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }


    public Customer fetchCustomerDetails(int custID , String loginPassword) {

        int validationCount = 0 ;
        Connection conn = DBConnection.getConnect();
        String sql = "select * from pb_customer where CUSTOMER_ID = ? and login_password = ?";
        // String sql = "select * from pb_account";
        Customer currCustomer = new Customer();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, custID); // Comment out to get all accounts in bank
            pstmt.setString(2, loginPassword);
            ResultSet resSet = pstmt.executeQuery();

            while(resSet.next()) {
               validationCount++ ;
               System.out.println(resSet.getString(2));
               currCustomer.setCustID(custID);
               currCustomer.setCustomerName(resSet.getString(2));
               currCustomer.setLoginPassword(loginPassword);
               currCustomer.setDob(resSet.getDate(3));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        if(validationCount ==1)
            return currCustomer ;
        else    
            return null ;

    }





    public List<Account> showAccountDetails(int custID) {

        Connection conn = DBConnection.getConnect();
        String sql = "select * from pb_account where CUSTOMER_ID = ?";
        // String sql = "select * from pb_account";
        ArrayList <Account> accountList = new ArrayList <Account>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, custID); // Comment out to get all accounts in bank
            ResultSet resSet = pstmt.executeQuery();

            while (resSet.next()) {
                Account acc = new Account();    
                acc.setAccountNumber(resSet.getInt("ACCOUNT_NUMBER"));
                acc.setCustId(custID);
                acc.setAccountType(resSet.getString("ACCOUNT_TYPE"));
                acc.setIfsc(resSet.getString("IFSC"));
                acc.setTransactionPassword(resSet.getString("TRANSACTION_PASSWORD"));
                acc.setAccountCreationDate(resSet.getTimestamp("ACCOUNT_CREATION_DATE"));
                acc.setTransferLimit(resSet.getFloat("TRANSFER_LIMIT"));
                acc.setBalance(resSet.getFloat("BALANCE"));
                accountList.add(acc);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }  
    
    public int changeLoginPassword(int custID, String newLoginPassword) {

        Connection conn = DBConnection.getConnect();
        String sqlFetch = "select NEW_LOGIN_PASSWORD from pb_password_details where CUSTOMER_ID = ?";
        String sqlUpdatePassTable = "update pb_password_details set OLD_LOGIN_PASSWORD = ?, NEW_LOGIN_PASSWORD = ?, LOGIN_PASSWORD_CREATION_DATE = ? where customer_id = ?";
        String sqlUpdateCustTable = "update pb_customer set LOGIN_PASSWORD = ? where customer_id = ?";
        int count = 0;

        try {
            PreparedStatement pstmtFetch = conn.prepareStatement(sqlFetch);
            pstmtFetch.setInt(1, custID);
            ResultSet resSetFetch = pstmtFetch.executeQuery();

            String currentLoginPassword="";

            if (resSetFetch.next()) {
                currentLoginPassword = resSetFetch.getString("NEW_LOGIN_PASSWORD");
            }


            PreparedStatement pstmtUpdatePass = conn.prepareStatement(sqlUpdatePassTable);
            pstmtUpdatePass.setString(1, currentLoginPassword);
            pstmtUpdatePass.setString(2, newLoginPassword);
            pstmtUpdatePass.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmtUpdatePass.setInt(4, custID);

            PreparedStatement pstmtUpdateCust = conn.prepareStatement(sqlUpdateCustTable);
            pstmtUpdateCust.setString(1, newLoginPassword);
            pstmtUpdateCust.setInt(2, custID);

            count = pstmtUpdatePass.executeUpdate();
            pstmtUpdateCust.executeUpdate();

            count = pstmtUpdatePass.executeUpdate();
            pstmtUpdateCust.executeUpdate();
            
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    } 

    public int changeTransactionPassword(int custID, String newTransactionPassword) {

        Connection conn = DBConnection.getConnect();
        String sqlFetch = "select NEW_TRANSACTION_PASSWORD from pb_password_details where CUSTOMER_ID = ?";
        String sqlUpdatePassTable = "update pb_password_details set OLD_TRANSACTION_PASSWORD = ?, NEW_TRANSACTION_PASSWORD = ?, TRANSACT_PSWD_CREATE_DATE = ? where customer_id = ?";
        String sqlUpdateAccTable = "update pb_account set TRANSACTION_PASSWORD = ? where customer_id = ?";
        int count = 0;

        try {
            PreparedStatement pstmtFetch = conn.prepareStatement(sqlFetch);
            pstmtFetch.setInt(1, custID);
            ResultSet resSetFetch = pstmtFetch.executeQuery();

            String currentTransactionPassword="";

            if (resSetFetch.next()) {
                currentTransactionPassword = resSetFetch.getString("NEW_TRANSACTION_PASSWORD");
            }


            PreparedStatement pstmtUpdatePass = conn.prepareStatement(sqlUpdatePassTable);
            pstmtUpdatePass.setString(1, currentTransactionPassword);
            pstmtUpdatePass.setString(2, newTransactionPassword);
            pstmtUpdatePass.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmtUpdatePass.setInt(4, custID);

            PreparedStatement pstmtUpdateAcc = conn.prepareStatement(sqlUpdateAccTable);
            pstmtUpdateAcc.setString(1, newTransactionPassword);
            pstmtUpdateAcc.setInt(2, custID);

            count = pstmtUpdatePass.executeUpdate();
            pstmtUpdateAcc.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public void updateCustDetails(Customer c){
        Connection conn = DBConnection.getConnect();
        String sql="update PB_Customer set Name = ?, Date_of_Birth = ? , Login_Password = ? where customer_id = ?";

        try {
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, c.getCustomerName());
            pstmt.setDate(2, c.getDob());
            pstmt.setString(3, c.getLoginPassword());
            pstmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace() ;
        }
    }


    public List<TransactionDetails> transactionDetails(Account acc )
    {   
        Connection con = DBConnection.getConnect();
        String sql="select * from PB_transaction_details where account_number = ?";
        ArrayList<TransactionDetails> tranList = new ArrayList<TransactionDetails>() ;
        try{
            PreparedStatement pstmt = con.prepareStatement(sql) ;
            pstmt.setInt(1, acc.getAccountNumber());
            ResultSet resultSet = pstmt.executeQuery() ;
            while(resultSet.next())
            {
                TransactionDetails td =new TransactionDetails();

                 td.setTransactionId( resultSet.getInt("transaction_ID") ) ;
                 td.setReferenceNumber( resultSet.getString("reference_number") ) ;
                 td.setAccountNumber( resultSet.getInt("account_number") );
                 td.setTransactionType( resultSet.getString("transaction_type") );
                 td.setTransactionTime(resultSet.getTimestamp("transaction_date")) ;
                 td.setBankId( resultSet.getInt("Bank_id") ) ;

                tranList.add(td);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return tranList ;
    }

    public void transaction(Customer c , Account acc , int TranId)
    {   Scanner sc = new Scanner(System.in) ;
    
        System.out.println("Enter the following details of payee for payment.");
        System.out.println("Enter destination Account NUmber :");
        int destAccNumber = sc.nextInt() ;
        System.out.println("Enter payee account holder Name:");
        String AccHolderName  = sc.next();
        System.out.println("Enter payee IFSC :");
        String ifsc  = sc.next();
        System.out.println("Enter amount to send:");
        float amount = sc.nextFloat(); 


        System.out.println("Enter your Customer ID") ;
        int customerId = sc.nextInt() ;
        System.out.println("Enter your Transaction Password") ;
        String pswd = sc.next() ;
        int validationCount = 0 ;
        Connection conn = DBConnection.getConnect();

        
        String sqlValidate="select count(*) as count_validation from PB_password_details where customer_id = ? and new_transaction_password = ?";
        
        //String sqlBalanceSender = "Select account_number , balance from PB_account where customer_id = ?" ;
        String sqlBalanceRec = "Select balance from PB_account where account_number = ?" ;
        String sqlTransfer = "update PB_Account set balance = ? where account_number = ?" ;
        String sqlDeduction = "Update  PB_account set balance = ? where account_number = ? " ;
        String sqlCreateTransaction = "Insert into PB_transaction_details values(? , ? , ? ,? ,? ,?)" ;
        // String sqlCreateTransaction = "Insert into PB_transaction_details(transaction_id, reference_number, account_number, transaction_type, bank_id) values(? , ? , ? ,? ,? ,?)" ;
        // String sqlBankId = "Select bank_id from PB_Bank where ifsc = ?" ;
        

        try {
            
            PreparedStatement pstmt = conn.prepareStatement(sqlValidate);
            pstmt.setInt(1, customerId);
            pstmt.setString(2, pswd );
            ResultSet resultSet=  pstmt.executeQuery();
            
            while(resultSet.next())
            {
                validationCount++ ;
                System.out.println(resultSet.getInt("count_validation"));
            }

            System.out.println(validationCount);

            if(validationCount == 1)
            {   
                //int accNumberSend = acc.getAccountNumber();
               /* PreparedStatement pstmtBalanceSender = conn.prepareStatement(sqlBalanceSender) ;
                pstmtBalanceSender.setInt(1, customerId);
                ResultSet  balanceSetSender = pstmtBalanceSender.executeQuery() ;
                System.out.println(balanceSetSender); 
                while (balanceSetSender.next()) {
                accNumberSend = balanceSetSender.getInt("ACCOUNT_NUMBER") ;
                senderBalance =  balanceSetSender.getFloat("balance") ; */
                //System.out.println("Before transfer: " + senderBalance);
                }
                //System.out.println("Before transfer outside while: " + senderBalance);

                //System.out.println("After getInt Acc No."); 
                
                PreparedStatement pstmtBalanceRec = conn.prepareStatement(sqlBalanceRec) ;
                pstmtBalanceRec.setInt(1, destAccNumber);
                
                ResultSet  balanceSetRec = pstmtBalanceRec.executeQuery() ;
                System.out.println(balanceSetRec);

                float recieverBalance = 0;
                while (balanceSetRec.next()) {
                // if (balanceSetRec.next()) {
                recieverBalance =  balanceSetRec.getFloat("balance") ;
                System.out.println("Before transfer: " + recieverBalance);
                }
                

                //System.out.println("Before transfer outside while: " + recieverBalance);
                //Deducting from sender balance
                Float senderBalance = acc.getBalance();
                PreparedStatement pstmtDeduction = conn.prepareStatement(sqlDeduction) ;
                pstmtDeduction.setFloat(1, senderBalance - amount);
                pstmtDeduction.setInt(2, acc.getAccountNumber());
                pstmtDeduction.executeUpdate() ;

                //adding to payee
                System.out.println("Transferring Amount.. Please wait");
                PreparedStatement pstmtTransfer = conn.prepareStatement(sqlTransfer) ;
                pstmtTransfer.setFloat(1, recieverBalance + amount);
                pstmtTransfer.setInt(2, destAccNumber);
                pstmtTransfer.executeUpdate() ;

                System.out.println("After transfer: " + recieverBalance + amount);
                
                PreparedStatement pstmtTransactionDetails = conn.prepareStatement(sqlCreateTransaction) ;
                pstmtTransactionDetails.setInt(1, acc.getAccountNumber()*8 + (++TranId));
                pstmtTransactionDetails.setString(2, acc.getIfsc() + Integer.toString(TranId/1000) ) ;
                pstmtTransactionDetails.setInt(3 , acc.getAccountNumber()) ;
                pstmtTransactionDetails.setString(4, "DEBIT");
                pstmtTransactionDetails.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                // pstmtTransactionDetails.setInt(5, ++tranCount);
                pstmtTransactionDetails.setInt(6 , 1000) ;
                pstmtTransactionDetails.executeUpdate() ;

                System.out.println("ACC Send" + destAccNumber);
                PreparedStatement pstmtTransactionDetailsRec = conn.prepareStatement(sqlCreateTransaction) ;
                pstmtTransactionDetailsRec.setInt(1, destAccNumber + (++TranId));
                pstmtTransactionDetailsRec.setString(2, acc.getIfsc() + Integer.toString(--TranId/1000)) ;
                pstmtTransactionDetailsRec.setInt(3 , destAccNumber) ;
                pstmtTransactionDetailsRec.setString(4, "CREDIT");
                pstmtTransactionDetailsRec.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
                // pstmtTransactionDetailsRec.setInt(5, ++tranCount);
                pstmtTransactionDetailsRec.setInt(6 , 1000) ;
                pstmtTransactionDetailsRec.executeUpdate() ;
                
                
                
            }
        
        catch(SQLException e){
            e.printStackTrace() ;
        }

        
    }

}



/* Extraneous Misc Statements

    Password
    // loginPasswordDetails.setPasswordId(resSetFetch.getInt("PASSWORD_ID"));
    // loginPasswordDetails.setCustID(custID);
    // loginPasswordDetails.setAccountNumber(resSetFetch.getInt("ACCOUNT_NUMBER"));
    // loginPasswordDetails.setOldLoginPassword(resSetFetch.getString("OLD_LOGIN_PASSWORD"));
    // loginPasswordDetails.setNewLoginPassword(resSetFetch.getString("NEW_LOGIN_PASSWORD"));
    // loginPasswordDetails.setLoginPasswordCreationDateTime(resSetFetch.getTimestamp("LOGIN_PASSWORD_CREATION_DATE"));
    // loginPasswordDetails.setOldTransactionPassword(resSetFetch.getString("OLD_TRANSACTION_PASSWORD"));
    // loginPasswordDetails.setNewTransactionPassword(resSetFetch.getString("NEW_TRANSACTION_PASSWORD"));
    // loginPasswordDetails.setTransactionPasswordCreationDateTime(resSetFetch.getTimestamp("TRANSACT_PSWD_CREATE_DATE"));

*/