package projekat;

import java.io.IOException;
import java.util.ArrayList;
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
import projekat.dao.VideoDAO;
import projekat.model.Korisnik;
import projekat.model.Video;

/**
 * Servlet implementation class UserProfileServlet
 */
public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String profile = (String) session.getAttribute("profile");
		Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
		
		ArrayList<Video> lista = VideoDAO.getAll();
		ArrayList<Video>listaKorsnika = new ArrayList<>();
		
		for(Video v : lista) 
		{
			if(v.getVlasnik().equals(profile)) 
			{
				listaKorsnika.add(v);
			}
		}
		
		int size = listaKorsnika.size();
		
		ArrayList<String> listaSub = SubscribeDAO.getAll();
		String subscribed = "no";
		if(listaSub.size()!=0) {
		for(String s : listaSub) 
		{
			String u=s.split(",")[0];
			String u1=s.split(",")[1];
			if(loggedInUser!=null)
			if(profile.equals(u) && loggedInUser.getKorisnickoIme().equals(u1)) 
			{
				subscribed = "yes";
			}
		}
		}
		
		if(loggedInUser==null) 
		{
			Map<String, Object> data = new HashMap<>();
			data.put("userName", UserDAO.get(profile));
			data.put("status", "success");
			data.put("loggedUser", "null");
			data.put("video",listaKorsnika);
			data.put("size",size);

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			return;
		}else if(loggedInUser.getKorisnickoIme().equals(profile)) 
		{
			Map<String, Object> data = new HashMap<>();
			data.put("userName", UserDAO.get(profile));
			data.put("status", "success");
			data.put("loggedUser", "m");
			data.put("video",listaKorsnika);
			data.put("size",size);

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			return;
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("userName", UserDAO.get(profile));
		data.put("status", "success");
		data.put("user", loggedInUser);
		data.put("subscribed", subscribed);
		data.put("video",listaKorsnika);
		data.put("size",size);

		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
//		response.sendRedirect("./Login.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String vlasnik = request.getParameter("vlasnik");
		
		String message = "Uspesna prijava!";
		String status = "success";
		
		if(!vlasnik.equals("lu")) 
		{
			HttpSession session = request.getSession();
			session.setAttribute("profile", vlasnik);
			
			Map<String, Object> data = new HashMap<>();
			data.put("message", message);
			data.put("status", status);
			data.put("vlasnik", vlasnik);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}else 
		{
			HttpSession session = request.getSession();
			Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
			session.setAttribute("profile", loggedInUser.getKorisnickoIme());
			
			Map<String, Object> data = new HashMap<>();
			data.put("message", message);
			data.put("status", status);
			data.put("vlasnik", loggedInUser.getKorisnickoIme());
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
	}

}
