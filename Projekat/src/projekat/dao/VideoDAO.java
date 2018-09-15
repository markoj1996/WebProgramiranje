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

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select video,slicica,opis,vidljivost,dozvoljenikomentari,vidljivostrejtinga,blokiran,brojpregleda,datumkreiranja,vlasnik from Video where id = ?"; // bezbedno u odnosu na SQL injection napad

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

				Korisnik vlasnikV= UserDAO.get(vlasnik);
				return new Video(id,video,slicica,opis,vidljivost,dozvoljeniKomentari,vidljivostRejtinga,blokiran,brojPregleda,datumKreiranja,vlasnik,vlasnikV);
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

	public static List<Video> getAll(String nameFilter, int lowFilter, int highFilter,String sort) {
		List<Video> videos = new ArrayList<>();

		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		if(sort.equals("Sortiraj po imenu")) 
		{
			try {
				String query = "select id,video,slicica,opis,vidljivost,dozvoljenikomentari,vidljivostrejtinga,blokiran,brojpregleda,datumkreiranja,vlasnik from Video where (opis like ? or vlasnik like ?) and brojpregleda >= ? and brojpregleda <= ? order by opis";

				pstmt = conn.prepareStatement(query);
				int index = 1;
				pstmt.setString(index++, "%" + nameFilter + "%");
				pstmt.setString(index++, "%" + nameFilter + "%");
				pstmt.setInt(index++, lowFilter);
				pstmt.setInt(index++, highFilter);
				System.out.println(pstmt);

				rset = pstmt.executeQuery();
				while (rset.next()) {
					index = 1;
					int id = rset.getInt(index++);
					String videoV = rset.getString(index++);
					String slicica = rset.getString(index++);
					String opis = rset.getString(index++);
					String vidljivost = rset.getString(index++);
					int dozvoljeniKomentari = rset.getInt(index++);
					int vidljivostRejtinga = rset.getInt(index++);
					int blokiran = rset.getInt(index++);
					int brojPregleda = rset.getInt(index++);
					String datumKreiranja = rset.getString(index++);
					String vlasnik = rset.getString(index++);

					Korisnik vlasnikV= UserDAO.get(vlasnik);
					Video video = new Video(id,videoV, slicica, opis, vidljivost, dozvoljeniKomentari, vidljivostRejtinga, blokiran, brojPregleda, datumKreiranja, vlasnik,vlasnikV);
					videos.add(video);
				}
			} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}
		}else 
		{
			try {
				String query = "select id,video,slicica,opis,vidljivost,dozvoljenikomentari,vidljivostrejtinga,blokiran,brojpregleda,datumkreiranja,vlasnik from Video where (opis like ? or vlasnik like ?) and brojpregleda >= ? and brojpregleda <= ? order by datumkreiranja";

				pstmt = conn.prepareStatement(query);
				int index = 1;
				pstmt.setString(index++, "%" + nameFilter + "%");
				pstmt.setString(index++, "%" + nameFilter + "%");
				pstmt.setInt(index++, lowFilter);
				pstmt.setInt(index++, highFilter);
				System.out.println(pstmt);

				rset = pstmt.executeQuery();
				while (rset.next()) {
					index = 1;
					int id = rset.getInt(index++);
					String videoV = rset.getString(index++);
					String slicica = rset.getString(index++);
					String opis = rset.getString(index++);
					String vidljivost = rset.getString(index++);
					int dozvoljeniKomentari = rset.getInt(index++);
					int vidljivostRejtinga = rset.getInt(index++);
					int blokiran = rset.getInt(index++);
					int brojPregleda = rset.getInt(index++);
					String datumKreiranja = rset.getString(index++);
					String vlasnik = rset.getString(index++);

					Korisnik vlasnikV= UserDAO.get(vlasnik);
					Video video = new Video(id,videoV, slicica, opis, vidljivost, dozvoljeniKomentari, vidljivostRejtinga, blokiran, brojPregleda, datumKreiranja, vlasnik,vlasnikV);
					videos.add(video);
				}
			} catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}
		}
		
		
		return videos;
	}
	
	public static ArrayList<Video> getAll() {
		
		ArrayList<Video> lista = new ArrayList<>();
		
		Connection conn = ConnManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select id,video,slicica,opis,vidljivost,dozvoljenikomentari,vidljivostrejtinga,blokiran,brojpregleda,datumkreiranja,vlasnik from video"; // bezbedno u odnosu na SQL injection napad

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

				Korisnik vlasnikV= UserDAO.get(vlasnik);
				Video video = new Video(id,videos,slicica,opis,vidljivost,dozvoljeniKomentari,vidljivostRejtinga,blokiran,brojPregleda,datumKreiranja,vlasnik,vlasnikV);
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
			
			String query = "insert into Video (video,slicica,opis,vidljivost,dozvoljenikomentari,vidljivostrejtinga,blokiran,brojpregleda,datumkreiranja,vlasnik) values (?, ?, ?,?,?,?,?,?,?,?)";

			
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
	
	public static boolean update(Video video,int blokiran) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query = "update Video set video=?,slicica=?,opis=?,vidljivost=?,dozvoljeniKomentari=?,vidljivostRejtinga=?,blokiran=?,brojPregleda=?,datumKreiranja=?,vlasnik=? where id=?";

			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, video.getVideoURL());
			pstmt.setString(index++, video.getSlikaURL());
			pstmt.setString(index++, video.getOpis());
			pstmt.setString(index++, video.getVidljivost());
			pstmt.setInt(index++, video.getDozvoljeniKomentari());
			pstmt.setInt(index++, video.getVidljivostRejtinga());
			pstmt.setInt(index++, blokiran);
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
	
	public static boolean delete(Video video) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query = "Delete from  Video where id=?";

			
			pstmt = conn.prepareStatement(query);
			int index = 1;
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
	
	public static boolean update(Video video) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query = "update Video set video=?,slicica=?,opis=?,vidljivost=?,dozvoljenikomentari=?,vidljivostrejtinga=?,blokiran=?,brojpregleda=?,datumkreiranja=?,vlasnik=? where id=?";

			
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
