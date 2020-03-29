package application;

import model.DAO.DaoFactory;
import model.DAO.DepartmentDAO;
import model.DAO.SellerDAO;
import model.Department;
import model.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ProgramDepartment {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        DepartmentDAO departmentDAO = DaoFactory.createDepartmentDao();

        Department dep = departmentDAO.findById(4);

        System.out.println(dep);
    }
}
