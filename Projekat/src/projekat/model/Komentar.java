package projekat.model;

import java.util.Date;

public class Komentar {

	/*1. ID (jedinstven, generiše aplikacija) 
	 * 2. Sadržaj 
	 * 3. Datum (vreme) kreiranja (generiše aplikacija) 
	 * 4. Vlasnik (korisnik, povezuje aplikacija) 
	 * 5. Video (na koji je ovo komentar, povezuje aplikacija) */
	
	private int ID;
	private String sadrzaj;
	private String datumKreiranja;
	private Korisnik vlasnik;
	private int video;
	
	
	
	public Komentar(String sadrzaj, String datumKreiranja ,Korisnik vlasnik) {
		super();
		this.sadrzaj = sadrzaj;
		this.datumKreiranja = datumKreiranja;
		this.vlasnik = vlasnik;
	}

	public Komentar(String sadrzaj, String datumKreiranja, Korisnik vlasnik, int video) {
		super();
		this.sadrzaj = sadrzaj;
		this.datumKreiranja = datumKreiranja;
		this.vlasnik = vlasnik;
		this.video = video;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public String getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(String datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public Korisnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Korisnik vlasnik) {
		this.vlasnik = vlasnik;
	}

	public int getVideo() {
		return video;
	}

	public void setVideo(int video) {
		this.video = video;
	}
	
	
	
}
