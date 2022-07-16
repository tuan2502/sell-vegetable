package oder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

public class OderDAO {

    public int getMaxDetailID() throws SQLException {
        int oderdetail = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT MAX(detailID) AS MAXDETAILID "
                        + " FROM tblOrderDetail ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    oderdetail = rs.getInt("MAXDETAILID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return oderdetail;

    }

    public int getMaxOderID() throws SQLException {
        int oderID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT MAX(orderID) AS  MAXID "
                        + " FROM tblOrders ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    oderID = rs.getInt("MAXID");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return oderID;

    }

    public boolean insertOrder(OrderDTO order) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblOrders(orderID, userID, date, total"
                        + " VALUES(?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, order.getOrderID());
                stm.setString(2, order.getUserID());
                stm.setDate(3, (Date) order.getDate());
                stm.setInt(4, order.getTotal());

                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean insertDetails(OrderDetails detail) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblOrderDetails(detailID, orderID, productID, price, quantity"
                        + " VALUES(?,?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, detail.getDetailID());
                stm.setInt(2, detail.getOrderID());
                stm.setString(3, detail.getProductID());
                stm.setInt(4, detail.getPrice());
                stm.setInt(5, detail.getQuantity());

                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

}
