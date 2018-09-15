package projekat;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import projekat.dao.UserDAO;
import projekat.dao.VideoDAO;
import projekat.model.Korisnik;
import projekat.model.Video;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Korisnik loggedIn = (Korisnik) session.getAttribute("user");
		
			String nameFilter = request.getParameter("nameFilter");
			if (nameFilter == null)
				nameFilter = "";
			
			List<Korisnik> filteredKorisnici = UserDAO.getAll(nameFilter,1);

			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("size", filteredKorisnici.size());
			data.put("users", filteredKorisnici);
			data.put("user", loggedIn);

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
