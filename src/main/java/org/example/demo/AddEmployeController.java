package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEmployeController {

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField roleField;

    private MainViewController mainController;

    // Méthode pour transmettre une référence au contrôleur principal
    public void setMainController(MainViewController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void handleAjouterEmploye(ActionEvent event) {
        // Récupérer les données des champs de texte
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String contact = contactField.getText();
        String role = roleField.getText();
  //      Employes employe = new Employe
        // Ajouter l'employé à la ListView du contrôleur principal
        if (mainController != null) {
            mainController.addEmployeToList(nom, prenom, contact, role);
        }

        // Fermer la fenêtre actuelle
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }
}
