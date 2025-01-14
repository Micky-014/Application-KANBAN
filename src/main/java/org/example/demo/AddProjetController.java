package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

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

    // Méthode pour transmettre une référence au contrôleur principal
    public void setMainController(MainViewController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void handleAjouterProjet(ActionEvent event) {
        // Récupérer les données des champs
        String nomProjet = nomProjetField.getText();
        String jourDebut = jourDebutField.getText();
        String moisDebut = moisDebutField.getText();
        String anneeDebut = anneeDebutField.getText();
        String jourFin = jourFinField.getText();
        String moisFin = moisFinField.getText();
        String anneeFin = anneeFinField.getText();

        // Validation des champs
        if (nomProjet.isEmpty() || jourDebut.isEmpty() || moisDebut.isEmpty() || anneeDebut.isEmpty()
                || jourFin.isEmpty() || moisFin.isEmpty() || anneeFin.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs !");
            return;
        }

        try {
            // Convertir les champs de date en entiers pour validation
            int jourDebutInt = Integer.parseInt(jourDebut);
            int moisDebutInt = Integer.parseInt(moisDebut);
            int anneeDebutInt = Integer.parseInt(anneeDebut);
            int jourFinInt = Integer.parseInt(jourFin);
            int moisFinInt = Integer.parseInt(moisFin);
            int anneeFinInt = Integer.parseInt(anneeFin);

            // Validation des dates (simple)
            if (jourDebutInt < 1 || jourDebutInt > 31 || moisDebutInt < 1 || moisDebutInt > 12 || anneeDebutInt < 1900
                    || jourFinInt < 1 || jourFinInt > 31 || moisFinInt < 1 || moisFinInt > 12 || anneeFinInt < 1900) {
                System.out.println("Veuillez entrer des dates valides !");
                return;
            }
            LocalDate debut = LocalDate.of(anneeDebutInt, moisDebutInt, jourDebutInt);
            LocalDate fin = LocalDate.of(anneeFinInt, moisFinInt, jourFinInt);

            // Ajouter le projet à la liste principale
            if (mainController != null) {
                mainController.addProjetToList(nomProjet,debut,fin);
            }

            // Fermer la fenêtre actuelle
            Stage stage = (Stage) nomProjetField.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer des nombres valides pour les dates !");
        }
    }
}