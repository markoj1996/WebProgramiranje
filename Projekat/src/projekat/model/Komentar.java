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
	private Date datumKreiranja;
	private Korisnik vlasnik;
	private Video video;
	
	public Komentar(int iD, String sadrzaj, Date datumKreiranja, Korisnik vlasnik, Video video) {
		super();
		ID = iD;
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

	public Date getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public Korisnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Korisnik vlasnik) {
		this.vlasnik = vlasnik;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
	
	
}
