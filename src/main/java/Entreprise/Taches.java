package Entreprise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Taches {
    private static int NombreId = 0;
    private int id;
    private String titre;
    private String priorite;
    private LocalDate dateLimite;
    private Employes responsable;
    private String statut;
    private String commentaires;
    private HashMap<Taches,List<Employes>> Equipe = new HashMap<Taches,List<Employes>>();
    public Taches(String titre, String priorite, LocalDate dateLimite,String commentaires) {
        this.id = NombreId++;
        this.titre = titre;
        this.priorite = priorite;
        this.dateLimite = dateLimite;
        this.statut = "A faire";
        this.commentaires = commentaires;
    }

    public void modifier(String titre, String priorite, LocalDate dateLimite, Employes responsable) {
        this.titre = titre;
        this.priorite = priorite;
        this.dateLimite = dateLimite;
        this.responsable = responsable;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }
    public void setDateLimite(LocalDate dateLimite) {
        this.dateLimite = dateLimite;
    }
    public void setResponsable(Employes responsable) {
        this.responsable = responsable;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }
    public int getId() {
        return id;
    }
    public String getTitre() {
        return titre;
    }
    public String getPriorite() {
        return priorite;
    }
    public LocalDate getDateLimite() {
        return dateLimite;
    }
    public Employes getResponsable() {
        return responsable;
    }
    public String getStatut() {
        return statut;
    }
    public String getCommentaires() {
        return commentaires;
    }
}
