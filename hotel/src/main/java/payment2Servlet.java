

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
 * Servlet implementation class payment2Servlet
 */
@WebServlet("/payment2Servlet")
public class payment2Servlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");;
		PrintWriter out = response.getWriter();
		try {
			Connection con = connection.getConnect();
			Statement st = con.createStatement();
			String name = request.getParameter("name");
			String phNo = request.getParameter("phNo");
			String mail = request.getParameter("email");
			String room = "PresidentialSuite";
			
			st.executeUpdate("insert into userRoomDetails values ('" + name + "', '" + phNo + "', '" + mail + "', '" + room + "')");
			
			ResultSet rs = st.executeQuery("select * from hotelRooms where roomType = '" + room + "'");
			
			int count = 0;
			
			while(rs.next()) {
				count += rs.getInt("count");

			}
			
			count = count - 1;

//			out.write("The value is " + count + "\n");
			st.executeUpdate("update hotelRooms set count = " + count + " where roomType = '" + room + "'");
			response.sendRedirect("success.html");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
