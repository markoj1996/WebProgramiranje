package projekat;

import java.io.IOException;
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
 * Servlet implementation class BlockServlet
 */
public class BlockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
		Video video = (Video)session.getAttribute("video");
		String profile = (String) session.getAttribute("profile");
		String statusA = request.getParameter("status");
		String videoS = request.getParameter("video");
		
		String message = "Uspesno ste se registrovali!";
		String status = "success";
		
		Korisnik user = (Korisnik) UserDAO.get(profile);
		
		if(statusA.equals("add")) 
		{
			if(videoS==null) 
			{
				UserDAO.update(user, 1);
			}
			else 
			{
				VideoDAO.update(video, 1);
			}
		}else if(statusA.equals("delete")) 
		{
			if(videoS==null) 
			{
				UserDAO.update(user, 0);
			}
			else 
			{
				VideoDAO.update(video, 0);
			}
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
