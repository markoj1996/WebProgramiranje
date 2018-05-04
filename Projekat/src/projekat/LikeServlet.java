package projekat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projekat.dao.LikeDislikeDAO;
import projekat.model.LikeDislike;

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
		int brLike = Integer.parseInt(request.getParameter("brLike"));
		
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String currentTime = sdf.format(dt);
		
		int likel = 1;
		
		LikeDislike like = new LikeDislike(likel, currentTime, id);
		
		LikeDislikeDAO.add(like);
		
		
		
	}

}
