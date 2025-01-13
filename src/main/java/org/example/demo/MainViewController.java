package org.example.demo;

import Entreprise.Employes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainViewController {


    @FXML
    private ListView<Employes> employeListView;

    private ObservableList<Employes> employeList = FXCollections.observableArrayList();

    @FXML
    public void handleAjouterEmploye() {
        try {
            // Charger la fenêtre d'ajout d'employé
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addEmploye.fxml"));
            Parent root = loader.load();

            // Obtenir une instance du contrôleur et transmettre la référence
            AddEmployeController controller = loader.getController();
            controller.setMainController(this);

            // Afficher la fenêtre d'ajout d'employé
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ajouter un employé");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Méthode pour ajouter un employé à la ListView
    public void addEmployeToList(String nom, String prenom, String contact, String role) {
        Employes newEmploye = new Employes(nom, prenom, contact, role);
        employeList.add(newEmploye); // Ajoute directement l'objet Employes
    }

    @FXML
    public void initialize() {
        employeList.addAll(Employes.getListeEmployes());
        employeListView.setItems(employeList);

        employeListView.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Employes employe, boolean empty) {
                super.updateItem(employe, empty);
                if (empty || employe == null) {
                    setText(null);
                } else {
                    setText(employe.getNom() + " " + employe.getPrenom());
                }
            }
        });

        employeListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-clic
                Employes selectedEmploye = employeListView.getSelectionModel().getSelectedItem();
                if (selectedEmploye != null) {
                    showEmployeDetails(selectedEmploye);
                }
            }
        });
    }

    // Méthode pour afficher les détails d'un employé dans une nouvelle fenêtre
    private void showEmployeDetails(Employes employe) {
        try {
            // Charger la vue des détails de l'employé
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeInfo.fxml"));
            Parent root = loader.load();

            // Obtenir le contrôleur associé
            EmployeInfoController controller = loader.getController();

            // Passer les détails de l'employé au contrôleur
            controller.setEmployeDetails(employe);

            //controller.setDeleteCallback(employe);
            //    Employes.suprEmploye(employe); // **Supprimer de la liste globale**
              //  employeList.remove(employe); // **Supprimer de l'ObservableList**



            // Afficher la fenêtre
            Stage stage = new Stage();
            stage.setTitle("Détails de l'Employé");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


