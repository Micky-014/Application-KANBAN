package Entreprise;

public class Employes {
    private int id;
    private static int nombreID = 0;
    private String nom;
    private String prenom;
    private String contact;
    public Employes(String nom, String prenom, String contact) {
        this.nom = nom;
        this.prenom = prenom;
        this.contact = contact;
        this.id = nombreID++;
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
}
