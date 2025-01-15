package org.example.demo;

import Entreprise.Employes;
import Entreprise.Projets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddEmployeAuProjetController {

    @FXML
    private ListView<Employes> employeListView;

    private Projets projet;

    private ObservableList<Employes> employeList = FXCollections.observableArrayList();

    public void setProjet(Projets projet) {
        this.projet = projet;
        if (projet != null) {
            List<Employes> listEmployes =new ArrayList<>(Employes.getListeEmployes());
            for (Employes employes : projet.getEmployes()) {
                if (listEmployes.contains(employes)){
                    listEmployes.remove(employes);
                }
            }
            employeList.setAll(listEmployes); // Charger tous les employés disponibles
            employeListView.setItems(employeList);
        }
    }

    @FXML
    private void handleAjouterEmploye() {
        Employes selectedEmploye = employeListView.getSelectionModel().getSelectedItem();
        if (selectedEmploye == null) {
            showError("Erreur", "Veuillez sélectionner un employé.");
            return;
        }
        if (!projet.getEmployes().contains(selectedEmploye)) {
            projet.ajouterEmploye(selectedEmploye);
            selectedEmploye.ajouterProjet(projet);
            employeList.remove(selectedEmploye);
            showInfo("Succès", "Employé ajouté au projet.");
        } else {
            showError("Erreur", "Cet employé est déjà associé au projet.");
        }
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
