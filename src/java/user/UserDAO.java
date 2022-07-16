package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import shopping.ProductDTO;
import utils.DBUtils;

public class UserDAO {

    // Kiểm tra thông tin để User đăng nhập
    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT fullName, roleID "
                        + " FROM tblUsers "
                        + " WHERE userID =? AND password =? ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, userID);
                pstm.setString(2, password);
                resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                    String fullName = resultSet.getString("fullName");
                    String roleID = resultSet.getString("roleID");
                    user = new UserDTO(userID, fullName, roleID);
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public UserDTO getUserInfor(String userID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT fullName, roleID "
                        + " FROM tblUsers "
                        + " WHERE userID= ? ";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, userID);
                resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                    String fullName = resultSet.getString("fullName");
                    String roleID = resultSet.getString("roleID");
                    user = new UserDTO(userID, fullName, "", "", "", roleID);
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public boolean insertUser(UserDTO user) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblUsers(userID, fullName, address, phone, password, roleID) "
                        + "VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, user.getUserId());
                stm.setString(2, user.getFullName());
                stm.setString(3, user.getAddress());
                stm.setString(4, user.getPhone());
                stm.setString(5, user.getPassword());
                stm.setString(6, user.getRoleId());
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

    public List<ProductDTO> getSearchList(String searchFullName) throws SQLException {
        List<ProductDTO> ListProduct = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet resultSet = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT productID, productName, image, price, quantity, categoryID "
                        + " FROM tblProducts "
                        + " WHERE productName like ?";
                pstm = conn.prepareStatement(sql);
                pstm.setString(1, "%" + searchFullName + "%");
                resultSet = pstm.executeQuery();
                while (resultSet.next()) {
                    String productID = resultSet.getString("productID");
                    String productName = resultSet.getNString("productName");
                    String image = resultSet.getString("image");
                    int price = resultSet.getInt("price");
                    int quantity = resultSet.getInt("quantity");
                    String categoryID = resultSet.getString("categoryID");
                    ListProduct.add(new ProductDTO(productID, productName, image, price, quantity, categoryID));
                }
            }
        } catch (Exception event) {
            event.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return ListProduct;
    }
}
