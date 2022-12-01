

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotel.connection;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Connection con = connection.getConnect();
			Statement st = con.createStatement();
//			st.executeUpdate("insert into userLogin values ('" + request.getParameter("name") + "', '" + request.getParameter("password") + "')");
//			out.write("inserted!!");
			
//			boolean flag = false;
//			String uName = request.getParameter("name");
//			ResultSet rs = st.executeQuery("select * from userLogin");
//			while(rs.next()) {
////				String name = rs.getString("uname")
//				if(rs.getString("uname").equals(uName) ) {
//					flag = true;
//					response.sendRedirect("rooms.html");
//				}
//			}
			

			String uName = request.getParameter("name");
			System.out.println("The value is : '" + uName +"'");
			
			if(request.getParameter("check").equals("Admin") && !uName.equals("")) {
				response.sendRedirect("admin.html");
			}
			else if(request.getParameter("check").equals("Login") && !uName.equals("")) {
				boolean flag = false;
				ResultSet rs = st.executeQuery("select * from userLogin");
				while(rs.next()) {
//					String name = rs.getString("uname")
					if(rs.getString("uname").equals(uName)) {
						flag = true;
						response.sendRedirect("rooms.html");
					}
				}
				if(flag == false) {
					response.sendRedirect("signup.html");
				}
			}
			else {
				response.sendRedirect("login.html");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
