
package controllers;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import oder.OderDAO;
import oder.OrderDTO;
import oder.OrderDetails;
import shopping.Cart;
import shopping.ProductDAO;
import shopping.ProductDTO;
import user.UserDTO;


public class CheckOutController extends HttpServlet {

    private static final String ERROR = "cart.jsp";
    private static final String LOGIN = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            ProductDAO productDAO = new ProductDAO();
            int total = Integer.parseInt(request.getParameter("total"));
            java.util.Date utilDate = new java.util.Date();
            Date oderDate = new java.sql.Date(utilDate.getTime());
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null) {
                url = LOGIN;
            } else {
                boolean checkquantity = true;
                Cart cart = (Cart) session.getAttribute("CART");

                for (ProductDTO product : cart.getCart().values()) {
                    int quantity = product.getQuantity();
                    ProductDTO checkProduct = (ProductDTO) productDAO.searchByProductID(product.getProductID());
                    int quantityproduct = checkProduct.getQuantity();
                    int finalquantity = quantityproduct - quantity;
                    if (finalquantity < 0) {
                        url = ERROR;
                        checkquantity = false;
                    }
                }
                if (checkquantity == true) {
                    OderDAO dao = new OderDAO();
                    int oderID = dao.getMaxOderID() + 1;
                    OrderDTO newOder = new OrderDTO(oderID, loginUser.getUserId(), oderDate, total);
                    boolean checkOrder = dao.insertOrder(newOder);
                    if (checkOrder) {
                        boolean check = true;
                        for (ProductDTO product : cart.getCart().values()) {
                            int detailID = dao.getMaxDetailID() + 1;
                            String productID = product.getProductID();
                            int price = product.getPrice();
                            int quantity = product.getQuantity();
                            
                            ProductDTO pro = productDAO.searchByProductID(productID);
                            int quantityProduct = pro.getQuantity();
                            int finalQuantity =  quantityProduct - quantity;   
                            pro.setQuantity(finalQuantity);
                            boolean updateQuantity = productDAO.updateQuantity(pro);
                            OrderDetails details = new OrderDetails(detailID, oderID, productID, price, quantity);
                            boolean checkOrderDetail = dao.insertDetails(details);
                            if(!checkOrderDetail || !updateQuantity){
                                check =false;
                                break;
                            }
                            
                        }
                        if(check){
                            request.setAttribute("CHECK_OUT", "Purchase successfully");
                            url="checkout.jsp";
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("Error at CheckOutController:" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
