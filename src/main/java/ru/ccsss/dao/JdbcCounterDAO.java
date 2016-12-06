package ru.ccsss.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class JdbcCounterDAO implements CounterDAO {
    private final DataSource dataSource;

    @Autowired
    public JdbcCounterDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void incrementCounter() {

        String sql = "UPDATE test_table SET COUNTER= COUNTER+1";

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public void decrementCounter() {

        String sql = "UPDATE test_table SET COUNTER= COUNTER-1";

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public int getCounter() {
        int counter = -1;

        String sql = "SELECT COUNTER FROM test_table";

        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        counter = rs.getInt("COUNTER");
                    }
                    return counter;
                    //// TODO: throw exception if counter column not found
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //    private void checkTableExistance() {
//        String sql = "SELECT * FROM test_table";
//
//        try (Connection conn = dataSource.getConnection()) {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            rs.close();
//            ps.close();
//        } catch (SQLException e) {
//            createTable();
//        }
//
//    }
//    @PostConstruct
//    protected void createTable() {
//        String sql = "CREATE TABLE test_table"
//                + "("
//                + "id integer NOT NULL,"
//                + "counter integer,"
//                + "PRIMARY KEY (id)"
//                + ");"
//                + "INSERT INTO test_table VALUES ( 1, 0)";
//
//        try (Connection conn = dataSource.getConnection()) {
//            try (PreparedStatement ps = conn.prepareStatement(sql)) {
//                ps.executeUpdate();
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
