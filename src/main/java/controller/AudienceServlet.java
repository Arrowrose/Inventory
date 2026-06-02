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

@WebServlet("/audience")
public class AudienceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // GET - отображение списка и формы
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        AudienceDbDAO dao = new AudienceDbDAO();
        
        // Обработка удаления (если передали параметр delete)
        String deleteId = request.getParameter("delete");
        if (deleteId != null && !deleteId.isEmpty()) {
            try {
                dao.delete(Long.parseLong(deleteId));
                System.out.println("Удалена аудитория с id: " + deleteId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect(request.getContextPath() + "/audience");
            return;
        }
        
        // Получение списка аудиторий
        try {
            List<Audience> audiences = dao.findAll();
            request.setAttribute("audiences", audiences);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Ошибка загрузки данных: " + e.getMessage());
        }
        
        request.getRequestDispatcher("/views/audiences.jsp").forward(request, response);
    }
    
    // POST - добавление новой аудитории
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        AudienceDbDAO dao = new AudienceDbDAO();
        
        // Получаем параметры из формы
        String number = request.getParameter("number");
        String responsible = request.getParameter("responsible");
        
        System.out.println("Добавление аудитории: номер=" + number + ", ответственный=" + responsible);
        
        if (number != null && !number.trim().isEmpty()) {
            Audience newAudience = new Audience();
            newAudience.setNumber(number);
            newAudience.setResponsible(responsible);
            
            try {
                Long id = dao.insert(newAudience);
                System.out.println("Аудитория добавлена с id: " + id);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ошибка при добавлении: " + e.getMessage());
            }
        }
        
        // После добавления перенаправляем на GET (чтобы обновить список)
        response.sendRedirect(request.getContextPath() + "/audience");
    }
}