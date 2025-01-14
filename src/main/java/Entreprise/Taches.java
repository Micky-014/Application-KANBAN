package Entreprise;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Taches {
    private static int NombreId = 0;
    private int id;
    private String titre;
    private String priorite;
    private Date dateLimite;
    private Employes responsable;
    private String statut;
    private List<String> commentaires;
    private HashMap<Taches,List<Employes>> Equipe = new HashMap<Taches,List<Employes>>();
    public Taches(String titre, String priorite, Date dateLimite) {
        this.id = NombreId++;
        this.titre = titre;
        this.priorite = priorite;
        this.dateLimite = dateLimite;
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
