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

@WebServlet("/editInventory")
public class EditInventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // GET - показать форму редактирования
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        InventoryDbDAO dao = new InventoryDbDAO();
        AudienceDbDAO audienceDao = new AudienceDbDAO();
        
        // Получаем id из параметра запроса
        String idStr = request.getParameter("id");
        Long id = null;
        if (idStr != null && !idStr.isEmpty()) {
            id = Long.parseLong(idStr);
        }
        
        try {
            // Находим предмет инвентаря по id
            Inventory inventory = dao.findById(id);
            // Получаем список всего инвентаря
            List<Inventory> inventories = dao.findAll();
            // Получаем список аудиторий для выпадающего списка
            List<Audience> audiences = audienceDao.findAll();
            
            request.setAttribute("inventoryEdit", inventory);
            request.setAttribute("inventories", inventories);
            request.setAttribute("audiences", audiences);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка загрузки данных: " + e.getMessage());
        }
        
        request.getRequestDispatcher("/views/editInventory.jsp").forward(request, response);
    }
    
    // POST - сохранить изменения
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        InventoryDbDAO dao = new InventoryDbDAO();
        
        // Получаем параметры из формы
        String idStr = request.getParameter("id");
        String inventoryNumber = request.getParameter("inventoryNumber");
        String name = request.getParameter("name");
        String quantityStr = request.getParameter("quantity");
        String description = request.getParameter("description");
        String idAudienceStr = request.getParameter("idAudience");
        
        if (idStr != null && !idStr.isEmpty()) {
            Long id = Long.parseLong(idStr);
            Inventory inventory = new Inventory();
            inventory.setId(id);
            inventory.setInventoryNumber(inventoryNumber);
            inventory.setName(name);
            
            if (quantityStr != null && !quantityStr.isEmpty()) {
                inventory.setQuantity(Integer.parseInt(quantityStr));
            } else {
                inventory.setQuantity(0);
            }
            
            inventory.setDescription(description);
            
            if (idAudienceStr != null && !idAudienceStr.isEmpty()) {
                inventory.setIdAudience(Long.parseLong(idAudienceStr));
            } else {
                inventory.setIdAudience(null);
            }
            
            try {
                dao.update(inventory);
                System.out.println("Инвентарь обновлён: id=" + id);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ошибка при обновлении: " + e.getMessage());
            }
        }
        
        // После сохранения перенаправляем на страницу списка
        response.sendRedirect(request.getContextPath() + "/inventory");
    }
}