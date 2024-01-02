/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iteration1;

/**
 *
 * @author ayushkumar
 */

import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.util.Scanner;

public class ProductList {
    private ArrayList<Product> ProductArrayList;
    private Product Product;
    
    public ProductList(){
        ProductArrayList = new ArrayList<Product>();
        Product = new Product();
    }
    
    public void AddToArrayList(Product product){
        ProductArrayList.add(Product);
    }
    
    public void RemoveFromArrayList(Product product){
        ProductArrayList.remove(Product);
    }
    
    public void GetProduct(String ID, String Name, double Weight, double Price,
            double PriceVAT, String ExpiryDate, String EstimatedDateToOrder, double Stock,
            double MinQuantity, double MaxQuantity, String Image, String SupplierName){
        
        Product.EditProduct(ID, Name, Weight, Price, Name, EstimatedDateToOrder, Stock, MinQuantity, MaxQuantity, Image, SupplierName);
    }
    
    public void DisplayList(JTextArea jProductListTextArea){
        try{
            FileReader FR = new FileReader("ProductListFile.txt");
            BufferedReader BR = new BufferedReader(FR);
            jProductListTextArea.read(BR,null);
        }catch(Exception e){}
    }
    
    public void DisplayProduct(String SearchID, String SearchName, JTextArea src){
        Product.SearchProduct(SearchID, SearchName);
        Product.DisplayProductInformation(src);
    }
    
    public void DisplayReOrder(String SearchID, String SearchName, double Amount, JTextArea src){
        Product.SearchProduct(SearchID, SearchName);
        Product.DisplayReOrderInformation(Amount, src);
    }
    
    public void DisplayImage(JLabel jImageLabel) throws IOException{
        Product.Image(jImageLabel);
    }
    
    public Boolean ReOrderProduct(String ID, String Name, String SupplierName){
        Boolean IsFound = false;
        try{
            try(FileReader FR = new FileReader("ProductListFile.txt")){
                Scanner reader = new Scanner(FR);
                String line;
                String[] LineArray;
                
                while((line = reader.nextLine()) != null){
                    LineArray = line.split(", ");
                    if(LineArray[0].equals(ID) && LineArray[1].equals(Name) && LineArray[10].equals(SupplierName)){
                        Product.SetRecommendedAmount(Double.parseDouble(LineArray[7]),Double.parseDouble(LineArray[9]));
                        Product.SetSupplierCharges(LineArray[10]);
                        IsFound = true;
                        break;
                    }else{
                        IsFound = false;
                    }
                }
                FR.close();
            }catch(Exception e){}
        }catch(Exception e){}
        return IsFound;
    }
    
    public void UpdateProduct(String SearchID, String SearchName, double NewStock, String NewDateOfOrder){
        ArrayList<String> TemporaryArray = new ArrayList<>();
        try{
            try(FileReader FR = new FileReader("ProductListFile.txt")){
                Scanner reader = new Scanner(FR);
                String line;
                String[] LineArray;
                
                while((line = reader.nextLine()) != null){
                    LineArray = line.split(", ");
                    if(LineArray[0].equals(SearchID) && LineArray[1].equals(SearchName)){
                        TemporaryArray.add(LineArray[0] + ", "
                                         + LineArray[1] + ", "
                                         + LineArray[2] + ", "
                                         + LineArray[3] + ", "
                                         + LineArray[4] + ", "
                                         + LineArray[5] + ", "
                                         + NewDateOfOrder + ", "
                                         + (Double.parseDouble(LineArray[7]) + NewStock) + ", "
                                         + LineArray[8] + ", "
                                         + LineArray[9] + ", "
                                         + LineArray[10] + ", "
                                         + LineArray[11] + ", "
                                         + LineArray[12] + ", "
                                         + LineArray[13] + ", "
                                         + LineArray[14] + ", ");
                    }else{
                        TemporaryArray.add(line);
                    }
                }
                FR.close();
            }catch(Exception e){}
        }catch(Exception e){}
        try{
            try(PrintWriter PR = new PrintWriter("ProductListFile.txt")){
                for(String string:TemporaryArray){
                    PR.println(string);
                }
                PR.close();
            }catch(Exception e){}
        }catch(Exception e){}
    }
    
    public void UpdateVAT(String SearchID, String SearchName, double NewVAT){
        ArrayList<String> TemporaryArray = new ArrayList<>();
        
        try{
            try(FileReader FR = new FileReader("ProductListFile.txt")){
                Scanner reader = new Scanner(FR);
                String line;
                String[] LineArray;
                
                while((line = reader.nextLine()) != null){
                    LineArray = line.split(", ");
                    if(LineArray[0].equals(SearchID) && LineArray[1].equals(SearchName)){
                        TemporaryArray.add(LineArray[0] + ", "
                                         + LineArray[1] + ", "
                                         + LineArray[2] + ", "
                                         + LineArray[3] + ", "
                                         + ((Double.parseDouble(LineArray[3])) + ((Double.parseDouble(LineArray[3])) * this.EditVAT(NewVAT))) + ", "
                                         + LineArray[5] + ", "
                                         + LineArray[6] + ", "
                                         + LineArray[7] + ", "
                                         + LineArray[8] + ", "
                                         + LineArray[9] + ", "
                                         + LineArray[10] + ", "
                                         + LineArray[11] + ", "
                                         + LineArray[12] + ", "
                                         + LineArray[13] + ", "
                                         + LineArray[14]);
                        Product.SetEditPriceVAT((Double.parseDouble(LineArray[3])), NewVAT);
                    }else{
                        TemporaryArray.add(line);
                    }
                }
                FR.close();
            }catch(Exception e){}
        }catch(Exception e){}
        
        try{
            try(PrintWriter PR = new PrintWriter("ProductListFile.txt")){
                for(String string:TemporaryArray){
                    PR.println(string);
                }
                PR.close();
            }catch(Exception e){}
        }catch(Exception e){}
    }
    
    public void SaveReOrderToFile(double Amount){
        FileWriter FW;
        try{
            FW = new FileWriter("ReOrderFile.txt", true);
            this.SaveReOrderToFile(Amount, FW);
            
            FW.flush();
            FW.close();
            FW = null;
        }catch(Exception e){
            System.out.println("Error in saving!");
        }
    }
    
    public void SaveReOrderToFile(double Amount, FileWriter FW){
        try{
            FW.write(Product.GetID() + ", "
                   + Product.GetName() + ", "
                   + Product.GetDateOfOrder() + ", "
                   + Amount + ", "
                   + Product.GetSupplierName() + ", "
                   + Product.GetSupplierAddress() + ", "
                   + Product.GetSupplierCharges() + ", "
                   + Product.GetSupplierDaysToDelivery() + ", ");
        }catch(IOException ioe){
            System.out.println("Error in saving!");
        }
    }
    
    public void SaveToFile(FileWriter FW){
        try{
            FW.write(Product.GetID() + ", "
          + Product.GetName() + ", "
          + Product.GetWeight() + ", "
          + Product.GetPrice() + ", "
          + Product.GetPriceVAT() + ", "
          + Product.GetExpiryDate() + ", "
          + Product.GetDateOfOrder() + ", "
          + Product.GetStock() + ", "
          + Product.GetMinQuantity() + ", "
          + Product.GetMaxQuantity() + ", "
          + Product.GetImagePath() + ", "
          + Product.GetSupplierName() + ", "
          + Product.GetSupplierAddress() + ", "
          + Product.GetSupplierCharges() + ", "
          + Product.GetSupplierDaysToDelivery() + ", " + "\n");
        }catch(IOException ioe){
            System.out.println("Error in saving!");
        }
    }
    
        public void SaveToFile(){
        FileWriter FW;
        try{
            FW = new FileWriter ("ProductListFile.txt", true);
        
            this.SaveToFile(FW);

            FW.flush();
            FW.close();
            FW = null;
        }
        catch(IOException ioe){
            System.out.println("Error in saving!");
        }
    }
    
    public void LoadFromFile(BufferedReader bin){
        for(int i = 0; i < ProductArrayList.size(); i++){
            Product.LoadFromFile(bin);
        }
    }
    
    public void LoadFromFile(){
        FileReader FR = null;
        try{
            FR = new FileReader("ProductListFile.txt");
            BufferedReader bin = new BufferedReader(FR);
            this.LoadFromFile(bin);
            
            bin.close();
            bin = null;
            
        }catch(IOException ioe){
            System.out.println("Error in saving!");
        }
    }
    
    public boolean SetReOrder(){
        Boolean IsOrder = false;
        if(Product.GetStock() <= Product.GetMinQuantity()){
            IsOrder = true;
        }
        return IsOrder;
    }
    
    public double EditVAT(double VAT){
        return Product.EditVAT(VAT);
    }
    
    public String GetName(){
        return Product.GetName();
    }
    
    public double GetCharges(){
        return Product.GetSupplierCharges();
    }
    
    public double GetRecommendedAmount(){
        return Product.GetRecommendedAmount();
    }
}
