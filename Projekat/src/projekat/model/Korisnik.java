package projekat.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import projekat.model.Korisnik.Uloga;

public class Korisnik {

	public enum Uloga {Korisnik, Administrator};
	
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private String email;
	private String opis;
	private String datumRegistracije;
	private Uloga uloga;
	private int blokiran;
	//private List<Korisnik> pratioci;
	private List<LikeDislike> likeVideo;
	//private List<LikeDislike> likeKomentar;

	public Korisnik()
	{
		
	}
	
	public Korisnik(String uName)
	{
		this.korisnickoIme = uName;
	}
	
	public void DodajLike(LikeDislike like)
	{
		likeVideo.add(like);
	}
	
	public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, String email, String opis,
			String datumRegistracije, Uloga uloga, int blokiran) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.opis = opis;
		this.datumRegistracije = datumRegistracije;
		this.uloga = uloga;
		this.blokiran = blokiran;
		/*this.pratioci = pratioci;
		this.likeVideo = likeVideo;
		this.likeKomentar = likeKomentar;*/
	}

	public Korisnik(String korisnickoIme, String lozinka, Uloga uloga) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
	}

	public Korisnik(String korisnickoIme, String lozinka, Uloga uloga,String ime,String prezime,String email) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
	}
	
	public Korisnik(String userName, String password, Uloga korisnik, String ime2, String prezime2, String email2,
			String opis2, String datum, int blokiran2) {
		this.korisnickoIme = userName;
		this.lozinka=password;
		this.uloga = korisnik;
		this.ime = ime2;
		this.prezime = prezime2;
		this.email = email2;
		this.opis = opis2;
		this.datumRegistracije = datum;
		this.blokiran = blokiran2;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(String datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public int getBlokiran() {
		return blokiran;
	}

	public void setBlokiran(int blokiran) {
		this.blokiran = blokiran;
	}

	public List<LikeDislike> getLikeVideo() {
		return likeVideo;
	}

	public void setLikeVideo(List<LikeDislike> likeVideo) {
		this.likeVideo = likeVideo;
	}
	
	/*public List<Korisnik> getPratioci() {
		return pratioci;
	}

	public void setPratioci(List<Korisnik> pratioci) {
		this.pratioci = pratioci;
	}

	public List<LikeDislike> getLikeVideo() {
		return likeVideo;
	}

	public void setLikeVideo(List<LikeDislike> likeVideo) {
		this.likeVideo = likeVideo;
	}

	public List<LikeDislike> getLikeKomentar() {
		return likeKomentar;
	}

	public void setLikeKomentar(List<LikeDislike> likeKomentar) {
		this.likeKomentar = likeKomentar;
	}*/
	
	
	
}
