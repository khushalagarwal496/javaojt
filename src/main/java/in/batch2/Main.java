package in.batch2;

import in.batch2.dao.EmployeeDAO;
import in.batch2.model.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args){
        EmployeeDAO employeeDAO = new EmployeeDAO();

        Employee employee = employeeDAO.findById(18);
        employee.setName("Virat Kohli");
        employee.setBranch("Cricket");
        employee.setSalary(1327373838);
        employeeDAO.update(employee);

        //employeeDAO.delete(18);

        List<Employee> employees = employeeDAO.findAll();

        for(Employee employee1 : employees){
            System.out.println(employee1);
        }

        //System.out.println("/n");
    }
}
