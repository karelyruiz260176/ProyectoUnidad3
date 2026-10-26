
package Servlet;

import model.Dulces;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


@WebServlet(name = "SvDulces", urlPatterns = {"/SvDulces"})
public class SvDulces extends HttpServlet {
    
    List<Dulces> listaMercancia = new ArrayList<Dulces>();
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=DulceriaPapeleriaCórdova;encrypt=true;trustServerCertificate=true";
    private final String user = "JavaUser";
    private final String password = "password123";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvDulces</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvDulces at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            throw new ServletException("Driver JDBC no encontrado", e);
        }
        
        try(Connection con = (Connection) DriverManager.getConnection(url, user, password) ){
            String sql = "SELECT * FROM Mercancia";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            response.setContentType("text/html;charset=UTF-8");
            listaMercancia.clear();
            while(rs.next()){
                Dulces a = new Dulces(rs.getInt("id"), rs.getString("nombre"), rs.getString("foto"), rs.getInt("precio"));
                listaMercancia.add(a);
            }
            
            request.setAttribute("lista", listaMercancia);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            
            }catch(SQLException e){
            throw new ServletException("Error al conectar con la base de datos");
        }
        
        
        
       // processRequest(request, response);
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
        
        
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }catch(ClassNotFoundException e){
            throw new ServletException("Driver JDBC no encontrado", e);
        }
        
        String nombre = request.getParameter("name");
        String foto = request.getParameter("foto");
        int precio = Integer.parseInt(request.getParameter("price"));
        
        try(Connection con = (Connection) DriverManager.getConnection(url, user, password)){
            String sql ="INSERT INTO Dulces(nombre, foto, precio) Values( ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, foto);
            ps.setInt(3, precio);
            ps.executeUpdate();
            
            doGet(request, response);
            
        }catch(SQLException e){
            throw new ServletException("Error de SQL al insertar: " + e.getMessage(), e);
        }
        
        
        
        
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

    

