package com.gogojuju.series;

import com.google.gson.annotations.SerializedName;

public class Acteur {
	@SerializedName("id")
	private long id;
	
	@SerializedName("seriesId")
	private long seriesId;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("role")
	private String role;
	
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getSeriesId() {
		return seriesId;
	}



	public void setSeriesId(long seriesId) {
		this.seriesId = seriesId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public String toString()
	{
		return "\nId :" + this.id + "\n" +
				"Nom :" + this.name + "\n" +
				"Id série :" + this.seriesId + "\n" +
				"Rôle :" + this.role + "\n";
	}
}
