package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import javafx.scene.control.Alert;

public class AddProjetController {

    @FXML
    private TextField nomProjetField;

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

    @FXML
    public void handleAjouterProjet(ActionEvent event) {
        String nomProjet = nomProjetField.getText();
        String jourDebut = jourDebutField.getText();
        String moisDebut = moisDebutField.getText();
        String anneeDebut = anneeDebutField.getText();
        String jourFin = jourFinField.getText();
        String moisFin = moisFinField.getText();
        String anneeFin = anneeFinField.getText();
        if (nomProjet.isEmpty() || jourDebut.isEmpty() || moisDebut.isEmpty() || anneeDebut.isEmpty()
                || jourFin.isEmpty() || moisFin.isEmpty() || anneeFin.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs !");
            return;
        }

        try {
            LocalDate debut = parseDate(jourDebut, moisDebut, anneeDebut);
            LocalDate fin = parseDate(jourFin, moisFin, anneeFin);
            if (debut.isAfter(fin)) {
                showAlert("Erreur", "La date de début doit être avant la date de fin.");
                return;
            }
            if (mainController != null) {
                mainController.addProjetToList(nomProjet, debut, fin);
            }
            Stage stage = (Stage) nomProjetField.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            showAlert("Erreur", "Veuillez entrer des nombres valides pour les dates.");
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