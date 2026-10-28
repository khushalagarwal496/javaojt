package in.batch2.servlet;

import in.batch2.dao.EmployeeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/employees/delete")
public class EmployeeDelete extends HttpServlet {

    private EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse res)
            throws ServletException, IOException {
        Long id =Long.parseLong(req.getParameter("id"));
        employeeDAO.delete(id);

        res.sendRedirect(req.getContextPath() + "/employees");
    }
}
