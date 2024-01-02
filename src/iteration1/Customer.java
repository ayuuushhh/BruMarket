/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iteration1;

/**
 *  
 * @author Ayush Kumar [Student number: 2028016]
 */

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Customer {
    private String ID;
    private String password;
    private String filename = "CustomerLoginFile.txt";


public Customer(){
    this.Edit(" "," ");
    } 

public Customer(String ID,String password){
        this.Edit(ID, password);
    }

public Customer CreateRecord(Customer R){
        R.ID = ID;
        R.password = password;      
        
        return R;
    }

public void Edit(String ID, String password){
        this.ID = ID;
        this.password = password;
    }

// The following method saves the user credentials into file:
public boolean IsRegistered(String ID, String password){
    boolean IsRegistered;
    Customer customer = new Customer();
    customer.ID = ID;
    customer.password = password; 
    this.CreateRecord(customer);
    
    FileWriter writer;
    try{
        writer = new FileWriter(filename, true);
        writer.write(ID+System.getProperty("line.separator"));
        writer.write(password+System.getProperty("line.separator"));
        writer.write(" ||||| "+System.getProperty("line.separator"));
        
        IsRegistered = true;
        
        }catch (IOException ioe){
            IsRegistered = false;
        }
        return IsRegistered;
    }

// The following method checks if the user credentials are saved into file:
public boolean IsCustomer(String ID, String password, String filename){
    boolean IsFound = false;
    String record;
    FileReader reader;
    try{
        reader = new FileReader(filename);
        BufferedReader bin = new BufferedReader(reader);
        record = new String();
        while((record = bin.readLine()) != null){
            if (ID.contentEquals(record)){
                record = bin.readLine();
                if (password.contentEquals(record)){
                    IsFound = true;
                    this.Edit(ID, password);
                    }
                } 
            }
        bin.close();
        bin = null;
        }catch (IOException ioe){
            IsFound = false;
        }
    return IsFound;
    }
}