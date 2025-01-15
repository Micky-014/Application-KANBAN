package org.example.demo;

import Entreprise.Projets;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.time.LocalDate;

public class ProjetInfoController {
    @FXML
    private Label idLabel;

    @FXML
    private ListView<Projets> projetsListView;

    @FXML
    private ListView<String> historiqueListView;

    private Projets projet;

    @FXML
    private TextField nomField;

    @FXML
    private TextField jourDebutField;

    @FXML
    private TextField moisDebutField;

    @FXML
    private TextField anneeDebutField;

    @FXML
    private TextField jourFinField;

    @FXML
    private TextField moisFinField;

    @FXML
    private TextField anneeFinField;

    private MainViewController mainController;

    public void setMainController(MainViewController mainController) {
        this.mainController = mainController;
    }

    // Méthode pour définir les détails d'un employé
    public void setProjetDetails(Projets projet) {

        this.projet = projet;
        if (projet != null) {
            nomField.setText(projet.getNomDeProjet());
            jourDebutField.setText(String.valueOf(projet.getDebut().getDayOfMonth()));
            moisDebutField.setText(String.valueOf(projet.getDebut().getMonthValue()));
            anneeDebutField.setText(String.valueOf(projet.getDebut().getYear()));
            jourFinField.setText(String.valueOf(projet.getDebut().getDayOfMonth()));
            moisFinField.setText(String.valueOf(projet.getDebut().getMonthValue()));
            anneeFinField.setText(String.valueOf(projet.getDebut().getYear()));
            idLabel.setText(String.valueOf(projet.getId()));

            afficherHistorique();
        }
        nomField.setEditable(false);
        jourDebutField.setEditable(false);
        moisDebutField.setEditable(false);
        anneeDebutField.setEditable(false);
        jourFinField.setEditable(false);
        moisFinField.setEditable(false);
        anneeFinField.setEditable(false);

    }

    private void afficherHistorique() {
        // Exemple : Ajouter un historique fictif
        historiqueListView.getItems().addAll("Étape 1 : Analyse", "Étape 2 : Développement", "Étape 3 : Tests");
    }

    @FXML
    private void handleEdit() {
        // Active la modification des champs
        nomField.setEditable(true);
        jourDebutField.setEditable(true);
        moisDebutField.setEditable(true);
        anneeDebutField.setEditable(true);
        jourFinField.setEditable(true);
        moisFinField.setEditable(true);
        anneeFinField.setEditable(true);
    }

    @FXML
    private void handleSave() {
        if (projet != null) {
            projet.setNomDeProjet(nomField.getText());
            int jourDebut = Integer.parseInt(jourDebutField.getText());
            int moisDebut = Integer.parseInt(moisDebutField.getText());
            int anneeDebut = Integer.parseInt(anneeDebutField.getText());
            int jourFin = Integer.parseInt(jourFinField.getText());
            int moisFin = Integer.parseInt(moisFinField.getText());
            int anneeFin = Integer.parseInt(anneeFinField.getText());
            projet.setDebut(LocalDate.of(anneeDebut, moisDebut, jourDebut));
            projet.setFin(LocalDate.of(anneeFin, moisFin, jourFin));
            afficherMessage("Modification réussie", "Les informations du projet ont été mises à jour.");

            // Désactive l'édition après enregistrement
            nomField.setEditable(false);
            jourDebutField.setEditable(false);
            moisDebutField.setEditable(false);
            anneeDebutField.setEditable(false);
            jourFinField.setEditable(false);
            moisFinField.setEditable(false);
            anneeFinField.setEditable(false);

            if (mainController != null) {
                mainController.refreshListView();
            }
        }
    }

    private void afficherMessage(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void handleDelete() {
        if (projet != null && mainController != null) {
            mainController.deleteProjetFromList(projet);
            afficherMessage("Suppression réussie", "Le projet a été supprimé.");
            // Fermer la fenêtre actuelle
            idLabel.getScene().getWindow().hide();
        }
    }

    @FXML
    private void handleKanban() {
        if (projet != null && mainController != null) {
            mainController.showKanban(projet); // Passe le projet à la méthode du contrôleur principal
            Stage stage = (Stage) nomField.getScene().getWindow();
            stage.close(); // Fermer la fenêtre actuelle si nécessaire
        }
    }
    
    


}
