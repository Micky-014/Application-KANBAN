package org.example.demo;

import Entreprise.Employes;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static Entreprise.Employes.*;

public class EmployeInfoController {

    @FXML
    private Label nomLabel;

    @FXML
    private Label prenomLabel;

    @FXML
    private Label contactLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label idLabel;

    private Employes employe; // Référence à l'employé actue
    private ObservableList<Employes> employeList; // Liste observable des employés
    private MainViewController mainController; // Référence au contrôleur principal
    // Méthode pour définir les détails d'un employé

    public void setEmployeDetails(Employes employe) {
        if (employe != null) {
            this.employe = employe;
            this.mainController = mainController;
            nomLabel.setText(employe.getNom());
            prenomLabel.setText(employe.getPrenom());
            contactLabel.setText(employe.getContact());
            roleLabel.setText(employe.getRole());
            idLabel.setText(String.valueOf(employe.getId()));
        }
    }

    private Runnable deleteCallback; // **Ajout d'une variable pour le callback**

    public void setDeleteCallback(Runnable deleteCallback) {
        this.deleteCallback = deleteCallback; // **Méthode pour définir le callback**
    }

    @FXML
    private void handleDeleteEmployee(ActionEvent event) {
        if (deleteCallback != null) {
            deleteCallback.run(); // **Appelle le callback pour synchroniser la suppression**
        }
    }
            @FXML
            private void deleteEmploye (ActionEvent event){
                // Demander confirmation
                suprEmploye(employe);
                employeList.remove(employe);
                // Fermer la fenêtre
                Stage stage = (Stage) nomLabel.getScene().getWindow();
                stage.close();
            }
        }


