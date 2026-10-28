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
import java.util.Map;

@WebServlet(urlPatterns = {"/employee/add","/employee/update"})
public class EmployeeFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
            throws ServletException, IOException {

        //private final EmployeeDAO employeeDAO;//dependency injection allows Inversion of control

        final EmployeeDAO employeeDAO = new EmployeeDAO();


        if(req.getServletPath().equals("/employee/add")){
            ThymeleafConfig.render(req,
                    res,
                    Map.of("employee",new Employee()),
                    "add-new-employee");
            return;
        }

        Long id = Long.parseLong(req.getParameter("id"));

        Employee employee = employeeDAO.findById(id);
        if(employee == null){
            res.sendError(HttpServletResponse.SC_NOT_FOUND,"Employee not found");
            return;
        }

        ThymeleafConfig.render(req,res, Map.of("employee",employee),"update-employee");

    }
}
