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
    
    // GET - отображение списка и формы
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        InventoryDbDAO dao = new InventoryDbDAO();
        AudienceDbDAO audienceDao = new AudienceDbDAO();
        
        // Обработка удаления (если передали параметр delete)
        String deleteId = request.getParameter("delete");
        if (deleteId != null && !deleteId.isEmpty()) {
            try {
                dao.delete(Long.parseLong(deleteId));
                System.out.println("Удалён предмет инвентаря с id: " + deleteId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/inventory");
            return;
        }
        
        // Получение списка инвентаря и аудиторий для формы
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
    
    // POST - добавление нового инвентаря
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        InventoryDbDAO dao = new InventoryDbDAO();
        
        // Получаем параметры из формы
        String inventoryNumber = request.getParameter("inventoryNumber");
        String name = request.getParameter("name");
        String quantityStr = request.getParameter("quantity");
        String description = request.getParameter("description");
        String idAudienceStr = request.getParameter("idAudience");
        
        System.out.println("Добавление инвентаря:");
        System.out.println("  Инв. номер: " + inventoryNumber);
        System.out.println("  Название: " + name);
        System.out.println("  Количество: " + quantityStr);
        System.out.println("  Аудитория ID: " + idAudienceStr);
        
        if (inventoryNumber != null && !inventoryNumber.trim().isEmpty()) {
            Inventory newItem = new Inventory();
            newItem.setInventoryNumber(inventoryNumber);
            newItem.setName(name);
            
            if (quantityStr != null && !quantityStr.isEmpty()) {
                try {
                    newItem.setQuantity(Integer.parseInt(quantityStr));
                } catch (NumberFormatException e) {
                    newItem.setQuantity(0);
                }
            } else {
                newItem.setQuantity(0);
            }
            
            newItem.setDescription(description);
            
            if (idAudienceStr != null && !idAudienceStr.isEmpty()) {
                try {
                    newItem.setIdAudience(Long.parseLong(idAudienceStr));
                } catch (NumberFormatException e) {
                    newItem.setIdAudience(null);
                }
            } else {
                newItem.setIdAudience(null);
            }
            
            try {
                Long id = dao.insert(newItem);
                System.out.println("Инвентарь добавлен с id: " + id);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ошибка при добавлении: " + e.getMessage());
            }
        }
        
        // После добавления перенаправляем на GET (чтобы обновить список)
        response.sendRedirect(request.getContextPath() + "/inventory");
    }
}