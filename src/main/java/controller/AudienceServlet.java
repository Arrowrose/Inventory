package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/audience")
public class AudienceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public AudienceServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        try {
            writer.println("<h2>ПРИВЕТ! Это сервлет для работы с АУДИТОРИЯМИ</h2>");
            writer.println("<p>Вариант 20. Здесь будет список аудиторий</p>");
            writer.println("<a href='/Inventory/'>На главную</a>");
        } finally {
            writer.close();
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}