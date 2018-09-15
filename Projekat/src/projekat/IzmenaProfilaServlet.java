package projekat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import projekat.dao.UserDAO;
import projekat.model.Korisnik;
import projekat.model.Korisnik.Uloga;

/**
 * Servlet implementation class IzmenaProfilaServlet
 */
public class IzmenaProfilaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
		session.setAttribute("staroKorIme", loggedInUser.getKorisnickoIme());
		Map<String, Object> data = new HashMap<>();
		data.put("user", loggedInUser);
		

		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		String email = request.getParameter("email");
		
		HttpSession session = request.getSession();
		String staroIme = (String) session.getAttribute("staroKorIme");

		String message = "Uspesno ste se registrovali!";
		String status = "success";
		try {
			if ("".equals(userName) || "".equals(password) || "".equals(ime)|| "".equals(prezime)||"".equals(email))
				throw new Exception("Niste popunili sva polja!");
	
			String opis = "";
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String currentTime = sdf.format(dt);
			int blokiran = 0;
			//userName, password, ime,prezime,email,opis,datumRegistracije,role,blokiran
			Korisnik newUser = new Korisnik(userName, password, Uloga.Korisnik, ime,prezime,email,opis,currentTime,blokiran);
			UserDAO.update(newUser, staroIme);
		} catch (Exception ex) {
			message = ex.getMessage();
			status = "failure";
		}

		Map<String, Object> data = new HashMap<>();
		data.put("message", message);
		data.put("status", status);

		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

}
