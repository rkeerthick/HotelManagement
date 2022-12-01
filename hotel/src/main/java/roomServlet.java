

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotel.connection;

/**
 * Servlet implementation class roomServlet
 */
@WebServlet("/roomServlet")
public class roomServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Connection con = connection.getConnect();
			Statement st = con.createStatement();
			String room = request.getParameter("room");
//			out.write(s);
			ResultSet rs = st.executeQuery("select * from hotelRooms where roomType = '" + room + "'");
			
			int count = 0;
			
			while(rs.next()) {
				count += rs.getInt("count");
//				out.write("The value is " + count + "\n");

			}
			
			int nCnt = Integer.parseInt(request.getParameter("val"));
			int upCnt = nCnt + count;

			st.executeUpdate("update hotelRooms set count = " + upCnt + " where roomType = '" + room +"'");
			response.sendRedirect("login.html");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
