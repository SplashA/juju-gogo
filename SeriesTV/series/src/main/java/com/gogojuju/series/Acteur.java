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
	
	@Override
	public String toString()
	{
		return "Id :" + this.id + "\n" +
				"Nom :" + this.name + "\n" +
				"Id série :" + this.seriesId + "\n" +
				"Rôle :" + this.role + "\n*************************\n";
	}
}
