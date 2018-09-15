package projekat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import projekat.dao.CommentDAO;
import projekat.dao.UserDAO;
import projekat.model.Komentar;
import projekat.model.Korisnik;
import projekat.model.Korisnik.Uloga;
import projekat.model.Video;

/**
 * Servlet implementation class CommentServlet
 */
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Video video = (Video) session.getAttribute("video");
		
		ArrayList<Komentar> lista;
		
		String sort = request.getParameter("sort");
		if (sort == null) 
		{
			lista = CommentDAO.getAll();
			int size = lista.size();
			ArrayList<Komentar> listaZaVideo = new ArrayList<>();
			for(Komentar k : lista) 
			{
				if(k.getVideo()==video.getID()) 
				{
					listaZaVideo.add(k);
				}
			}
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("com",listaZaVideo);
			data.put("size",size);

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
		else 
		{
			lista = CommentDAO.getAll(sort);
			int size = lista.size();
			ArrayList<Komentar> listaZaVideo = new ArrayList<>();
			for(Komentar k : lista) 
			{
				if(k.getVideo()==video.getID()) 
				{
					listaZaVideo.add(k);
				}
			}
			
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("com",listaZaVideo);
			data.put("size",size);

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comment = request.getParameter("comment");
		String video = request.getParameter("video");
		String message = "Uspesno komentarisano!";
		String status = "success";
		
		HttpSession session = request.getSession();
		Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
		
		try {
			if ("".equals(comment))
			{
				throw new Exception("Niste popunili sva polja!");
			}
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String currentTime = sdf.format(dt);
			
			Komentar komentar = new Komentar(comment, currentTime,loggedInUser,Integer.parseInt(video));
			CommentDAO.add(komentar);
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
