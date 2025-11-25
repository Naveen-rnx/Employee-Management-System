package com.example.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class Employee {
    int id;
    String name;
    String dept;
    double salary;
    Employee(int id, String name, String dept, double salary) {
        this.id = id; this.name = name; this.dept = dept; this.salary = salary;
    }
}

@WebServlet(urlPatterns = {"/add", "/list"})
public class EmployeeServlet extends HttpServlet {
    static ArrayList<Employee> employees = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String dept = req.getParameter("dept");
        double salary = Double.parseDouble(req.getParameter("salary"));
        employees.add(new Employee(id, name, dept, salary));
        resp.sendRedirect("employee.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><meta charset='utf-8'><title>Employee List</title></head><body>");
        out.println("<h1>Employee List</h1>");
        if (employees.isEmpty()) {
            out.println("<p>No employees added yet.</p>");
        } else {
            out.println("<table border='1' cellpadding='6'><tr><th>ID</th><th>Name</th><th>Dept</th><th>Salary</th></tr>");
            for (Employee e : employees) {
                out.printf("<tr><td>%d</td><td>%s</td><td>%s</td><td>%.2f</td></tr>", e.id, e.name, e.dept, e.salary);
            }
            out.println("</table>");
        }
        out.println("<br><a href='employee.html'>Go Back</a>");
        out.println("</body></html>");
    }
}
