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
import projekat.model.Video;
import projekat.model.Korisnik.Uloga;

public class UserDAO {

	public static Korisnik get(String userName) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select password,ime,prezime,email,opis,datumregistracije,blokiran,role from Korisnici where username = ?"; // bezbedno u odnosu na SQL injection napad

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

	public static List<Korisnik> getAll(String nameFilter, int lowFilter) {
		List<Korisnik> users = new ArrayList<>();

		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			String query = "select * from Korisnici where ime like ? or prezime like ? or username like ?";

			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, "%" + nameFilter + "%");
			pstmt.setString(index++, "%" + nameFilter + "%");
			pstmt.setString(index++, "%" + nameFilter + "%");
			System.out.println(pstmt);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				index = 1;
				String userName = rset.getString(index++);
				String password = rset.getString(index++);
				String ime = rset.getString(index++);
				String prezime = rset.getString(index++);
				String email = rset.getString(index++);
				String opis = rset.getString(index++);
				String datumRegistracije = rset.getString(index++);
				Uloga role = Uloga.valueOf(rset.getString(index++));
				int blokiran = rset.getInt(index++);

				Korisnik user = new Korisnik(userName, password,ime,prezime,email,opis,datumRegistracije,role,blokiran);
				
				users.add(user);
			}
		} catch (SQLException ex) {
			System.out.println("Greska u SQL upitu!");
			ex.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rset.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		
		return users;
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
			
			String query = "insert into Korisnici (username, password, ime,prezime,email,opis,datumregistracije,role,blokiran) values (?, ?, ?,?,?,?,?,?,?)";

			
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
	
	public static boolean update(Korisnik korisnik,int blok) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query = "update Korisnici set username=?,password=?,ime=?,prezime=?,email=?,datumregistracije=?,role=?,blokiran=? where username=?";

			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setString(index++, korisnik.getIme());
			pstmt.setString(index++, korisnik.getPrezime());
			pstmt.setString(index++, korisnik.getEmail());
			pstmt.setString(index++, korisnik.getDatumRegistracije());
			pstmt.setString(index++, korisnik.getUloga().toString());
			pstmt.setInt(index++, blok);
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			
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

	public static boolean update(Korisnik korisnik,String uName) {
		Connection conn = ConnManager.getConnection();

		PreparedStatement pstmt = null;
		try {
			
			String query = "update Korisnici set userName=?,password=?,ime=?,prezime=?,email=?,datumregistracije=?,role=?,blokiran=? where userName=?";

			
			pstmt = conn.prepareStatement(query);
			int index = 1;
			pstmt.setString(index++, korisnik.getKorisnickoIme());
			pstmt.setString(index++, korisnik.getLozinka());
			pstmt.setString(index++, korisnik.getIme());
			pstmt.setString(index++, korisnik.getPrezime());
			pstmt.setString(index++, korisnik.getEmail());
			pstmt.setString(index++, korisnik.getDatumRegistracije());
			pstmt.setString(index++, korisnik.getUloga().toString());
			pstmt.setInt(index++, korisnik.getBlokiran());
			pstmt.setString(index++, uName);
			
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
