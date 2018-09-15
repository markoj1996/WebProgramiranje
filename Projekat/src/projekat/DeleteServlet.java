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

import projekat.dao.CommentDAO;
import projekat.dao.LikeDislikeDAO;
import projekat.dao.LikeKorisniciDAO;
import projekat.dao.VideoDAO;
import projekat.model.Video;

/**
 * Servlet implementation class DeleteServlet
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Video video = (Video) session.getAttribute("video");
		
		int id = LikeDislikeDAO.get(video);
		if(id!=0) 
		{
			LikeKorisniciDAO.delete(id);
			LikeDislikeDAO.delete(video);
		}
		CommentDAO.delete(video);
		VideoDAO.delete(video);
		
		Map<String, Object> data = new HashMap<>();
		data.put("message", "obrisan");
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

}
