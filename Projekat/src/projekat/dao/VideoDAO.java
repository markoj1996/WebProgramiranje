package projekat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import projekat.model.Korisnik;
import projekat.model.Korisnik.Uloga;
import projekat.model.Video;
import projekat.model.Video.Vidljivost;

public class VideoDAO {

	public static Video get(int id) {
		Connection conn = ConnManager.getConnection();

//		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			// SQL upit
			// OBAVEZNO PISATI NAZIVE TABELA I KOLONA IDENTICNO (cak i po case-u) KAO U SKRIPTI ZA KREIRANJE BAZE!
//			String query = "SELECT password, role FROM users WHERE userName = '" + userName + "'"; // osetljivo na SQL injection napad!
//			System.out.println(query);
//
//			// kreiranje SQL naredbe, jednom za svaki SQL upit
//			stmt = conn.createStatement();
//			// izvrsavanje naredbe i prihvatanje rezultata (SELECT), jednom za svaki SQL upit
//			rset = stmt.executeQuery(query);
/*videoURL, slicica, opis, vidljivost, dozvoljeniKomentari, vidljivostRejtinga, blokiran, brojPregleda, out, user);
		*/
			String query = "SELECT video,slicica,opis,vidljivost,dozvoljeniKomentari,vidljivostRejtinga,blokiran,brojPregleda,datumKreiranja,vlasnik FROM Video WHERE ID = ?"; // bezbedno u odnosu na SQL injection napad

			// kreiranje SQL naredbe, jednom za svaki SQL upit
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (SELECT), jednom za svaki SQL upit
			rset = pstmt.executeQuery();

			// citanje rezultata upita
			if (rset.next()) {
				int index = 1;
				String video = rset.getString(index++);
				String slicica = rset.getString(index++);
				String opis = rset.getString(index++);
				String vidljivost = rset.getString(index++);
				int dozvoljeniKomentari = rset.getInt(index++);
				int vidljivostRejtinga = rset.getInt(index++);
				int blokiran = rset.getInt(index++);
				int brojPregleda = rset.getInt(index++);
				String datumKreiranja = rset.getString(index++);
				String vlasnik = rset.getString(index++);
//				String password = rset.getString("password");
//				Role role = Role.valueOf(rset.getString("role"));

				
				return new Video(id,video,slicica,opis,vidljivost,dozvoljeniKomentari,vidljivostRejtinga,blokiran,brojPregleda,datumKreiranja,vlasnik);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			// zatvaranje naredbe i rezultata
//			try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return null;
	}

	public static ArrayList<Video> getAll() {
		
		ArrayList<Video> lista = new ArrayList<>();
		
		Connection conn = ConnManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT id,video,slicica,opis,vidljivost,dozvoljeniKomentari,vidljivostRejtinga,blokiran,brojPregleda,datumKreiranja,vlasnik FROM Video"; // bezbedno u odnosu na SQL injection napad

			// kreiranje SQL naredbe, jednom za svaki SQL upit
			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (SELECT), jednom za svaki SQL upit
			rset = pstmt.executeQuery();

			// citanje rezultata upita
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String videos = rset.getString(index++);
				String slicica = rset.getString(index++);
				String opis = rset.getString(index++);
				String vidljivost = rset.getString(index++);
				int dozvoljeniKomentari = rset.getInt(index++);
				int vidljivostRejtinga = rset.getInt(index++);
				int blokiran = rset.getInt(index++);
				int brojPregleda = rset.getInt(index++);
				String datumKreiranja = rset.getString(index++);
				String vlasnik = rset.getString(index++);
//				String password = rset.getString("password");
//				Role role = Role.valueOf(rset.getString("role"));

				
				Video video = new Video(id,videos,slicica,opis,vidljivost,dozvoljeniKomentari,vidljivostRejtinga,blokiran,brojPregleda,datumKreiranja,vlasnik);
				lista.add(video);
						
			}
			return lista;
		}
			catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
				return lista;
			} finally {
				// zatvaranje naredbe i rezultata
//				try {stmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}

		
	}

	public static boolean add(Video video) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query = "INSERT INTO Video (video,slicica,opis,vidljivost,dozvoljeniKomentari,vidljivostRejtinga,blokiran,brojPregleda,datumKreiranja,vlasnik) VALUES (?, ?, ?,?,?,?,?,?,?,?)";

			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, video.getVideoURL());
			pstmt.setString(index++, video.getSlikaURL());
			pstmt.setString(index++, video.getOpis());
			pstmt.setString(index++, video.getVidljivost());
			pstmt.setInt(index++, video.getDozvoljeniKomentari());
			pstmt.setInt(index++, video.getVidljivostRejtinga());
			pstmt.setInt(index++, video.getBlokiran());
			pstmt.setInt(index++, video.getBrojPregleda());
			pstmt.setString(index++, video.getDatumKreiranja().toString());
			pstmt.setString(index++, video.getVlasnik());
			
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (INSERT, UPDATE, DELETE), jednom za svaki SQL upit
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			// zatvaranje naredbe i rezultata
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
	public static boolean update(Video video) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query = "Update Video set video=?,slicica=?,opis=?,vidljivost=?,dozvoljeniKomentari=?,vidljivostRejtinga=?,blokiran=?,brojPregleda=?,datumKreiranja=?,vlasnik=? where id=?";

			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, video.getVideoURL());
			pstmt.setString(index++, video.getSlikaURL());
			pstmt.setString(index++, video.getOpis());
			pstmt.setString(index++, video.getVidljivost());
			pstmt.setInt(index++, video.getDozvoljeniKomentari());
			pstmt.setInt(index++, video.getVidljivostRejtinga());
			pstmt.setInt(index++, video.getBlokiran());
			pstmt.setInt(index++, video.getBrojPregleda());
			pstmt.setString(index++, video.getDatumKreiranja().toString());
			pstmt.setString(index++, video.getVlasnik());
			pstmt.setInt(index++, video.getID());
			
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (INSERT, UPDATE, DELETE), jednom za svaki SQL upit
			return pstmt.executeUpdate() == 1;
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			// zatvaranje naredbe i rezultata
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}

		return false;
	}
	
}
