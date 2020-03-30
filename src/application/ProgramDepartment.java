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

        System.out.println("Department findById");
        Department dep = departmentDAO.findById(4);
        System.out.println(dep);

        System.out.println("\nDepartment findAll");
        List<Department> list = departmentDAO.findAll();
        for (Department depart: list) {
            System.out.println(depart);
        }

/*        System.out.println("\nDepartment insert");
        Department department = new Department(null, "Server");
        departmentDAO.insert(department);*/

        System.out.println("\nDepartment update");
        Department newDepartment = new Department(3, "Heavy Metal");
        departmentDAO.update(newDepartment);

        System.out.println("\nDepartment delete");
        System.out.println("Enter department ID: ");
        int id = sc.nextInt();
        String result = departmentDAO.deleteById(id) ? "Delete completed!!!" : "ID not found";
        System.out.println(result);
    }
}
