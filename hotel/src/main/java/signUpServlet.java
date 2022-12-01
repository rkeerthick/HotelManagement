

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotel.connection;

@WebServlet("/signUpServlet")
public class signUpServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Connection con = connection.getConnect();
			Statement st = con.createStatement();
			st.executeUpdate("insert into userLogin values ('" + request.getParameter("name") + "', '" + request.getParameter("password") + "')");
			out.write("inserted!!");
			response.sendRedirect("rooms.html");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
