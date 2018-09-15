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
import projekat.dao.LikeKorisniciDAO;
import projekat.dao.SubscribeDAO;
import projekat.dao.UserDAO;
import projekat.dao.VideoDAO;
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
		
		if(lista.size()!=0) {
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
		}
		
		Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
		
		ArrayList<String> listaSub = SubscribeDAO.getAll();
		String subscribed = "no";
		if(listaSub.size()!=0) {
		for(String s : listaSub) 
		{
			String u=s.split(",")[0];
			String u1=s.split(",")[1];
			if(loggedInUser!=null)
			if(video.getVlasnik().equals(u) && loggedInUser.getKorisnickoIme().equals(u1)) 
			{
				subscribed = "yes";
			}
		}
		}
		
		
		
		if (loggedInUser == null) {
			Map<String, Object> data = new HashMap<>();
			data.put("user", "unauthenticated");
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
			data.put("subscribed", subscribed);

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			return;
		}
		
		ArrayList<LikeDislike> ld = LikeDislikeDAO.getAll();
		ArrayList<String> lk = LikeKorisniciDAO.getAll();
		
		ArrayList<LikeDislike> listaKor = new ArrayList<>();
		
		for(LikeDislike ll : ld) 
		{
			if(ll.getVideo()==id) 
			{
				listaKor.add(ll);
			}
		}
		
		int broj = listaKor.size();
		
		String liked = "liked";
		
		if(broj>0) 
		{
			for(LikeDislike l : ld) 
			{
					for(String s : lk) 
					{
						int idK = Integer.parseInt(s.split(",")[0]);
						String korisnik = s.split(",")[1];
						int likeDislike = Integer.parseInt(s.split(",")[2]);
						if(korisnik.equals(loggedInUser.getKorisnickoIme())) 
						{
							if(l.getVideo()==id && l.getID()==likeDislike)
							{
								if(l.getLike()==1) 
								{
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
									data.put("user", loggedInUser);
									data.put("liked", liked);
									data.put("subscribed", subscribed);

									ObjectMapper mapper = new ObjectMapper();
									String jsonData = mapper.writeValueAsString(data);
									System.out.println(jsonData);

									response.setContentType("application/json");
									response.getWriter().write(jsonData);
									return;
								}
								else if(l.getLike()==0)
								{
									liked = "disliked";
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
									data.put("user", loggedInUser);
									data.put("liked", liked);
									data.put("subscribed", subscribed);

									ObjectMapper mapper = new ObjectMapper();
									String jsonData = mapper.writeValueAsString(data);
									System.out.println(jsonData);

									response.setContentType("application/json");
									response.getWriter().write(jsonData);
									return;
								}
							}
							else 
							{
								continue;
							}
						
						}
						else 
						{
							continue;
						}
						
				}
			}
		}
		else 
		{
			liked = "like";
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
			data.put("user", loggedInUser);
			data.put("liked", liked);
			data.put("subscribed", subscribed);

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
			return;
		}
		liked = "like";
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
		data.put("user", loggedInUser);
		data.put("liked", liked);
		data.put("subscribed", subscribed);

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

		Korisnik vlasnikV= UserDAO.get(vlasnik);
		Video video2 = new Video(id, video, slika, opis, vidljivost, dozKom	, vidRejt, blokiran, brPregleda, datum, vlasnik,vlasnikV);
		
		VideoDAO.update(video2);
		
		String message = "Uspesna prijava!";
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
