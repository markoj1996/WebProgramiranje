package projekat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import projekat.model.Komentar;
import projekat.model.Korisnik;
import projekat.model.Video;

public class CommentDAO {
	
public static ArrayList<Komentar> getAll() {
		
		ArrayList<Komentar> lista = new ArrayList<>();
		
		Connection conn = ConnManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select id,sadrzaj,datum,korisnik,video from Komentar";

			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);
			
			rset = pstmt.executeQuery();

			
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String sadrzaj = rset.getString(index++);
				String datum = rset.getString(index++);
				String korisnik = rset.getString(index++);
				Korisnik k = UserDAO.get(korisnik);
				String video = rset.getString(index++);
				int v = Integer.parseInt(video);

				
				Komentar komentar = new Komentar(sadrzaj, datum, k,v);
				lista.add(komentar);
						
			}
			return lista;
		}
			catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
				return lista;
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}

		
	}
	
public static ArrayList<Komentar> getAll(String sort) {
	
	ArrayList<Komentar> lista = new ArrayList<>();
	
	Connection conn = ConnManager.getConnection();
	
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	if(sort.equals("Sortiraj po sadrzaju")) 
	{
		try {
			
			String query = "select id,sadrzaj,datum,korisnik,video from Komentar order by sadrzaj";

			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);
			
			rset = pstmt.executeQuery();

			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String sadrzaj = rset.getString(index++);
				String datum = rset.getString(index++);
				String korisnik = rset.getString(index++);
				Korisnik k = UserDAO.get(korisnik);
				String video = rset.getString(index++);
				int v = Integer.parseInt(video);

				
				Komentar komentar = new Komentar(sadrzaj, datum, k,v);
				lista.add(komentar);
						
			}
			return lista;
		}
			catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
				return lista;
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}

	}else 
	{
		try {
			String query = "select id,sadrzaj,datum,korisnik,video from Komentar order by datum";

			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (SELECT), jednom za svaki SQL upit
			rset = pstmt.executeQuery();

			// citanje rezultata upita
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String sadrzaj = rset.getString(index++);
				String datum = rset.getString(index++);
				String korisnik = rset.getString(index++);
				Korisnik k = UserDAO.get(korisnik);
				String video = rset.getString(index++);
				int v = Integer.parseInt(video);

				
				Komentar komentar = new Komentar(sadrzaj, datum, k,v);
				lista.add(komentar);
						
			}
			return lista;
		}
			catch (SQLException ex) {
				System.out.println("Greska u SQL upitu!");
				ex.printStackTrace();
				return lista;
			} finally {
				try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
				try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			}
	}
	
	
}

public static boolean delete(Video video) {
	Connection conn = ConnManager.getConnection();

	PreparedStatement pstmt = null;
	try {
		
		String query = "delete from  Komentar where video=?";

		
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

	public static boolean add(Komentar komentar) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query = "insert into Komentar (sadrzaj,datum,korisnik,video) values (?, ?, ?, ?)";

			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, komentar.getSadrzaj());
			pstmt.setString(index++, komentar.getDatumKreiranja().toString());
			pstmt.setString(index++, komentar.getVlasnik().getKorisnickoIme());
			pstmt.setInt(index++, komentar.getVideo());
			
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
