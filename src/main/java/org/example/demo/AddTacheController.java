package org.example.demo;

import Entreprise.Taches;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


import java.time.LocalDate;

public class AddTacheController {
    @FXML
    private TextField nomTacheField;
    @FXML
    private TextField jourField;
    @FXML
    private TextField moisField;
    @FXML
    private TextField anneeField;
    @FXML
    private TextArea commentairesField;
    @FXML
    private ComboBox<String> prioriteComboBox;

    private KanbanController mainController;

    public void setMainController(KanbanController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        prioriteComboBox.getItems().addAll("Haute", "Moyenne", "Basse");
    }

    @FXML
    public void handleAjouterTache(ActionEvent event) {
        String nom = nomTacheField.getText();
        String priorite = prioriteComboBox.getValue();
        String jour = jourField.getText();
        String mois = moisField.getText();
        String annee = anneeField.getText();
        String commentaires = commentairesField.getText();
        if (nom.isEmpty() || priorite == null || jour.isEmpty() || mois.isEmpty() || annee.isEmpty()) {
            showAlert("Erreur", "Tous les champs doivent être remplis.");
            return;
        }
        try {
            LocalDate date = parseDate(jour, mois, annee);
            Taches tache = new Taches(nom, priorite, date, commentaires);
            mainController.ajouterTacheKanban(tache);
            System.out.println("Tâche ajoutée : " + tache);
            Stage stage = (Stage) nomTacheField.getScene().getWindow();
            stage.close();
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Veuillez entrer des valeurs numériques valides pour la date.");
        } catch (IllegalArgumentException e) {
            showAlert("Erreur", e.getMessage());
        }
    }

    private LocalDate parseDate(String jour, String mois, String annee) {
        try {
            int jourInt = Integer.parseInt(jour);
            int moisInt = Integer.parseInt(mois);
            int anneeInt = Integer.parseInt(annee);
            if (jourInt < 1 || jourInt > 31 || moisInt < 1 || moisInt > 12 || anneeInt < 1900) {
                throw new IllegalArgumentException("Veuillez entrer une date valide.");
            }
            return LocalDate.of(anneeInt, moisInt, jourInt);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Les champs de date doivent contenir uniquement des chiffres.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}