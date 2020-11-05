package exam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class loginservlet
 */
@WebServlet("/loginservlet")
public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String u=request.getParameter("username");
		String p=request.getParameter("password");
		try{
			String s1="org.apache.derby.jdbc.EmbeddedDriver";
			Class.forName(s1);
			Connection con=DriverManager.getConnection("jdbc:derby:C:/Users/Hp/MyDB;create=true","akhila", "admin");
			Statement stmt=con.createStatement();
			ResultSet rs =stmt.executeQuery("select * from users");
			 
			String username,password;
			int flag=0;
			while(rs.next())
			{
				username=rs.getString(1);
				password=rs.getString(2);
				if(u.equals(username)&&p.equals(password))
				{
					response.sendRedirect("success.jsp");
					System.out.println("welcome:"+u);
					flag=1;
					break;
				}
			}
			if(flag==0)
			{
				response.sendRedirect("error.jsp");
				System.out.println("error");
			}
			con.close();
			
		}catch(SQLException e){
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
