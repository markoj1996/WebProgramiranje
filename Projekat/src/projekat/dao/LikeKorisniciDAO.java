package projekat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import projekat.model.LikeDislike;

public class LikeKorisniciDAO {

public static ArrayList<Integer> getAll() {
		
		ArrayList<Integer> lista = new ArrayList<>();
		
		Connection conn = ConnManager.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "SELECT * FROM LikeKorisniciVideo"; // bezbedno u odnosu na SQL injection napad

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
	
}
