package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.AudienceDbDAO;
import domain.Audience;

@WebServlet("/editAudience")
public class EditAudienceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // GET - показать форму редактирования
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        AudienceDbDAO dao = new AudienceDbDAO();
        
        // Получаем id из параметра запроса
        String idStr = request.getParameter("id");
        Long id = null;
        if (idStr != null && !idStr.isEmpty()) {
            id = Long.parseLong(idStr);
        }
        
        try {
            // Находим аудиторию по id
            Audience audience = dao.findById(id);
            // Получаем список всех аудиторий для отображения
            List<Audience> audiences = dao.findAll();
            
            request.setAttribute("audienceEdit", audience);
            request.setAttribute("audiences", audiences);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка загрузки данных: " + e.getMessage());
        }
        
        request.getRequestDispatcher("/views/editAudience.jsp").forward(request, response);
    }
    
    // POST - сохранить изменения
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        AudienceDbDAO dao = new AudienceDbDAO();
        
        // Получаем параметры из формы
        String idStr = request.getParameter("id");
        String number = request.getParameter("number");
        String responsible = request.getParameter("responsible");
        
        if (idStr != null && !idStr.isEmpty()) {
            Long id = Long.parseLong(idStr);
            Audience audience = new Audience();
            audience.setId(id);
            audience.setNumber(number);
            audience.setResponsible(responsible);
            
            try {
                dao.update(audience);
                System.out.println("Аудитория обновлена: id=" + id);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ошибка при обновлении: " + e.getMessage());
            }
        }
        
        // После сохранения перенаправляем на страницу списка
        response.sendRedirect(request.getContextPath() + "/audience");
    }
}