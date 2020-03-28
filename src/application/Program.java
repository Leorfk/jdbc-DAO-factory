package application;

import model.DAO.DaoFactory;
import model.DAO.SellerDAO;
import model.Department;
import model.Seller;

import java.util.Date;

public class Program {

    public static void main(String[] args){

        SellerDAO sellerDAO = DaoFactory.createSellerDao();

        Seller seller = sellerDAO.findById(8);

        System.out.println(seller);
    }
}
