package com.gogojuju.series;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class App 
{
    static List<Serie> series = new ArrayList<>();
    static List<Acteur> acteurs = new ArrayList<>();
	
	public static void main( String[] args )
    {

    	creerObjets("series.json");
    	Serie.setComp("alpha");
    	Collections.sort(series);
    	for(Serie s:series)
    	{
    		System.out.println(s);
    	}
    }
    
	// Création d'un objet Temp:
	// On divise l'objet fourni entre Séries et Acteurs
    public static void creerObjets(String source)
    {
    	// Création d'un objet Gson et d'un reader pour le fichier fourni
    	Gson gson = new Gson();
    	JsonReader readerFichier;
    	try
    	{
    		readerFichier = new JsonReader(new FileReader(source));
    		Temp t = gson.fromJson(readerFichier, Temp.class);
    		creerSeries(t);
    		creerActeurs(t);
    	} 
    	catch(FileNotFoundException e)
    	{
    		System.out.println("Fichier introuvable !");;
    	}
    }
    public static void creerSeries(Temp t/*, List<Serie> s*/)
    {
    	Gson gson = new Gson();
    	for (int i =0; i < t.getSeries().size(); i++)
		{
    		series.add(gson.fromJson(gson.toJson(t.getSeries().get(i)), Serie.class));
		}
    }
    
    public static void creerActeurs(Temp t)
    {
    	Gson gson = new Gson();
    	for (int i =0; i < t.getActors().size(); i++)
		{
    		acteurs.add(gson.fromJson(gson.toJson(t.getActors().get(i)), Acteur.class));
		}
    }
}