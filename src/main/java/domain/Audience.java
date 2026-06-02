package domain;

/**
 * Класс данных об аудиториях
 * Вариант 20
 */
public class Audience {
    
    // Идентификатор аудитории
    private Long id;
    
    // Номер аудитории
    private String number;
    
    // ФИО ответственного
    private String responsible;
    
    // Конструктор без параметров
    public Audience() {
    }
    
    // Конструктор с номером аудитории
    public Audience(String number, String responsible) {
        this.number = number;
        this.responsible = responsible;
    }
    
    // Конструктор со всеми параметрами
    public Audience(Long id, String number, String responsible) {
        this.id = id;
        this.number = number;
        this.responsible = responsible;
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    public String getResponsible() {
        return responsible;
    }
    
    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
    
    @Override
    public String toString() {
        return "Audience{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", responsible='" + responsible + '\'' +
                '}';
    }
}