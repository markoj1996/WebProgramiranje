package projekat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projekat.dao.LikeDislikeDAO;
import projekat.dao.LikeKorisniciDAO;
import projekat.model.Korisnik;
import projekat.model.LikeDislike;
import projekat.model.Video;

/**
 * Servlet implementation class LikeServlet
 */
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String like = request.getParameter("like");
		
		HttpSession session = request.getSession();
		Video video = (Video) session.getAttribute("video");
		Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
		
		ArrayList<LikeDislike> lista = LikeDislikeDAO.getAll();
		
		if(like.equals("lajkuj")) {
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String currentTime = sdf.format(dt);
		
			int likel = 1;
		
			LikeDislike likee = new LikeDislike(lista.size()+1,likel, currentTime, video.getID());
		
			LikeDislikeDAO.add(likee);
			LikeKorisniciDAO.add(loggedInUser.getKorisnickoIme(), lista.size()+1);
		}
		else if(like.equals("ponistiLike")) 
		{

			ArrayList<LikeDislike> ld = LikeDislikeDAO.getAll();
			ArrayList<String> lk = LikeKorisniciDAO.getAll();
			
			for(LikeDislike l : ld) 
			{
					for(String s : lk) 
					{
						int idK = Integer.parseInt(s.split(",")[0]);
						String korisnik = s.split(",")[1];
						int likeDislike = Integer.parseInt(s.split(",")[2]);
						if(l.getVideo()==video.getID() && l.getID()==likeDislike) 
						{
							LikeKorisniciDAO.delete(loggedInUser.getKorisnickoIme(),l.getID());
							LikeDislikeDAO.delete(likeDislike, video.getID());
						}
					}
			}
		}
		else if(like.equals("dislikeL")) {
			ArrayList<LikeDislike> ld = LikeDislikeDAO.getAll();
			ArrayList<String> lk = LikeKorisniciDAO.getAll();
			
			for(LikeDislike l : ld) 
			{
				for(String s : lk) 
				{
					int idK = Integer.parseInt(s.split(",")[0]);
					String korisnik = s.split(",")[1];
					int likeDislike = Integer.parseInt(s.split(",")[2]);
					if(l.getVideo()==video.getID() && l.getID()==likeDislike && korisnik.equals(loggedInUser.getKorisnickoIme()))
					{
						LikeDislikeDAO.update(l,0);
					}
				}
			}
		}
		else if(like.equals("LikeD")) 
		{
			ArrayList<LikeDislike> ld = LikeDislikeDAO.getAll();
			ArrayList<String> lk = LikeKorisniciDAO.getAll();
			
			for(LikeDislike l : ld) 
			{
				for(String s : lk) 
				{
					int idK = Integer.parseInt(s.split(",")[0]);
					String korisnik = s.split(",")[1];
					int likeDislike = Integer.parseInt(s.split(",")[2]);
					if(l.getVideo()==video.getID() && l.getID()==likeDislike)
					{
						LikeDislikeDAO.update(l,1);
					}
				}
			}
		}
		else if(like.equals("dislajkuj")) 
		{
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			String currentTime = sdf.format(dt);
		
			int likel = 0;
		
			LikeDislike likee = new LikeDislike(lista.size()+1,likel, currentTime, video.getID());
		
			LikeDislikeDAO.add(likee);
			LikeKorisniciDAO.add(loggedInUser.getKorisnickoIme(), lista.size()+1);
		}
		
	}

}
