package com.gogojuju.series;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Temp {
	
	@SerializedName("series")
	private List<Serie> series;
	
	@SerializedName("actors")
	private List<Acteur> actors;

	public List<Serie> getSeries() {
		return series;
	}

	public List<Acteur> getActors() {
		return actors;
	}
}
