package projekat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import projekat.dao.UserDAO;
import projekat.dao.VideoDAO;
import projekat.model.Video;
import projekat.model.Video.Vidljivost;

/**
 * Servlet implementation class VideoServlet
 */
public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String videoURL = request.getParameter("video");
		String slicica = request.getParameter("slicica");
		String opis = request.getParameter("opis");
		String user = request.getParameter("user");
		
		String message = "Uspesno ste dodali video!";
		String status = "success";
		
/*(video,slicica,opis,vidljivost,dozvoljeniKomentari,vidljivostRejtinga,blokiran,brojPregleda,datumKreiranja,vlasnik)*/
		
		String vidljivost = "Javni";
		int dozvoljeniKomentari=1;
		int vidljivostRejtinga=1;
		int blokiran=0;
		int brojPregleda=0;
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String currentTime = sdf.format(dt);
		Video video = new Video(videoURL, slicica, opis, vidljivost, dozvoljeniKomentari, vidljivostRejtinga, blokiran, brojPregleda, currentTime, user);
		
		VideoDAO.add(video);
		
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
