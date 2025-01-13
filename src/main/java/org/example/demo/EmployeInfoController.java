package org.example.demo;

import Entreprise.Employes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

        // Méthode pour définir les détails d'un employé
        public void setEmployeDetails(Employes employe) {
            if (employe != null) {
                nomLabel.setText(employe.getNom());
                prenomLabel.setText(employe.getPrenom());
                contactLabel.setText(employe.getContact());
                roleLabel.setText(employe.getRole());
                idLabel.setText(String.valueOf(employe.getId()));
            }
        }
    }

