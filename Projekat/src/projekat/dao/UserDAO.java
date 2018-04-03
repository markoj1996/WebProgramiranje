package projekat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import projekat.model.Korisnik;
import projekat.model.Korisnik.Uloga;

public class UserDAO {

	public static Korisnik get(String userName) {
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

			String query = "SELECT password,ime,prezime,email,opis,datumRegistracije,blokiran,role FROM Korisnici WHERE userName = ?"; // bezbedno u odnosu na SQL injection napad

			// kreiranje SQL naredbe, jednom za svaki SQL upit
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			System.out.println(pstmt);
			// izvrsavanje naredbe i prihvatanje rezultata (SELECT), jednom za svaki SQL upit
			rset = pstmt.executeQuery();

			// citanje rezultata upita
			if (rset.next()) {
				int index = 1;
				String password = rset.getString(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String email = rset.getString(index++);
				String opis = rset.getString(index++);
				String datumRegistracije = rset.getString(index++);
				int blokiran = rset.getInt(index++);
				Uloga role = Uloga.valueOf(rset.getString(index++));
//				String password = rset.getString("password");
//				Role role = Role.valueOf(rset.getString("role"));

				
				return new Korisnik(userName, password,ime,prezime,email,opis,datumRegistracije,role,blokiran);
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

	public static List<Korisnik> getAll() {
		return new ArrayList<>();
	}

	public static List<Korisnik> getAll(String name, Uloga role) {
		return new ArrayList<>();
	}

	public static boolean add(Korisnik user) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query = "INSERT INTO Korisnici (userName, password, ime,prezime,email,opis,datumRegistracije,role,blokiran) VALUES (?, ?, ?,?,?,?,?,?,?)";

			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, user.getKorisnickoIme());
			pstmt.setString(index++, user.getLozinka());
			pstmt.setString(index++, user.getIme());
			pstmt.setString(index++, user.getPrezime());
			pstmt.setString(index++, user.getEmail());
			pstmt.setString(index++, user.getOpis());
			pstmt.setString(index++, user.getDatumRegistracije().toString());
			pstmt.setString(index++, user.getUloga().toString());
			pstmt.setString(index++, String.valueOf(user.getBlokiran()));
			
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

	/*public static boolean update(User user) {
		return false;
	}

	public static boolean delete(String userName) {
		return false;
	}
*/
}
