package projekat.model;

import java.util.Date;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class Video {
	
	public enum Vidljivost {Javni, Unlistet, Privatni};
	
	private int ID;
	private String videoURL;
	private String slikaURL;
	private String opis;
	private String vidljivost;
	private int dozvoljeniKomentari;
	private int vidljivostRejtinga;
	private int blokiran;
	private int brojPregleda;
	private String datumKreiranja;
	private String vlasnik;
	private Korisnik vlasnikV;
	
	public Video(){}
	
	public Video(int iD, String videoURL, String slikaURL)
	{
		this.ID = iD;
		this.videoURL = videoURL;
		this.slikaURL = slikaURL;
	}
	
	public Video(String video,String slicica,String opis,String vidljivost,int dozvoljeniKomentari,int vidljivostRejtinga,int blokiran,int brojPregleda,String out,String user)
	{
		this.videoURL = video;
		this.slikaURL = slicica;
		this.opis = opis;
		this.vidljivost = vidljivost;
		this.dozvoljeniKomentari = dozvoljeniKomentari;
		this.vidljivostRejtinga = vidljivostRejtinga;
		this.blokiran = blokiran;
		this.brojPregleda = brojPregleda;
		this.datumKreiranja = out;
		this.vlasnik = user;
	}
	public Video(int iD, String videoURL, String slikaURL, String opis, String vidljivost,
			int dozvoljeniKomentari, int vidljivostRejtinga, int blokiran, int brojPregleda,
			String datumKreiranja, String vlasnik,Korisnik vlasnikV) {
		super();
		ID = iD;
		this.videoURL = videoURL;
		this.slikaURL = slikaURL;
		this.opis = opis;
		this.vidljivost = vidljivost;
		this.dozvoljeniKomentari = dozvoljeniKomentari;
		this.vidljivostRejtinga = vidljivostRejtinga;
		this.blokiran = blokiran;
		this.brojPregleda = brojPregleda;
		this.datumKreiranja = datumKreiranja;
		this.vlasnik = vlasnik;
		this.vlasnikV = vlasnikV;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public String getSlikaURL() {
		return slikaURL;
	}

	public void setSlikaURL(String slikaURL) {
		this.slikaURL = slikaURL;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getVidljivost() {
		return vidljivost;
	}

	public void setVidljivost(String vidljivost) {
		this.vidljivost = vidljivost;
	}

	public int getDozvoljeniKomentari() {
		return dozvoljeniKomentari;
	}

	public void setDozvoljeniKomentari(int dozvoljeniKomentari) {
		this.dozvoljeniKomentari = dozvoljeniKomentari;
	}

	public int getVidljivostRejtinga() {
		return vidljivostRejtinga;
	}

	public void setVidljivostRejtinga(int vidljivostRejtinga) {
		this.vidljivostRejtinga = vidljivostRejtinga;
	}

	public int getBlokiran() {
		return blokiran;
	}

	public void setBlokiran(int blokiran) {
		this.blokiran = blokiran;
	}

	public int getBrojPregleda() {
		return brojPregleda;
	}

	public void setBrojPregleda(int brojPregleda) {
		this.brojPregleda = brojPregleda;
	}

	public String getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(String datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public String getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
	}

	public Korisnik getVlasnikV() {
		return vlasnikV;
	}

	public void setVlasnikV(Korisnik vlasnikV) {
		this.vlasnikV = vlasnikV;
	}
	
	
	
}
