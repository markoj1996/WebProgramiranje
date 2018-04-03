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

import org.apache.catalina.Session;

import com.fasterxml.jackson.databind.ObjectMapper;

import projekat.dao.LikeDislikeDAO;
import projekat.dao.LikeKorisniciDAO;
import projekat.model.Korisnik;
import projekat.model.LikeDislike;

/**
 * Servlet implementation class ChannelServlet
 */
public class ChannelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Korisnik loggedInUser = (Korisnik) session.getAttribute("user");
		if (loggedInUser == null) {
			Map<String, Object> data = new HashMap<>();
			data.put("status", "unauthenticated");

			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
//			response.sendRedirect("./Login.html");
			return;
		}
		
		ArrayList<Integer> lista = LikeKorisniciDAO.getAll();
		
		for(int like : lista)
		{
			LikeDislike ld = LikeDislikeDAO.get(like);
			ArrayList<LikeDislike> listalike = new ArrayList<>();
			listalike.add(ld);
			loggedInUser.setLikeVideo(listalike);
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("status", "success");
		data.put("user", loggedInUser);

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
