import java.io.*;
import java.sql.*;
import javax.servlet.*;
public class LoginServlet extends GenericServlet
{
     Connection con;
     public void init()
     {
       try{
           Class.forName("oracle.jdbc.driver.OracleDriver");
           con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");

          }
        catch(Exception e)
        {
          System.out.println(e);
        }
     }
     public void service(ServletRequest request,ServletResponse response)
     {
       try{
               
            String s1=request.getParameter("uname");        
            String s2=request.getParameter("pwd");    
             PreparedStatement pstmt=con.prepareStatement("select * from uinfo where uname=?  and pwd=?"); 
             pstmt.setString(1,s1);
             pstmt.setString(2,s2);
             ResultSet rs=pstmt.executeQuery();  
            PrintWriter  pw=response.getWriter();
            pw.println("<html><body bgcolor=lightyellow text=red><center><h1>");
            if(rs.next())
            {
            pw.println("wellcome"+s1);
             }
            else
             {
              pw.println("invalid username and passward");
               pw.println("<a href=registration.html>Registration</a>");
              }
               pw.println("</h1></center></body></html>");
          }
        catch(Exception e)
        {
          System.out.println(e);
        }

     }

}