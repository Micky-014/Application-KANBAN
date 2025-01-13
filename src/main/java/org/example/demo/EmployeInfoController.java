package org.example.demo;

import Entreprise.Employes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class EmployeInfoController {


    @FXML
    private Label idLabel;

    @FXML
    private ListView<Employes> employeListView;

    @FXML
    private ListView<String> historiqueListView;

    private Employes employe;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField roleField;



    // Méthode pour définir les détails d'un employé
    public void setEmployeDetails(Employes employe) {
        if (employe != null) {
            nomField.setText(employe.getNom());
            prenomField.setText(employe.getPrenom());
            contactField.setText(employe.getContact());
            roleField.setText(employe.getRole());
            idLabel.setText(String.valueOf(employe.getId()));
        }
        nomField.setEditable(false);
        prenomField.setEditable(false);
        contactField.setEditable(false);
        roleField.setEditable(false);

    }

    public void setDeleteCallback(Employes employe) {
        Employes selectedEmploye = employeListView.getSelectionModel().getSelectedItem();
        if (selectedEmploye != null) {
            employeListView.getItems().remove(selectedEmploye);
        }
    }
    //private void afficherHistorique() {
        //historiqueListView.getItems().setAll(employe.getHistoriqueProjets());
    //}

    @FXML
    private void handleEdit() {
        // Active la modification des champs
        nomField.setEditable(true);
        prenomField.setEditable(true);
        contactField.setEditable(true);
        roleField.setEditable(true);
    }

    @FXML
    private void handleSave() {
        if (employe != null) {
            employe.setNom(nomField.getText());
            employe.setPrenom(prenomField.getText());
            employe.setContact(contactField.getText());
            employe.setRole(roleField.getText());
            afficherMessage("Modification réussie", "Les informations de l'employé ont été mises à jour.");

            // Désactive l'édition après enregistrement
            nomField.setEditable(false);
            prenomField.setEditable(false);
            contactField.setEditable(false);
            roleField.setEditable(false);
        }
    }

    private void afficherMessage(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

