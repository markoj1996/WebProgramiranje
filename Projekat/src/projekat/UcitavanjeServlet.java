package projekat;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class UcitavanjeServlet
 */
public class UcitavanjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nameFilterr = (String) session.getAttribute("nameFilter");
		Korisnik loggedIn = (Korisnik) session.getAttribute("user");
		
		if(nameFilterr==null) 
		{
			String sort = request.getParameter("sort");
			if (sort == null)
				sort = "";
			String nameFilter = request.getParameter("nameFilter");
			if (nameFilter == null)
				nameFilter = "";
			int lowFilter = 0;
			try {
				lowFilter = Integer.parseInt(request.getParameter("lowFilter"));
			} catch (Exception ex) {}
			int highFilter = Integer.MAX_VALUE;
			try {
				highFilter = Integer.parseInt(request.getParameter("highFilter"));		
			} catch (Exception ex) {}
			
			List<Video> filteredVideos = VideoDAO.getAll(nameFilter,lowFilter,highFilter,sort);

			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("size", filteredVideos.size());
			data.put("video", filteredVideos);
			data.put("user", loggedIn);

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
		else 
		{
			String sort = request.getParameter("sort");
			if (sort == null)
				sort = "";
			if (nameFilterr == null)
				nameFilterr = "";
			int lowFilter = 0;
			try {
				lowFilter = Integer.parseInt(request.getParameter("lowFilter"));
			} catch (Exception ex) {}
			int highFilter = Integer.MAX_VALUE;
			try {
				highFilter = Integer.parseInt(request.getParameter("highFilter"));		
			} catch (Exception ex) {}
			
			List<Video> filteredVideos = VideoDAO.getAll(nameFilterr,lowFilter,highFilter,sort);

			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("size", filteredVideos.size());
			data.put("video", filteredVideos);
			data.put("user", loggedIn);

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
