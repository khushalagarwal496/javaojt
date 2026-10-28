package in.batch2.servlet;

import in.batch2.config.ThymeleafConfig;
import in.batch2.dao.EmployeeDAO;
import in.batch2.model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/employees")
public class EmployeeListServlet extends HttpServlet {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
        throws ServletException, IOException{
        List<Employee> employees = employeeDAO.findAll();

        ThymeleafConfig.render(req,
                res,
                Map.of("employees",employees),
                "employee");
    }

}
