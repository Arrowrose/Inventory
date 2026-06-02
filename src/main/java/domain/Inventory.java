package domain;

/**
 * Класс данных об инвентаре
 * Вариант 20
 */
public class Inventory {
    
    // Идентификатор инвентаря
    private Long id;
    
    // Инвентарный номер
    private String inventoryNumber;
    
    // Название
    private String name;
    
    // Количество
    private Integer quantity;
    
    // Описание
    private String description;
    
    // Внешний ключ - ссылка на аудиторию
    private Long idAudience;
    
    // Навигационное свойство - ссылка на аудиторию
    private Audience audience;
    
    // Конструктор без параметров
    public Inventory() {
    }
    
    // Конструктор с основными полями
    public Inventory(String inventoryNumber, String name, Integer quantity, String description, Audience audience) {
        this.inventoryNumber = inventoryNumber;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.audience = audience;
    }
    
    // Конструктор с внешним ключом
    public Inventory(String inventoryNumber, String name, Integer quantity, String description, Long idAudience, Audience audience) {
        this.inventoryNumber = inventoryNumber;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.idAudience = idAudience;
        this.audience = audience;
    }
    
    // Полный конструктор
    public Inventory(Long id, String inventoryNumber, String name, Integer quantity, String description, Long idAudience, Audience audience) {
        this.id = id;
        this.inventoryNumber = inventoryNumber;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.idAudience = idAudience;
        this.audience = audience;
    }
    
    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getInventoryNumber() {
        return inventoryNumber;
    }
    
    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getIdAudience() {
        return idAudience;
    }
    
    public void setIdAudience(Long idAudience) {
        this.idAudience = idAudience;
    }
    
    public Audience getAudience() {
        return audience;
    }
    
    public void setAudience(Audience audience) {
        this.audience = audience;
    }
    
    // Метод для получения номера аудитории (через навигационное свойство)
    public String getAudienceNumber() {
        return audience != null ? audience.getNumber() : "";
    }
    
    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", inventoryNumber='" + inventoryNumber + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", idAudience=" + idAudience +
                ", audience=" + (audience != null ? audience.getNumber() : "null") +
                '}';
    }
}