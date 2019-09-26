package no.hvl.dat108;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = { "/login" })
public class Login extends HttpServlet {

	String passord;

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		ServletConfig config = getServletConfig();
		passord = config.getInitParameter("passord");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String error = request.getParameter("error");
		
		response.setContentType("text/html; charset=ISO-8859-1");
		
		if (error != null) {
			if (error.equals("1")) {
				writer.println("Passordet du skrev inn var feil. Pr�v igjen:");
			}
		}

		writer.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Handleliste login</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "	<h1>Login</h1>\r\n"
				+ "	<form action=\"login\" method=\"post\">\r\n" + "		<fieldset>\r\n" + "			<p>\r\n"
				+ "				<label for=\"passwordField\">Skriv inn passord:</label>\r\n" + "			</p>\r\n"
				+ "			<p>\r\n"
				+ "				<input id=\"passwordField\" type=\"password\" name=\"passord\" />\r\n"
				+ "			</p>\r\n" + "			<p>\r\n"
				+ "				<input type=\"submit\" value=\"Login\" />\r\n" + "			</p>\r\n"
				+ "		</fieldset>\r\n" + "	</form>\r\n" + "</body>\r\n" + "</html>");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pass = request.getParameter("passord");

		if (pass.compareTo(passord) == 0) {
			logInn(request, response);
			response.sendRedirect("handle");
		} else {
			response.sendRedirect("login?error=1");
		}

	}
	
	protected void logUt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession sesjon = request.getSession(false);
		sesjon.invalidate();
	}


	protected void logInn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		logUt(request, response);
		HttpSession sesjon = request.getSession(true);
		sesjon.setAttribute("login", 1);
		
	}
	
}
