import org.apache.commons.dbcp2.BasicDataSource;

import java.util.*;
import java.sql.*;

public class ShippersDataManager {
    private final BasicDataSource dataSource;
    private Shippers shipper;

    public ShippersDataManager(String user, String password) {
        this.dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername(user);
        dataSource.setPassword(password);
    }


    public void addNewShipper(String name, String phone){
        String sql ="INSERT INTO shippers (CompanyName, Phone) " +
                "VALUES (?, ?)";

        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
