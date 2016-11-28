package com.gogojuju.series;

import com.google.gson.annotations.SerializedName;


public class Serie implements Comparable<Serie> {
	private static String comp = "rating";
	
	@SerializedName("id")
	private long id;
	
	@SerializedName("seriesName")
	private String seriesName;
	
	@SerializedName("network")
	private String network;
	
	@SerializedName("overview")
	private String overview;
	
	@SerializedName("siteRating")
	private float siteRating;
	
	@SerializedName("genre")
	private String[] genre;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public float getSiteRating() {
		return siteRating;
	}

	public void setSiteRating(float siteRating) {
		this.siteRating = siteRating;
	}
	
	public String[] getGenre() {
		return this.genre;
	}
	
	public String afficherGenre()
	{
		String g = "";
		if(genre.length>0)
		{
			g = genre[0];
			for(int i = 1; i < genre.length;i++)
			{
				g = g + "," + genre[i];
			}
		}
		return g;
	}
	
	
	static public void setComp(String c)
	{
		Serie.comp = c;
	}
	
	@Override
	public String toString() {
		return "Id :" + this.id + "\n" +
				"Nom :" + this.seriesName + "\n" +
				"Genre :" + this.afficherGenre() + "\n" +
				"Chaîne :" + this.network + "\n" +
				"Résumé :" + this.overview + "\n" +
				"Note :" + this.siteRating + "\n*************************\n";
	}
	
	@Override
	public int compareTo(Serie s){
		if (comp.equals("alpha"))
		{
			String titre1 = this.seriesName.toLowerCase();
			String titre2 = s.getSeriesName().toLowerCase();
			return titre1.compareToIgnoreCase(titre2);
		}
		else
		{
			if(this.siteRating > s.getSiteRating())
			{
				return 1;
			}
			else if (this.siteRating == s.getSiteRating())
			{
				return 0;
			}
			else
			{
				return -1;
			}
		}
	}
}
