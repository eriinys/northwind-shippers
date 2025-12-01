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
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, name);
            ps.setString(2, phone);
            int rows = ps.executeUpdate();

            if(rows > 0){
                System.out.println("New shipper was successfully added\n" +
                        "Row(s) updated: " + rows);
            }

            try(ResultSet keys = ps.getGeneratedKeys()){
                if(keys.next()){
                    int key = keys.getInt(1);
                    //1 grabs the value(index) of the first column of ResultSet row (newly auto-generated ID)
                    System.out.printf("Generated key: %d%n", key);
                }
            }

            try(PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM shippers");
            ResultSet rs = ps2.executeQuery()){
                while(rs.next()){
                    int shipperID = rs.getInt("ShipperID");
                    String CompanyName = rs.getString("CompanyName");
                    String Phone = rs.getString("Phone");
                    System.out.printf("%d %s %s%n", shipperID, CompanyName, Phone);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeShipperInfo(int id, String newPhone){
        String sql = "UPDATE shippers SET Phone = ? " +
                "WHERE ShipperID = ?";

        try(Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, newPhone);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.printf("Row(s) updated: %d%n", rows);

            try(PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM shippers");
                ResultSet rs = ps2.executeQuery()){
                while(rs.next()){
                    int shipperID = rs.getInt("ShipperID");
                    String CompanyName = rs.getString("CompanyName");
                    String Phone = rs.getString("Phone");
                    System.out.printf("%d %s %s%n", shipperID, CompanyName, Phone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteShipper(int id){
        String sql = "DELETE FROM shippers " +
                "WHERE ShipperID = ?";

        try(Connection conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.printf("Row(s) deleted: %d%n", rows);

            try(PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM shippers");
                ResultSet rs = ps2.executeQuery()){
                while(rs.next()){
                    int shipperID = rs.getInt("ShipperID");
                    String CompanyName = rs.getString("CompanyName");
                    String Phone = rs.getString("Phone");
                    System.out.printf("%d %s %s%n", shipperID, CompanyName, Phone);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
