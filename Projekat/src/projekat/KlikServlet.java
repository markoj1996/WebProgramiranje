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

import projekat.dao.LikeDislikeDAO;
import projekat.model.Korisnik;
import projekat.model.LikeDislike;
import projekat.model.Video;

/**
 * Servlet implementation class KlikServlet
 */
public class KlikServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Video video = (Video) session.getAttribute("video");
		String video2 = video.getVideoURL();
		String slika = video.getSlikaURL();
		String opis = video.getOpis();
		String vidljivost = video.getVidljivost();
		int dozKom = video.getDozvoljeniKomentari();
		int vidRejt = video.getVidljivostRejtinga();
		int blokiran = video.getBlokiran();
		int brPregleda = video.getBrojPregleda();
		String datum = video.getDatumKreiranja();
		String vlasnik = video.getVlasnik();
		int id = video.getID();
		
		ArrayList<LikeDislike> lista = LikeDislikeDAO.getAll();
		int brojLike=0;
		int brojDislike=0;
		
		for(LikeDislike l : lista)
		{
			if(l.getVideo()==video.getID())
			{
				if(l.getLike()==1)
				{
					brojLike+=1;
				}
				else
				{
					brojDislike+=1;
				}
			}
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("status", "success");
		data.put("video", video);
		data.put("slika", slika);
		data.put("opis", opis);
		data.put("vidljivost", vidljivost);
		data.put("dozKom", dozKom);
		data.put("vidRejt", vidRejt);
		data.put("blokiran", blokiran);
		data.put("brPregleda", brPregleda);
		data.put("datum", datum);
		data.put("vlasnik", vlasnik);
		data.put("id", id);
		data.put("brojLike", brojLike);
		data.put("brojDislike", brojDislike);

		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String video = request.getParameter("video");
		String slika = request.getParameter("slika");
		String opis = request.getParameter("opis");
		String vidljivost = request.getParameter("vidljivost");
		int dozKom = Integer.parseInt(request.getParameter("dozKom"));
		int vidRejt = Integer.parseInt(request.getParameter("vidRejt"));
		int blokiran = Integer.parseInt(request.getParameter("blokiran"));
		int brPregleda = Integer.parseInt(request.getParameter("brPregleda"));
		String datum = request.getParameter("datum");
		String vlasnik = request.getParameter("vlasnik");
		int id = Integer.parseInt(request.getParameter("id"));

		Video video2 = new Video(id, video, slika, opis, vidljivost, dozKom	, vidRejt, blokiran, brPregleda, datum, vlasnik);
		
		String message = "Uspesna prijava!";
//		String link = "<a href=\"WebShopServlet\">Nastavak</a>";
		String status = "success";
		
		HttpSession session = request.getSession();
		session.setAttribute("video", video2);
		
		Map<String, Object> data = new HashMap<>();
		data.put("message", message);
		data.put("status", status);
		data.put("video", video);
		data.put("slika", slika);
		data.put("opis", opis);
		data.put("vidljivost", vidljivost);
		data.put("dozKom", dozKom);
		data.put("vidRejt", vidRejt);
		data.put("blokiran", blokiran);
		data.put("brPregleda", brPregleda);
		data.put("datum", datum);
		data.put("vlasnik", vlasnik);
		data.put("id", id);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
	}

}
