package application;

import model.DAO.DaoFactory;
import model.DAO.SellerDAO;
import model.Department;
import model.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
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

        System.out.println("Teste método insert =======> Seller");
        Seller objSeller = new Seller(null, "Teste", "teste@teste", new Date(), 1000.00, new Department(2, null));
        sellerDAO.insert(objSeller);

        System.out.println("Teste método update =======> Seller");
        Seller newSeller = sellerDAO.findById(4);
        newSeller.setName("Ronnie James");
        sellerDAO.update(newSeller);

        System.out.println("Teste método delete =======> Seller");
        System.out.print("Enter Id for delete: ");
        int id = sc.nextInt();
        if (sellerDAO.deleteById(id)){
            System.out.println("Delete completed!!!");
        }else{
            System.out.println("ID not found");
        }
    }
}
