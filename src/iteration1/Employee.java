/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iteration1;

/**
 *
 * @author Ayush Kumar [Student number: 2028016]
 */

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Employee {
    private String ID;
    private String password;
    private String FileName = "EmployeeLoginFile.txt";
    
    public Employee(){
        this.Edit(" "," ");
    }
    
    public Employee(String ID, String password){
        this.Edit(ID,password);
    }
    
    public Employee CreateRecord(Employee R){
        R.ID = ID;
        R.password = password;
        
        return R;
    }
    
    public void Edit(String ID, String password){
        this.ID = ID;
        this.password = password;
    }
    
    public boolean IsRegistered(String ID, String password){
        boolean IsRegistered;
        
        Employee employee = new Employee();
        employee.ID = ID;
        employee.password = password;
        this.CreateRecord(employee);
        FileWriter writer;
        
        try{
            writer = new FileWriter(FileName, true);
            writer.write(ID+System.getProperty("line.separator"));
            writer.write(password+System.getProperty("line.separator"));
            IsRegistered = true;
            writer.flush();
            writer.close();
            writer = null;
        } catch (IOException ioe){
            IsRegistered = false;
        }
        return IsRegistered;
    }
    
    public boolean IsEmployee(String ID, String password, String filename){
        boolean isFound = false;
        String Record;
        FileReader Reader;
        
        try{
            Reader = new FileReader(FileName);
            BufferedReader bin = new BufferedReader(Reader);
            Record = new String();
            while((Record = bin.readLine()) != null){
                if(ID.contentEquals(Record)){
                    Record = bin.readLine();
                    if(password.contentEquals(Record)){
                        isFound = true;
                        this.Edit(ID,password);
                    }
                }
            }
            bin.close();
            bin = null;
        }catch(IOException ioe){
            isFound = false;
        }
        return isFound;
    }
}
