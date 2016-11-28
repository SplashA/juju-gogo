package com.gogojuju.series;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class App 
{
    static List<Serie> series = new ArrayList<>();
    static List<Acteur> acteurs = new ArrayList<>();
    static Scanner in = new Scanner(System.in);
    
	public static void main( String[] args ) throws IOException
    {
		String choix= "0";
		String[] choixPossibles = {"0","1","2","3","4","5"};
    	creerObjets("series.json");
    	do
    	{
    		System.out.println("Faites votre choix :");
    		System.out.println("1. Afficher la liste des séries (triées sur leur nom)");
    		System.out.println("2. Afficher la liste des séries (triées sur leur note)");
    		System.out.println("3. Rechercher une série");
    		System.out.println("4. Rechercher des séries par Genre");
    		System.out.println("5. Rechercher des séries par Acteur");
    		System.out.println("0. Quitter");
    		choix = in.next();
    		if((Arrays.binarySearch(choixPossibles, choix) < 0))
    		{
    			System.out.println("Ceci n'est pas un choix ...\n");
    		}
    		else
    		{
    			switch(choix)
    			{
    			case "1":
    				Serie.setComp("alpha");
    		    	Collections.sort(series);
    		    	for(Serie s:series)
    		    	{
    		    		System.out.println(s.getSeriesName() + " - " + s.getSiteRating() + "\n");
    		    	}
    		    	break;
    			case "2":
    				Serie.setComp("rating");
    		    	Collections.sort(series);
    		    	for(Serie s:series)
    		    	{
    		    		System.out.println(s.getSiteRating() + " - " + s.getSeriesName() + "\n");
    		    	}
    		    	break;
    			case "3":
    				System.out.println(rechercheNom(series));
    				break;
    			case "4":
    				rechercheParGenre(series);
    				break;
    			case "5":
    				rechercheParActeur(series,acteurs);
    				break;
    			case "0":
    				System.out.println("Merci de votre visite !\n");
    			}
    		}
    	}while (!choix.equals("0"));    	
    }
    
	// Création d'un objet Temp:
	// On divise l'objet fourni entre Séries et Acteurs
    public static void creerObjets(String source) throws IOException
    {
    	// Création d'un objet Gson et d'un reader pour le fichier fourni
    	Gson gson = new Gson();
    	//JsonReader readerFichier;
    	try(JsonReader readerFichier = new JsonReader(new FileReader(source));)
    	{
    		Temp t = gson.fromJson(readerFichier, Temp.class);
    		creerSeries(t);
    		creerActeurs(t);
    	} 
    	catch(FileNotFoundException e)
    	{
    		System.out.println("Fichier introuvable !");;
    	}
    }
    
    public static void creerSeries(Temp t)
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
    
    public static String rechercheNom(List<Serie> seriesList)
    {
    	System.out.println("Tapez le nom de la série :");
    	in.nextLine();
    	String nom = in.nextLine();
    	for(Serie s:seriesList)
    	{
    		if(s.getSeriesName().equalsIgnoreCase(nom))
    		{
    			return s.toString();
    		}
    	}
    	return "Cette série n'existe pas ...\n";	
    }
    
    public static void rechercheParGenre(List<Serie> seriesList)
    {
    	boolean trouve = false;
    	int compteur = 0;
    	System.out.println("Tapez un genre :");
    	in.nextLine();
    	String genre = in.nextLine();
    	for(Serie s:seriesList)
    	{
    		for(int i = 0; i < s.getGenre().length; i++)
    		{
    			if(s.getGenre()[i].equalsIgnoreCase(genre))
    			{
    				compteur++;
    				trouve = true;
    				System.out.println(compteur + " - " + s.getSeriesName() + "\n");
    			}
    		}
    	}
    	if(!trouve)
    	{
    		System.out.println("Aucune série n'appartient à ce genre ...\n");
    	}
    }
    
    public static void rechercheParActeur(List<Serie> seriesList,List<Acteur> acteursList)
    {
    	boolean trouve = false;
    	int compteur = 1;
    	System.out.println("Tapez le nom d'un acteur :");
    	in.nextLine();
    	String acteur = in.nextLine();
    	for(Acteur a:acteursList)
    	{
    		for(Serie s:seriesList)
    		{
    			if((a.getName().equalsIgnoreCase(acteur)) && (a.getSeriesId() == s.getId()))
    			{
    				trouve = true;
    				System.out.println(compteur + " - " + s.getSeriesName() + "\n");
    			}
    		}
    	}
    	if(!trouve)
    	{
    		System.out.println("Cet acteur n'apparaît dans aucune série ...\n");
    	}
    }
}