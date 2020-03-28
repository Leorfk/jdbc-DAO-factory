package application;

import model.DAO.DaoFactory;
import model.DAO.SellerDAO;
import model.Department;
import model.Seller;

import java.util.Date;
import java.util.List;

public class Program {

    public static void main(String[] args){

        SellerDAO sellerDAO = DaoFactory.createSellerDao();

        System.out.println("Teste método findById =======> Seller");
        Seller seller = sellerDAO.findById(8);
        System.out.println(seller);

        System.out.println("Teste método findByDepartment =======> Seller");
        List<Seller> list = sellerDAO.findByDepartment(new Department(2, null));
        for (Seller obj: list) {
            System.out.println(obj);
        }

        System.out.println("Teste método findAll =======> Seller");
        List<Seller> allSellers = sellerDAO.findAll();
        for (Seller obj: allSellers) {
            System.out.println(obj);
        }
    }
}
