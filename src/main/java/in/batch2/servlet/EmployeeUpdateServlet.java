package in.batch2.servlet;

import in.batch2.dao.EmployeeDAO;
import in.batch2.model.Employee;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/employee/modify")
public class EmployeeUpdateServlet extends HttpServlet {

    EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse res)
            throws ServletException, IOException {
       Long id = Long.parseLong(req.getParameter("id"));
       String name = req.getParameter("name");
       String branch = req.getParameter("branch");
       Double salary = Double.parseDouble(req.getParameter("salary"));

       Employee employee = employeeDAO.findById(id);

       if(employee == null){
           res.sendError(HttpServletResponse.SC_NOT_FOUND,"Employee not found");
           return;
       }

       employee.setName(name);
       employee.setBranch(branch);
       employee.setSalary(salary);
       employeeDAO.update(employee);

      res.sendRedirect(req.getContextPath()+"/employees");
    }
}
