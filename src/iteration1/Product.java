/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iteration1;

/**
 *
 * @author Ayush Kumar [Student number: 2028016]
 */

// Calling the libraries required for the Product Class:

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Product {
    // This the declaration/initialization of the attributes and objects:
    private String ID;
    private String Name;
    private double Weight;
    private double Price;
    private double PriceVAT;
    private String ExpiryDate;
    private String DateOfOrder;
    private double Stock;
    private double NewQuantity;
    private double MinQuantity;
    private double MaxQuantity;
    private double RecommendedAmount;
    private String Image;
    private Supplier Supplier;
    private String SupplierName;
    
    public void Product(){
        Supplier = new Supplier(); // Instantiation of the objects.
        this.LoadFromFile();
    }
    
    public void EditProduct(String ID, String Name, double Weight, double Price,
            String ExpiryDate, String DateOfOrder,double Stock, double MinQuantity,
            double MaxQuantity, String Image, String SupplierName){
        
        // The following extracts the values from the user to assign into the class's attributes:
        this.SetID(ID);
        this.SetName(Name);
        this.SetWeight(Weight);
        this.SetPrice(Price);
        this.SetSupplierPriceVAT(Price,SupplierName);
        this.SetExpiryDate(ExpiryDate);
        this.SetDateOfOrder(DateOfOrder);
        this.SetStock(Stock);
        this.SetMinQuantity(MinQuantity);
        this.SetMaxQuantity(MaxQuantity);
        this.SetImagePath(Image);
        this.SetSupplierName(SupplierName);
        this.SetSupplierAddress(SupplierName);
        this.SetSupplierCharges(SupplierName);
        this.SetSupplierDaysToDelivery(SupplierName);
    }
    
    public void SearchProduct(String SearchID, String SearchName){
        BufferedReader bin = null;
        try{
            try(FileReader FR = new FileReader("ProductListFile.txt")){
                Scanner reader = new Scanner(FR);
                String Line;
                String[] LineArray;
                
                while((Line = reader.nextLine())!= null){
                    LineArray = Line.split(" | ");
                    if(LineArray[0].equals(SearchID) && LineArray[1].equals(SearchName)){
                        this.ID = LineArray[0];
                        this.Name = LineArray[1];
                        this.Weight = Double.parseDouble(LineArray[2]);
                        this.Price = Double.parseDouble(LineArray[3]);
                        this.PriceVAT = Double.parseDouble(LineArray[4]);
                        this.ExpiryDate = LineArray[5];
                        this.DateOfOrder = LineArray[6];
                        this.Stock = Double.parseDouble(LineArray[7]);
                        this.MaxQuantity = Double.parseDouble(LineArray[8]);
                        this.MinQuantity = Double.parseDouble(LineArray[9]);
                        this.SupplierName = LineArray[10];
                        Supplier.EditSupplier(SupplierName);
                        Supplier.LoadFromFile(bin);
                    }else{}
                }
                FR.close();
            }catch (Exception e){}
        }catch (Exception e){}
    }
    
    public String ToString(){
        return(       "Product ID: " + this.ID + "\n"
                    + "Product Name: " + this.Name + "\n"
                    + "Weight: " + String.valueOf(this.Weight) + "Units(Kgs/L)\n"
                    + "Price VAT exclusive: £" +String.valueOf(this.Price) + "\n"
                    + "Price VAT inclusive: £" + String.valueOf(this.PriceVAT) + "\n"
                    + "Expiry Date: " + this.ExpiryDate + "\n"
                    + "Date to Order: " + this.DateOfOrder + "\n"
                    + "Stock: " + String.valueOf(this.Stock) + "\n"
                    + "Minimum Quantity: " + String.valueOf(this.MinQuantity) + "\n"
                    + "Maximum Quantity: " + String.valueOf(this.MaxQuantity) + "\n"
                    + this.Image + "\n"
                    + "Vendor: " + this.SupplierName + "\n"
                    + Supplier.ToString());
    }
    
    public void Image(JLabel jImageLabel) throws IOException{
        String path = this.GetImagePath();
        BufferedImage img = ImageIO.read(new File(path));
        ImageIcon icon = new ImageIcon(img);
        Image image = icon.getImage();
        Image imageScale = image.getScaledInstance(jImageLabel.getWidth(), jImageLabel.getHeight(),java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imageScale);
        jImageLabel.setIcon(scaledIcon); 
    }
    
    public void DisplayProductInformation(JTextArea jNewProductTextArea){
        jNewProductTextArea.append(this.ToString());
    }
    
    public String ToStringReOrder(double Amount){
        return(       "Product ID: " + this.ID + "\n"
                    + "Product Name: " + this.Name + "\n"
                    + "Date of Order: " + this.DateOfOrder + "\n"
                    + "Amount to Reorder: " + Amount + "\n"
                    + "Vendor: " + this.SupplierName + "\n"
                    + Supplier.ToString());
    }
    
    public void DisplayReOrderInformation(double Amount, JTextArea jReOrderProductTextArea){
        jReOrderProductTextArea.append(this.ToStringReOrder(Amount));
    }
    

    
    public void LoadFromFile(BufferedReader bin){
        try{
        String string;
        
        while((string = bin.readLine()) != null){
            String[] LineArray = string.split(", ");
            ID = LineArray[0];
            Name = LineArray[1];
            Weight = Double.parseDouble(LineArray[2]);
            Price = Double.parseDouble(LineArray[3]);
            PriceVAT = Double.parseDouble(LineArray[4]);
            ExpiryDate = LineArray[5];
            DateOfOrder = LineArray[6];
            Stock = Double.parseDouble(LineArray[7]);
            MinQuantity = Double.parseDouble(LineArray[8]);
            MaxQuantity = Double.parseDouble(LineArray[9]);
            Image = LineArray[10];
            SupplierName = LineArray[11];
            Supplier.LoadFromFile(bin);}
        }catch(IOException ioe){
                    System.out.println("Error in saving!");
                }
    }
    
    public void LoadFromFile(){
       FileReader reader = null;
       try{
           reader = new FileReader("ProductListFile.txt");
           BufferedReader bin = new BufferedReader(reader);          
           this.LoadFromFile(bin);
           
           bin.close();
           bin = null;
       }catch(IOException ioe){
           System.out.println("Error in saving!");
       }
    }
    
    public void SetID(String ID){
        this.ID = ID;
    }
    
    public void SetName(String Name){
        this.Name = Name;
    }
    
    public void SetWeight(double Weight){
        this.Weight = Weight;
    }
    
    public void SetPrice(double Price){
        this.Price = Price;
    }
    
    public void SetSupplierPriceVAT(double Price, String SupplierName){
        this.PriceVAT = Price + (Price * (Supplier.SetVAT(SupplierName)/100));
    }
    
    public void SetExpiryDate(String ExpiryDate){
        this.ExpiryDate = ExpiryDate;
    }
    
    public void SetDateOfOrder(String DateOfOrder){
        this.DateOfOrder = DateOfOrder;
    }
    
    public void SetStock(double Stock){
        this.Stock = (Stock + this.NewQuantity);
    }
    
    public void SetMinQuantity(double MinQuantity){
        this.MinQuantity = MinQuantity;
    }
    
    public void SetMaxQuantity(double MaxQuantity){
        this.MaxQuantity = MaxQuantity;
    }
    
    public void SetImagePath(String Image){
        this.Image = Image;
    }
    
    public void SetSupplierName(String SupplierName){
        this.SupplierName = SupplierName;
    }
    
    public void SetSupplierAddress(String SupplierName){
        Supplier.SetAddress(SupplierName);
    }
    
    public void SetSupplierCharges(String SupplierName){
        Supplier.SetCharges(SupplierName);
    }
    
    public void SetSupplierDaysToDelivery(String SupplierName){
        Supplier.SetDaysToDelivery(SupplierName);
    }
    
    public void SetEditPriceVAT(double Price, double NewVAT){
        this.PriceVAT = Price + (Price * Supplier.EditVAT(NewVAT));
    }
    
    public void SetRecommendedAmount(double Stock, double MaxQuantity){
        if((MaxQuantity - Stock) >= 0 )
            this.RecommendedAmount = (MaxQuantity - Stock);
        else
            this.RecommendedAmount = MaxQuantity;
    }
    
    public double EditVAT(double VAT){
        return Supplier.EditVAT(VAT);
    }
    
    public String GetID(){
        return this.ID;
    }
    
    public String GetName(){
        return this.Name;
    }
    
    public String GetImagePath(){
        return this.Image;
    }
    
    public double GetWeight(){
        return this.Weight;
    }
    
    public double GetPrice(){
        return this.Price;
    }
    
    public double GetPriceVAT(){
        return this.PriceVAT;
    }
    
    public String GetExpiryDate(){
        return this.ExpiryDate;
    }
    
    public String GetDateOfOrder(){
        return this.DateOfOrder;
    }
    
    public double GetStock(){
        return this.Stock;
    }
    
    public double GetMinQuantity(){
        return this.MinQuantity;
    }
    
    public double GetMaxQuantity(){
        return this.MaxQuantity;
    }
    
    public String GetSupplierName(){
        return this.SupplierName;
    }
    
    public String GetSupplierAddress(){
        return Supplier.GetAddress();
    }
    
    public double GetSupplierCharges(){
        return Supplier.GetCharges();
    }
    
    public int GetSupplierDaysToDelivery(){
        return Supplier.GetDaysToDelivery();
    }
    
    public double GetRecommendedAmount(){
        return this.RecommendedAmount;
    }
    
}