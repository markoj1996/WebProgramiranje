package projekat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import projekat.model.LikeDislike;
import projekat.model.Video;

public class LikeKorisniciDAO {

public static ArrayList<String> getAll() {
		
		ArrayList<String> lista = new ArrayList<>();
		
		Connection conn = ConnManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from LikeKorisniciVideo"; // bezbedno u odnosu na SQL injection napad

			// kreiranje SQL naredbe, jednom za svaki SQL upit
			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (SELECT), jednom za svaki SQL upit
			rset = pstmt.executeQuery();

			// citanje rezultata upita
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				String korisnik = rset.getString(index++);
				int like = rset.getInt(index++);
				String rez = Integer.toString(id)+","+korisnik+","+Integer.toString(like);

				lista.add(rez);
						
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

public static boolean add(String user,int like) {
	Connection conn = ConnManager.getConnection();

	PreparedStatement pstmt = null;
	try {
		
		String query = "insert into LikeKorisniciVideo (korisnik,likedislike) values (?, ?)";

		
		pstmt = conn.prepareStatement(query);
		int index = 1;
		pstmt.setString(index++, user);
		pstmt.setInt(index++, like);
		
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

public static boolean delete(int id) {
	Connection conn = ConnManager.getConnection();

	PreparedStatement pstmt = null;
	try {
		
		String query = "delete from  LikeKorisniciVideo where likedislike=?";

		
		pstmt = conn.prepareStatement(query);
		int index = 1;
		pstmt.setInt(index++, id);
		
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

public static boolean delete(String korisnik,int id) {
	Connection conn = ConnManager.getConnection();

	PreparedStatement pstmt = null;
	try {
		
		String query = "delete from LikeKorisniciVideo where korisnik = ? and likedislike = ?";

		pstmt = conn.prepareStatement(query);
		int index = 1;
		pstmt.setString(index++, korisnik);
		pstmt.setInt(index++, id);
		System.out.println(pstmt);

		boolean success = pstmt.executeUpdate() == 1;
		
		return success;
	} catch (SQLException ex) {
		System.out.println("Greska u SQL upitu!");
		ex.printStackTrace();
		
		try {conn.rollback();} catch (SQLException ex1) {ex1.printStackTrace();}
	} finally {
		try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}

		try {conn.setAutoCommit(true);} catch (SQLException ex1) {ex1.printStackTrace();}
	}

	return false;
}
	
}
