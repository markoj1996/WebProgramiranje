package projekat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		ArrayList<Video> lista = VideoDAO.getAll();
		
		int size = lista.size();
		
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("video",lista);
			data.put("size",size);

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
