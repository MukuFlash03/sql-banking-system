package Prestige_Banking;

import Prestige_Banking.domain.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        boolean loginFlag = true ;

        CustomerDAO cdao = new CustomerDAO() ;
        while(loginFlag)
        {
            System.out.println("Press 1 for Customer Login");
            System.out.println("Press 2 for Admin login");
            System.out.println("Press 3 to exit server");
            int loginKey = scn.nextInt() ;
            if(loginKey == 1){
            System.out.println("Enter your Customer ID");
                int custID = scn.nextInt() ;

                System.out.println("Enter login password");
                String loginPassword = scn.next() ;

                Customer currCustomer = cdao.fetchCustomerDetails(custID , loginPassword);
              
               while(currCustomer != null)
               {    
                    System.out.println("Welcome, You are logeed in :)\n\n");

                    List<Account> currAccounts = cdao.showAccountDetails(custID) ;
                    int accCount = 1 ;
                    for(Account acc:currAccounts)
                    {   System.out.println("Account "+ accCount + " Details :");
                        System.out.println("Account Number : " + acc.getAccountNumber());
                        System.out.println("Account Type : " + acc.getAccountType());
                        System.out.println("Current Balance : " + acc.getBalance());
                        accCount++ ;
                        System.out.println("------------------------");
                    }

                System.out.println("Operation Menu: ");
                System.out.println("1. Account Activities");
                System.out.println("2. Transfers");
                System.out.println("3. Change Login Password");
                System.out.println("4. Change Transaction Password");
                System.out.println("5. Log Out");
                System.out.println("Enter choice: ");
                int ch1 = scn.nextInt();
                scn.nextLine();

                switch (ch1) {
                    case 1: 
                        for(Account acc:currAccounts){

                            List<TransactionDetails> transList = cdao.transactionDetails(acc) ;
                            for(TransactionDetails t:transList)
                            {
                                System.out.println(t.getAccountNumber() + "\t" + t.getTransactionId() + "\t" + t.getReferenceNumber() + "\t" + t.getTransactionTime() + "\t" + t.getBankId());
                            }
                        }
                        break;

                    case 2:
                        int count = 0 ;
                        for(Account acc:currAccounts)
                        {   count++ ;
                            System.out.println("Press " + count + " to select Account " + count + ": " + acc.getAccountNumber() + "for payment");
                        
                        }
                        int accNo = scn.nextInt() ;
                        if(accNo <= count && accNo >0){
                            List<TransactionDetails> transList = cdao.transactionDetails(currAccounts.get(accNo-1)) ;
                            int latestTranId = transList.get(transList.size() -1).getTransactionId() ;
                            cdao.transaction(currCustomer, currAccounts.get(accNo -1) , latestTranId);
                        }
                        break;

                    case 3:                
                        changeLoginPass(currCustomer.getCustID());
                        break;

                    case 4:                
                        changeTransactPass(currCustomer.getCustID());
                        break;

                    case 5:                
                        System.out.println("Logging OUT......");
                        currCustomer = null ;
                        currAccounts = null ;
                        break;
                    default:
                        System.out.println("Invalid Choice.");
                    }
            
                }

            }

            if(loginKey == 2){
                System.out.println("Admin Logged in :)\n");
                while(loginKey == 2)
                {
                    System.out.println("Press 1 to add new Customer");
                    int ch2 = scn.nextInt() ;
                    switch (ch2) {
                        case 1:
                            updateCustomerData("add");
                            break;
                        case 2:
                            System.out.println("Admin Logging Out.....\n");
                            loginKey = 5;
                        default:
                            break;
                    }
                        
                    
                }
            }

            if(loginKey == 3){
                System.out.println("Exiting Server.....");
                loginFlag = false ;
            }

        }

    }





  

    public static void updateCustomerData(String action) {

        Scanner scn = new Scanner(System.in);

        int custID;
        String customerName, birthDate, loginPassword;
        Date dob;
        java.util.Date bdate;
        int count = 0;

        System.out.println("Welcome to Customer Data Entry: ");
        System.out.println();
        System.out.println("Enter Customer Details: ");
        
        System.out.println("\nEnter Customer ID: ");
        custID = scn.nextInt();
        scn.nextLine();
        
        System.out.println("\nEnter Customer Name: ");
        customerName = scn.nextLine();
        
        System.out.println("\nEnter Date of Birth in dd/mm/yyyy format: ");
        birthDate = scn.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        try {
            bdate = dateFormat.parse(birthDate);    
            dob = new Date(bdate.getTime());

            System.out.println("\nEnter Login Password: ");
            loginPassword = scn.nextLine();

            Customer c1 = new Customer();
            c1.setCustID(custID);
            c1.setCustomerName(customerName);
            c1.setDob(dob);
            c1.setLoginPassword(loginPassword);
            
            CustomerDAO cdao = new CustomerDAO();

            if (action.equals("add"))
                count = cdao.addCustomer(c1);
            // else if (action.equals("update"))
            //   count = cdao.updateCustDetails(c1);
            System.out.println(count + " No. of Rows affected.");

        } 
        catch (ParseException e) {
            System.out.println("Date Parse Exception. Sorry for inconvenience.");
        }
    }

    
    public static void showAccountData() {
        
        Scanner scn = new Scanner(System.in);

        int custID;

        System.out.println("\nEnter Customer ID: ");
        custID = scn.nextInt();
        scn.nextLine();

        CustomerDAO cdao = new CustomerDAO();
        List <Account> accList = cdao.showAccountDetails(custID);

        for (Account acc : accList) { 
            System.out.println(acc.getCustId() + "\t" + acc.getAccountNumber() + "\t" + acc.getAccountType() + "\t" + acc.getIfsc() + "\t" + acc.getBalance() + "\t" + acc.getTransactionPassword() + "\t" + acc.getAccountCreationDate() + "\t " + acc.getTransferLimit());
        }

        System.out.println();
    }

    public static void changeLoginPass(int custID) {
        
        Scanner scn = new Scanner(System.in);

        String newLoginPass, confirmPass;
        
        System.out.println("Enter New Login Password: ");
        newLoginPass = scn.nextLine();
        System.out.println("Confirm New Login Password: ");
        confirmPass = scn.nextLine();

        if (!newLoginPass.equals(confirmPass))
            System.out.println("Passwords do not match. Please try again.");
        else {
            CustomerDAO cdao = new CustomerDAO();
            int count = cdao.changeLoginPassword(custID, newLoginPass);
            System.out.println(count + " No. of Rows affected.");
        }
    }

    public static void changeTransactPass(int custID) {
        
        Scanner scn = new Scanner(System.in);

        String newTransactPass, confirmPass;
        System.out.println("Enter New Transaction Password: ");
        newTransactPass = scn.nextLine();
        System.out.println("Confirm New Transaction Password: ");
        confirmPass = scn.nextLine();

        if (!newTransactPass.equals(confirmPass))
            System.out.println("Passwords do not match. Please try again.");
        else {
            CustomerDAO cdao = new CustomerDAO();
            int count = cdao.changeTransactionPassword(custID, newTransactPass);
            System.out.println(count + " No. of Rows affected.");
        }
    }

}




