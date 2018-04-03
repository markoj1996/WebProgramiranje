package projekat.model;

import java.util.Date;

public class LikeDislike {

	/*1. ID (jedinstven, generiše aplikacija) 
	 * 2. Da li je like? (true/false) (generiše aplikacija) 
	 * 3. Datum (vreme) kreiranja (generiše aplikacija) 
	 * 4. Video ili komentar (koji se like-uje/dislike-uje, povezuje aplikacija) */
	
	private int ID;
	private int like;
	private String datumKreiranja;
	private int video;
	private Komentar komentar;
	
	public LikeDislike(int iD, int like, String datumKreiranja, int video) {
		super();
		ID = iD;
		this.like = like;
		this.datumKreiranja = datumKreiranja;
		this.video = video;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public String getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(String datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public int getVideo() {
		return video;
	}

	public void setVideo(int video) {
		this.video = video;
	}

	public Komentar getKomentar() {
		return komentar;
	}

	public void setKomentar(Komentar komentar) {
		this.komentar = komentar;
	}
	
	
	
}
