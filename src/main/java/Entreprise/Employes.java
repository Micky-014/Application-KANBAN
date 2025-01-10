package Entreprise;

import java.util.ArrayList;
import java.util.List;

public class  Employes {
    private int id;
    private static int nombreID = 0;
    private String nom;
    private String prenom;
    private String contact;
    private List<Projets> historiqueProjets;

    public Employes(String nom, String prenom, String contact) {
        this.nom = nom;
        this.prenom = prenom;
        this.contact = contact;
        this.id = nombreID++;
        this.historiqueProjets= new ArrayList<>();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public void modifier(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.contact = contact;
    }

    public void supprimer() {
        System.out.println("Employé supprimé : " + nom + " " + prenom);
    }

    public void ajouterProjet(Projets projet){
        historiqueProjets.add(projet);
    }

    public void afficherHistorique(){
        System.out.println("Historique des projets pour" + nom + ":");
        for(Projets projet:historiqueProjets){
            System.out.println("-"+projet.getTitre());
        }
    }
}
