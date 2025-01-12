package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainViewController {

    @FXML
    private ListView<String> employeListView;

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
        String employeInfo = String.format("%s %s - %s (%s)", nom, prenom, role, contact);
        employeListView.getItems().add(employeInfo);
    }
}

