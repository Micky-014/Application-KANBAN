package org.example.demo;

import Entreprise.Projets;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class KanbanController {

    private Projets projet;

    public void handleAjouterTache() {
        try {
            // Charger la fenêtre d'ajout de tache
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTache.fxml"));
            Parent root = loader.load();
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
            System.out.println("Projet dans Kanban : " + projet.getNomDeProjet());
            // Initialisez ici les éléments de votre vue Kanban avec les données du projet.
        }
    }
}
