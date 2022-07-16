package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shopping.ProductDTO;
import java.util.List;
import javax.servlet.http.HttpSession;
import shopping.Category;
import shopping.ProductDAO;
import user.UserDTO;

public class ViewController extends HttpServlet {

    private static final String USER = "view.jsp";
    private static final String ADMIN = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = USER;
        try {
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> ListProduct = productDAO.getListProduct();
            List<Category> ListCategory = productDAO.getListCategory();
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

            if (loginUser == null || "US".equals(loginUser.getRoleId())) {
                if (ListProduct != null) {
                    session.setAttribute("LIST_CATE", ListCategory);
                    session.setAttribute("LIST_ITEM", ListProduct);
                    url = USER;
                }
            } else if ("AD".equals(loginUser.getRoleId())) {
                if (ListProduct != null) {
                    session.setAttribute("LIST_ITEM", ListProduct);
                    session.setAttribute("LIST_CATE", ListCategory);
                    url = ADMIN;
                }
            }
        } catch (Exception e) {
            log("Error at ViewController: " + e.toString());
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