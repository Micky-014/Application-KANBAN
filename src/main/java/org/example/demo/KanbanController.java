package org.example.demo;

import Entreprise.Projets;
import Entreprise.Taches;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class KanbanController {

    private Projets projet;
    private List<Taches> taches;
    public void handleAjouterTache() {
        try {
            // Charger la fenêtre d'ajout de tache
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTache.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle fenêtre pour la tâche
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ajouter une Tâche");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setProjet(Projets projet) {
        this.projet = projet;
        initializeKanban();
    }

    @FXML
    private void initializeKanban() {
        if (projet != null) {
            taches = projet.getTaches();
            for (Taches tache : taches) {
//                if tache.getStatut()
            }
            System.out.println("Projet dans Kanban : " + projet.getNomDeProjet());
            // Initialisez ici les éléments de votre vue Kanban avec les données du projet.
        }
    }
}
