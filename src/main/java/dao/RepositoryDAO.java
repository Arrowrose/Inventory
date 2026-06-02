package dao;

import java.util.List;
import java.sql.SQLException;

public interface RepositoryDAO<T> {
    Long insert(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void delete(Long id) throws SQLException;
    T findById(Long id) throws SQLException;
    List<T> findAll() throws SQLException;
}