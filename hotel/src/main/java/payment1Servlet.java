

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotel.connection;

@WebServlet("/payment1Servlet")
public class payment1Servlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");;
		PrintWriter out = response.getWriter();
		try {
			Connection con = connection.getConnect();
			Statement st = con.createStatement();
			String name = request.getParameter("name");
			String phNo = request.getParameter("phNo");
			String mail = request.getParameter("email");
			String room = "PrimeSuite";
			
			st.executeUpdate("insert into userRoomDetails values ('" + name + "', '" + phNo + "', '" + mail + "', '" + room + "')");
			
			ResultSet rs = st.executeQuery("select * from hotelRooms where roomType = '" + room + "'");
			
			int count = 0;
			
			while(rs.next()) {
				count += rs.getInt("count");
//				out.write("The value is " + count + "\n");

			}
			
			count = count - 1;

//			out.write("The value is " + count + "\n");
			if(count > 0) {
				st.executeUpdate("update hotelRooms set count = " + count + " where roomType = '" + room + "'");
				response.sendRedirect("success.html");
			}
			else {
				response.sendRedirect("notSuccess.html");
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}
