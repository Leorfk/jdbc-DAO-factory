package application;

import model.Department;
import model.Seller;

import java.util.Date;

public class Program {

    public static void main(String[] args){

        Department obj = new Department(1, "Toma");
        System.out.println(obj);

        Seller objSeller = new Seller(1, "Bob Marley", "bob@brisa.com", new Date(), 1000.00);
        System.out.println(objSeller);

    }
}
