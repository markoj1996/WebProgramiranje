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
import projekat.dao.VideoDAO;
import projekat.model.Korisnik;
import projekat.model.Video;
import projekat.model.Korisnik.Uloga;

/**
 * Servlet implementation class IzmenaVideaServlet
 */
public class IzmenaVideaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
		Video video = (Video) session.getAttribute("video");
		session.setAttribute("stariId", video.getID());
		Map<String, Object> data = new HashMap<>();
		data.put("video", video);
		

		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String video = request.getParameter("video");
		String slicica = request.getParameter("slicica");
		String opis = request.getParameter("opis");
		String vidljivost = request.getParameter("vidljivost");
		String vid = null;
		String komentari = request.getParameter("komentari");
		int kom=Integer.parseInt(komentari);
		String rejting = request.getParameter("rejting");
		int rej=Integer.parseInt(rejting);
		
		HttpSession session = request.getSession();
		int stariId =  (int) session.getAttribute("stariId");
		Video videoV = (Video) session.getAttribute("video");
		
		String message = "Uspesno ste se registrovali!";
		String status = "success";
		try {
			if ("".equals(video) || "".equals(slicica) || "".equals(opis))
				throw new Exception("Niste popunili sva polja!");
			if(Integer.parseInt(vidljivost)==0) 
			{
				vid="Privatni";
			}
			else if(Integer.parseInt(vidljivost)==1){
				vid="Javni";
			}
			
			Video newVideo = new Video(videoV.getID(),video, slicica, opis, vid, kom, rej, 0, videoV.getBrojPregleda(), videoV.getDatumKreiranja(), videoV.getVlasnik(), videoV.getVlasnikV());
			VideoDAO.update(newVideo);
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
