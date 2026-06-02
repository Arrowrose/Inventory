package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Inventory;
import domain.Audience;

public class InventoryDbDAO implements RepositoryDAO<Inventory> {
    
    private static final String SELECT_ALL = 
        "SELECT id, inventory_number, name, quantity, description, id_audience " +
        "FROM inventory_items ORDER BY name ASC";
    
    private static final String SELECT_BY_ID = 
        "SELECT id, inventory_number, name, quantity, description, id_audience " +
        "FROM inventory_items WHERE id = ?";
    
    private static final String INSERT = 
        "INSERT INTO inventory_items(inventory_number, name, quantity, description, id_audience) " +
        "VALUES(?, ?, ?, ?, ?)";
    
    private static final String UPDATE = 
        "UPDATE inventory_items SET inventory_number = ?, name = ?, quantity = ?, " +
        "description = ?, id_audience = ? WHERE id = ?";
    
    private static final String DELETE = "DELETE FROM inventory_items WHERE id = ?";
    
    private ConnectionBuilder builder = new ConnectionBuilder();
    private AudienceDbDAO audienceDao = new AudienceDbDAO();
    
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    
    @Override
    public List<Inventory> findAll() throws SQLException {
        List<Inventory> list = new ArrayList<>();
        List<Audience> audiences = audienceDao.findAll();
        
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillInventory(rs, audiences));
            }
        }
        return list;
    }
    
    @Override
    public Inventory findById(Long id) throws SQLException {
        Inventory inventory = null;
        List<Audience> audiences = audienceDao.findAll();
        
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    inventory = fillInventory(rs, audiences);
                }
            }
        }
        return inventory;
    }
    
    @Override
    public Long insert(Inventory inventory) throws SQLException {
        Long generatedId = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            pst.setString(1, inventory.getInventoryNumber());
            pst.setString(2, inventory.getName());
            pst.setInt(3, inventory.getQuantity());
            pst.setString(4, inventory.getDescription());
            
            if (inventory.getIdAudience() != null) {
                pst.setLong(5, inventory.getIdAudience());
            } else {
                pst.setNull(5, java.sql.Types.BIGINT);
            }
            
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedId = rs.getLong("id");
                }
            }
        }
        return generatedId;
    }
    
    @Override
    public void update(Inventory inventory) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(
                 "UPDATE inventory_items SET inventory_number = ?, name = ?, quantity = ?, description = ?, id_audience = ? WHERE id = ?")) {
            pst.setString(1, inventory.getInventoryNumber());
            pst.setString(2, inventory.getName());
            pst.setInt(3, inventory.getQuantity());
            pst.setString(4, inventory.getDescription());
            if (inventory.getIdAudience() != null) {
                pst.setLong(5, inventory.getIdAudience());
            } else {
                pst.setNull(5, java.sql.Types.BIGINT);
            }
            pst.setLong(6, inventory.getId());
            pst.executeUpdate();
        }
    }
    
    @Override
    public void delete(Long id) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        }
    }
    
    private Inventory fillInventory(ResultSet rs, List<Audience> audiences) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setId(rs.getLong("id"));
        inventory.setInventoryNumber(rs.getString("inventory_number"));
        inventory.setName(rs.getString("name"));
        inventory.setQuantity(rs.getInt("quantity"));
        inventory.setDescription(rs.getString("description"));
        
        Long idAudience = rs.getLong("id_audience");
        if (!rs.wasNull()) {
            inventory.setIdAudience(idAudience);
            Audience audience = audienceDao.findByIdInList(idAudience, audiences);
            inventory.setAudience(audience);
        }
        
        return inventory;
    }
}