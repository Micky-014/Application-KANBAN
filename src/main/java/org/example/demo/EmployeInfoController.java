package org.example.demo;

import Entreprise.Employes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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

    @FXML
    private ListView<Employes> employeListView;

    @FXML
    private ListView<String> historiqueListView;

    private Employes employe;

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

    public void setDeleteCallback(Employes employe) {
        Employes selectedEmploye = employeListView.getSelectionModel().getSelectedItem();
        if (selectedEmploye != null) {
            employeListView.getItems().remove(selectedEmploye);
        }
    }
    //private void afficherHistorique() {
        //historiqueListView.getItems().setAll(employe.getHistoriqueProjets());
    //}
}

