package Entreprise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Projets {
    private int id;
    private static int nombreID = 0;
    private String nomDeProjet;
    private LocalDate debut;
    private LocalDate fin;
    private List<Employes> listeEmployes = new ArrayList<Employes>();
    private List<Taches> listeTaches = new ArrayList<>();
    private HashMap<String,Taches> chercheTaches = new HashMap<>();
    private static List<Projets> listeProjets = new ArrayList<>();

    public Projets(String nomDeProjet, LocalDate debut, LocalDate fin) {
        this.id = 1+nombreID++;
        this.nomDeProjet = nomDeProjet;
        this.debut = debut;
        this.fin = fin;
        listeProjets.add(this);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNomDeProjet() {
        return nomDeProjet;
    }
    public void setNomDeProjet(String nomDeProjet) {
        this.nomDeProjet = nomDeProjet;
    }
    public LocalDate getDebut() {
        return debut;
    }
    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }
    public LocalDate getFin() {
        return fin;
    }
    public void setFin(LocalDate fin) {
        this.fin = fin;
    }
    public static List<Projets> getListeProjets() {
        return listeProjets;
    }
    public static void suprProjet(Projets projet) {
        listeProjets.remove(projet);
    }
    public String getTitre(){
        return nomDeProjet;
    }
    public List<Employes> getEmployes() {
        return listeEmployes;
    }
    public void ajouterEmploye(Employes employe) {
        listeEmployes.add(employe);
    }
    public void supprimerEmploye(Employes employe) {
        listeEmployes.remove(employe);
    }
    public void ajouterTache(Taches tache) {
        listeTaches.add(tache);
        chercheTaches.put(tache.getTitre(), tache);
    }
    public void supprimerTache(Taches tache) {
        listeTaches.remove(tache);
    }
    public Taches projetGetTache(String titre) {
        return chercheTaches.get(titre);
    }
    public void suprimerChercheTache(String titre) {
        chercheTaches.remove(titre);
    }
    public List<Taches> getTaches(){
        return listeTaches;
    }
}
