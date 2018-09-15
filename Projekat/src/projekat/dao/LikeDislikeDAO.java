package projekat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import projekat.model.LikeDislike;
import projekat.model.Video;

public class LikeDislikeDAO {
	
	public static int get(Video v) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from LikeDislike where video = ?"; // bezbedno u odnosu na SQL injection napad

			// kreiranje SQL naredbe, jednom za svaki SQL upit
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, v.getID());
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (SELECT), jednom za svaki SQL upit
			rset = pstmt.executeQuery();

			// citanje rezultata upita
			if (rset.next()) {				
				int index = 1;
				int id = rset.getInt(index++);
				int like = rset.getInt(index++);
				String datumKreiranja = rset.getString(index++);
				int video = rset.getInt(index++);
				
				return id;
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

		return 0;
	}

	public static LikeDislike get(int id) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select likee,datumkreiranja,video from LikeDislike where id = ?"; // bezbedno u odnosu na SQL injection napad

			// kreiranje SQL naredbe, jednom za svaki SQL upit
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (SELECT), jednom za svaki SQL upit
			rset = pstmt.executeQuery();

			// citanje rezultata upita
			if (rset.next()) {
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
			String query = "select * from LikeDislike"; // bezbedno u odnosu na SQL injection napad

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
		
		String query = "insert into LikeDislike (id,likee,datumkreiranja,video) values (?,?, ?, ?)";

		
		pstmt = conn.prepareStatement(query);
		int index = 1;
		pstmt.setInt(index++, like.getID());
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

public static boolean delete(int id, int video) {
	Connection conn = ConnManager.getConnection();

	PreparedStatement pstmt = null;
	try {
		
		String query = "delete from LikeDislike where id = ? and video = ?";

		pstmt = conn.prepareStatement(query);
		int index = 1;
		pstmt.setInt(index++, id);
		pstmt.setInt(index++, video);
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

public static boolean delete(Video video) {
	Connection conn = ConnManager.getConnection();

	PreparedStatement pstmt = null;
	try {
		
		String query = "delete from  LikeDislike where video=?";

		
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
	
public static boolean update(LikeDislike like,int id) {
	Connection conn = ConnManager.getConnection();

	PreparedStatement pstmt = null;
	try {
		
		String query = "update LikeDislike set id=?,likee=?,datumkreiranja=?,video=? where id=?";

		
		pstmt = conn.prepareStatement(query);
		int index = 1;
		pstmt.setInt(index++, like.getID());
		pstmt.setInt(index++, id);
		pstmt.setString(index++, like.getDatumKreiranja());
		pstmt.setInt(index++, like.getVideo());
		pstmt.setInt(index++, like.getID());
		
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
