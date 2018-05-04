package projekat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import projekat.model.LikeDislike;
import projekat.model.Video;

public class LikeDislikeDAO {

	public static LikeDislike get(int id) {
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
			String query = "SELECT likee,datumKreiranja,video FROM LikeDislike WHERE ID = ?"; // bezbedno u odnosu na SQL injection napad

			// kreiranje SQL naredbe, jednom za svaki SQL upit
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (SELECT), jednom za svaki SQL upit
			rset = pstmt.executeQuery();

			// citanje rezultata upita
			if (rset.next()) {
//int iD, Boolean like, String datumKreiranja, int video				
				int index = 1;
				int like = rset.getInt(index++);
				String datumKreiranja = rset.getString(index++);
				int video = rset.getInt(index++);
				
				return new LikeDislike(id,like,datumKreiranja,video);
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
	
public static ArrayList<LikeDislike> getAll() {
		
		ArrayList<LikeDislike> lista = new ArrayList<>();
		
		Connection conn = ConnManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM LikeDislike"; // bezbedno u odnosu na SQL injection napad

			// kreiranje SQL naredbe, jednom za svaki SQL upit
			pstmt = conn.prepareStatement(query);
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (SELECT), jednom za svaki SQL upit
			rset = pstmt.executeQuery();

			// citanje rezultata upita
			while (rset.next()) {
				int index = 1;
				int id = rset.getInt(index++);
				int likee = rset.getInt(index++);
				String datumKreiranja = rset.getString(index++);
				int video = rset.getInt(index++);


				LikeDislike like = new LikeDislike(id, likee, datumKreiranja, video);
				lista.add(like);
						
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

public static boolean add(LikeDislike like) {
	Connection conn = ConnManager.getConnection();

	PreparedStatement pstmt = null;
	try {
		
		String query = "INSERT INTO LIKEDISLIKE (likee,datumKreiranja,video) VALUES (?, ?, ?)";

		
		pstmt = conn.prepareStatement(query);
		int index = 1;
		pstmt.setInt(index++, like.getLike());
		pstmt.setString(index++, like.getDatumKreiranja());
		pstmt.setInt(index++, like.getVideo());
		
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
