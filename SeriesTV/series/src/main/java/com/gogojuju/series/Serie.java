package com.gogojuju.series;


public class Serie {
	
	private int id;
	private String seriesName;
	private String network;
	private String overview;
	private float siteRating;
	
	public Serie(int i, String sN, String n, String o, float sR){
		this.id = i;
		this.seriesName = sN;
		this.network = n;
		this.overview = o;
		this.siteRating = sR;
	}
}
