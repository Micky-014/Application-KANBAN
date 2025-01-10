package Entreprise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Taches {
    private int id;
    private String titre;
    private String priorite;
    private Date dateLimite;
    private Employes responsable;
    private String statut;
    private List<String> commentaires;

    public Taches(int id, String titre, String priorite, Date dateLimite, Employes responsable) {
        this.id = id;
        this.titre = titre;
        this.priorite = priorite;
        this.dateLimite = dateLimite;
        this.responsable = responsable;
        this.statut = "A faire";
        this.commentaires = new ArrayList<>();
    }

    public void modifier(String titre, String priorite, Date dateLimite, Employes responsable) {
        this.titre = titre;
        this.priorite = priorite;
        this.dateLimite = dateLimite;
        this.responsable = responsable;
    }

    public void ajouterCommentaire(String commentaire) {
        commentaires.add(commentaire);
    }
}
