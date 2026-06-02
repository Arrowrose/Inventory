package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.InventoryDbDAO;
import dao.AudienceDbDAO;
import domain.Inventory;
import domain.Audience;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        InventoryDbDAO dao = new InventoryDbDAO();
        AudienceDbDAO audienceDao = new AudienceDbDAO();
        
        try {
            List<Inventory> inventories = dao.findAll();
            List<Audience> audiences = audienceDao.findAll();
            
            request.setAttribute("inventories", inventories);
            request.setAttribute("audiences", audiences);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка загрузки данных: " + e.getMessage());
        }
        
        request.getRequestDispatcher("/views/inventory.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}