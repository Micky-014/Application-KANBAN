package org.example.demo;

import Entreprise.Employes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

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

    public void setMainController(MainViewController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void handleAjouterEmploye(ActionEvent event) {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String contact = contactField.getText();
        String role = roleField.getText();
        if (nom.isEmpty() || prenom.isEmpty() || contact.isEmpty() || role.isEmpty()) {
            showAlert("Erreur", "Tous les champs doivent être remplis.");
            return;
        }
        if (!contact.matches("\\S+@\\S+\\.\\S+")) {
            showAlert("Erreur", "Veuillez entrer une adresse e-mail valide.");
            return;
        }
        if (mainController != null)  {
            mainController.addEmployeToList(nom, prenom, contact, role);
        }
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
