package Entreprise;

import java.util.ArrayList;
import java.util.List;

public class  Employes {
    private int id;
    private static int nombreID = 0;
    private String nom;
    private String prenom;
    private String contact;
    private String role;
    private List<Projets> historiqueProjets;
    private static List<Employes> ListeEmployes= new ArrayList<>();

    public Employes(String nom, String prenom, String contact, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.contact = contact;
        this.role = role;
        this.id = 1+nombreID++;
        this.historiqueProjets= new ArrayList<>();
        ListeEmployes.add(this);
    }
    public static void suprEmploye(Employes employe){
        ListeEmployes.remove(employe);
    }
    public static List<Employes> getListeEmployes(){
        return ListeEmployes;
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
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
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
    public String getHistoriqueProjets() {
        return historiqueProjets.toString();
    }

    @Override
    public String toString() {
        return nom + " " + prenom; // Assurez-vous que "nom" et "prenom" sont définis dans la classe Employes
    }
}
