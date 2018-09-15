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

import projekat.dao.SubscribeDAO;
import projekat.dao.UserDAO;
import projekat.model.Korisnik;
import projekat.model.Video;
import projekat.model.Korisnik.Uloga;

/**
 * Servlet implementation class SubscribeServlet
 */
public class SubscribeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
		Video video = (Video) session.getAttribute("video");
		String statusA = request.getParameter("status");
		
		String message = "Uspesno ste se registrovali!";
		String status = "success";
		
		if(statusA.equals("add")) 
		{
			SubscribeDAO.add(video.getVlasnik(), loggedInUser.getKorisnickoIme());
		}else if(statusA.equals("delete")) 
		{
			SubscribeDAO.delete(video.getVlasnik(), loggedInUser.getKorisnickoIme());
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
