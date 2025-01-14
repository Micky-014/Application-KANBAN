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
    private static List<Projets> listeProjets = new ArrayList<>();

    public Projets(String nomDeProjet, LocalDate debut, LocalDate fin) {
        this.id = nombreID++;
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

    public void ajouterTache(Taches tache) {
        listeTaches.add(tache);
    }

    public void supprimerTache(Taches tache) {
        listeTaches.remove(tache);
    }
}
