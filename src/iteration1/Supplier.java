/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iteration1;

/**
 *
 * @author ayushkumar
 */

import java.io.BufferedReader;
import java.io.IOException;

public class Supplier {
    private String SupplierName;
    private double VAT;
    private String Address;
    private double Charges;
    private int DaysToDelivery;
    
    public Supplier(){
        this.GetSupplierName();
        this.GetVAT();
        this.GetAddress();
        this.GetCharges();
        this.GetDaysToDelivery();        
    }
    
    public Supplier(String SupplierName, double VAT, String Address, double Charges,int DaysToDelivery){
        this.SupplierName = SupplierName;
        this.VAT = VAT;
        this.Address = Address;
        this.Charges = Charges;
        this.DaysToDelivery = DaysToDelivery;
    }
    
    public String ToString(){    
        return ("Vendor's Address: " + this.GetAddress() + "\n"
              + "Vendor's Charges: £" + this.GetCharges() + "\n"
              + "Vendor's Time to Delivery: " + this.GetDaysToDelivery() + "days." + "\n");
    }
    
    public void EditSupplier(String SupplierName){
        this.SetAddress(SupplierName);
        this.SetCharges(SupplierName);
        this.SetDaysToDelivery(SupplierName);
    }
    
    public void LoadFromFile(BufferedReader bin){
        try{
            String string;
            while ((string = bin.readLine()) != null){
                String[] LineArray = string.split(" , ");
                Address = LineArray[12];
                Charges = Double.parseDouble(LineArray[13]);
                DaysToDelivery = Integer.parseInt(LineArray[14]);              
            }
        }catch (IOException ioe){
            System.out.println("Error in saving!");
        }
    }
    
    public String SetAddress(String SupplierName){
        if(SupplierName.contentEquals("Gardenia")){
            this.Address = "Address_Gardenia";
        }else{
            if(SupplierName.contentEquals("Billy The Butcher")){
                this.Address = "Address_BillyTheButcher";
            }else{
                if(SupplierName.contentEquals("Dairy Queen")){
                    this.Address = "Address_DairyQueen";
                }else{
                    if(SupplierName.contentEquals("SweetStop")){
                        this.Address = "Address_SweetStop";
                    }
                }
            }
        }
        return Address;
    }
    
    public double SetCharges(String SupplierName){
        if(SupplierName.contentEquals("Gardenia")){
            this.Charges = 19.99;
        }else{
            if(SupplierName.contentEquals("Billy The Butcher")){
                this.Charges = 29.99;
            }else{
                if(SupplierName.contentEquals("Dairy Queen")){
                    this.Charges = 8.99;
                }else{
                    if(SupplierName.contentEquals("SweetStop")){
                        this.Charges = 39.99;
                    }
                }
            }
        }
        return this.Charges;
    }
    
    public int SetDaysToDelivery(String SupplierName){
        if(SupplierName.contentEquals("Gardenia")){
            this.DaysToDelivery = 14;
        }else{
            if(SupplierName.contentEquals("Billy The Butcher")){
                this.DaysToDelivery = 7;
            }else{
                if(SupplierName.contentEquals("Dairy Queen")){
                    this.DaysToDelivery = 1;
                }else{
                    if(SupplierName.contentEquals("SweetStop")){
                        this.DaysToDelivery = 5;
                    }else{} //Change this if it doesn't work!
                }
            }
        }
        return this.DaysToDelivery;
    }
    
    public double SetVAT(String SupplierName){
        if(SupplierName == "Gardenia"){
            return this.VAT = 10;
        }else{
            if(SupplierName == "Billy The Butcher"){
                return this.VAT = 12;
            }else{
                if(SupplierName == "Dairy Queen"){
                    return this.VAT = 5;
                }else{
                    if(SupplierName == "SweetStop"){
                        return this.VAT = 25;
                    }
                }
            }
        }
        return this.VAT;
    }
    
    public String GetSupplierName(){
        return SupplierName;
    }
    
    public double GetVAT(){
        return ((this.VAT) / 100);
    }
    
    public String GetAddress(){
        return this.Address;
    }
    
    public double GetCharges(){
        return this.Charges;
    }
    
    public int GetDaysToDelivery(){
        return this.DaysToDelivery;
    }
    
    public double EditVAT(double NewVAT){
        return this.VAT = ((NewVAT) / 100);
    }
    
}