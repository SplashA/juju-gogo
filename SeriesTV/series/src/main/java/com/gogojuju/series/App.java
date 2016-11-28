package com.gogojuju.series;

// imports de bibliothèques Google Gson pour la gestion du JSON
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

// imports de biblithèques utilitaires
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
    
    // Initialisation d'un scanner de saisie (static car utilisé dans toute l'app
    static Scanner in = new Scanner(System.in);
    
	public static void main( String[] args ) throws IOException
    {
		String choix= "0";
		// Tableau des possibles. En String pour éviter les erreurs nombre/chaîne
		String[] choixPossibles = {"0","1","2","3","4","5"};
    	creerObjets("series.json");
    	// Menu qui apparaît jusqu'à ce que l'utilisateur choisisse de sortir (0)
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
    		// On teste d'abord si la saisie de l'utilisateur est valide
    		if((Arrays.binarySearch(choixPossibles, choix) < 0))
    		{
    			System.out.println("Ceci n'est pas un choix ...\n");
    			effaceEcran();
    		}
    		else
    		{
    			switch(choix)
    			{
    			case "1":
    				// Set de la statique comp qui définit le critère de tri
    				Serie.setComp("alpha");
    				// Utilisation de la méthode sort() pur le tri d'une collection
    		    	Collections.sort(series);
    		    	effaceEcran();
    		    	for(Serie s:series)
    		    	{
    		    		System.out.println(s.getSeriesName() + " - " + s.getSiteRating() + "\n");
    		    	}
    		    	
    		    	break;
    			case "2":
    				Serie.setComp("rating");
    		    	Collections.sort(series);
    		    	effaceEcran();
    		    	for(Serie s:series)
    		    	{
    		    		System.out.println(s.getSiteRating() + " - " + s.getSeriesName() + "\n");
    		    	}
    		    	break;
    			case "3":
    				effaceEcran();
    				System.out.println(rechercheNom(series));
    				break;
    			case "4":
    				effaceEcran();
    				rechercheParGenre(series);
    				break;
    			case "5":
    				effaceEcran();
    				rechercheParActeur(series,acteurs);
    				break;
    			case "0":
    				effaceEcran();
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
    	// Try with resources pour fermer le stream une fois qu'il n'est plus utilisé
    	try(JsonReader readerFichier = new JsonReader(new FileReader(source));)
    	{
    		// Utilisation de la biblio Gson de google pour la manipulation du Json
    		// On prend le fichier qu'on calque sur le schéma de la classe Temp
    		Temp t = gson.fromJson(readerFichier, Temp.class);
    		creerSeries(t);
    		creerActeurs(t);
    	} 
    	catch(FileNotFoundException e)
    	{
    		System.out.println("Fichier introuvable !");;
    	}
    }
    
    // Création des instances de Serie, basées sur la collection Series de l'instance de Temp
    // et le schéma de la classe Serie
    public static void creerSeries(Temp t)
    {
    	Gson gson = new Gson();
    	for (int i =0; i < t.getSeries().size(); i++)
		{
    		series.add(gson.fromJson(gson.toJson(t.getSeries().get(i)), Serie.class));
		}
    }
    
    // Création des instances de Acteur, basées sur la collection Acteurs de l'instance de Temp
    // et le schéma de la classe Acteur
    public static void creerActeurs(Temp t)
    {
    	Gson gson = new Gson();
    	for (int i =0; i < t.getActors().size(); i++)
		{
    		acteurs.add(gson.fromJson(gson.toJson(t.getActors().get(i)), Acteur.class));
		}
    }
    
    // Recherche par nom, l'utilisateur est invité à saisir un nom de série
    public static String rechercheNom(List<Serie> seriesList)
    {
    	System.out.println("Tapez le nom de la série :");
    	in.nextLine();
    	String nom = in.nextLine();
    	// Parcours (foreach) des Serie d'une List et sortie des infos de la série (si trouvée)
    	for(Serie s:seriesList)
    	{
    		if(s.getSeriesName().equalsIgnoreCase(nom))
    		{
    			return s.toString();
    		}
    	}
    	return "Cette série n'existe pas ...\n";	
    }
    
    // Recherche par nom, l'utilisateur est invité à saisir un genre
    public static void rechercheParGenre(List<Serie> seriesList)
    {
    	boolean trouve = false;
    	int compteur = 0;
    	System.out.println("Tapez un genre :");
    	in.nextLine();
    	String genre = in.nextLine();
    	// Double parcours : les instances de Serie puis du tableau des genres dans l'instance
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
    
    // Recherche par acteur, l'utilisateur est invité à saisir un nom prénom d'acteur
    public static void rechercheParActeur(List<Serie> seriesList,List<Acteur> acteursList)
    {
    	boolean trouve = false;
    	int compteur = 1;
    	System.out.println("Tapez le nom d'un acteur :");
    	in.nextLine();
    	String acteur = in.nextLine();
    	// On parcourt les Acteur pour obtenir ceux qui répondent à la saisie utilisateur
    	// puis on croise avec les Serie en utilisant l'attribut SeriesId de Acteur
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
    
    public static void effaceEcran()
    {
    	System.out.println("\033[H\033[2J");
    	System.out.flush();
    }
}