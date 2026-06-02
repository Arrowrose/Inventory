package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Audience;

public class AudienceDbDAO implements RepositoryDAO<Audience> {
    
    private static final String SELECT_ALL = "SELECT id, number, responsible FROM audiences ORDER BY number ASC";
    private static final String SELECT_BY_ID = "SELECT id, number, responsible FROM audiences WHERE id = ?";
    private static final String INSERT = "INSERT INTO audiences(number, responsible) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE audiences SET number = ?, responsible = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM audiences WHERE id = ?";
    
    private ConnectionBuilder builder = new ConnectionBuilder();
    
    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }
    
    @Override
    public List<Audience> findAll() throws SQLException {
        List<Audience> list = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_ALL);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(fillAudience(rs));
            }
        }
        return list;
    }
    
    @Override
    public Audience findById(Long id) throws SQLException {
        Audience audience = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_BY_ID)) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    audience = fillAudience(rs);
                }
            }
        }
        return audience;
    }
    
    @Override
    public Long insert(Audience audience) throws SQLException {
        Long generatedId = null;
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT, new String[]{"id"})) {
            pst.setString(1, audience.getNumber());
            pst.setString(2, audience.getResponsible());
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
    public void update(Audience audience) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(
                 "UPDATE audiences SET number = ?, responsible = ? WHERE id = ?")) {
            pst.setString(1, audience.getNumber());
            pst.setString(2, audience.getResponsible());
            pst.setLong(3, audience.getId());
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
    
    
    private Audience fillAudience(ResultSet rs) throws SQLException {
        Audience audience = new Audience();
        audience.setId(rs.getLong("id"));
        audience.setNumber(rs.getString("number"));
        audience.setResponsible(rs.getString("responsible"));
        return audience;
    }
    
    // Поиск аудитории в списке по id (для инвентаря)
    public Audience findByIdInList(Long id, List<Audience> audiences) {
        if (audiences != null) {
            for (Audience a : audiences) {
                if (a.getId().equals(id)) {
                    return a;
                }
            }
        }
        return null;
    }
}