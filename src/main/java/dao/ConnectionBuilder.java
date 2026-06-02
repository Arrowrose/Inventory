package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBuilder {
    
    // Параметры подключения (укажите свои)
    private static final String URL = "jdbc:postgresql://localhost:5432/inventory";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "psql";  
    
    public ConnectionBuilder() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("✅ Драйвер PostgreSQL загружен");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Драйвер PostgreSQL НЕ НАЙДЕН");
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() throws SQLException {
        System.out.println("🟢 Подключаюсь к БД: " + URL);
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }
}