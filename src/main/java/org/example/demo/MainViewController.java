package org.example.demo;

import Entreprise.Employes;
import Entreprise.Projets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.time.LocalDate;

public class MainViewController {


    @FXML
    private ListView<Employes> employeListView;

    private ObservableList<Employes> employeList = FXCollections.observableArrayList();

    @FXML
    private ListView<Projets> projetsListView;

    private ObservableList<Projets> projetsList = FXCollections.observableArrayList();

    @FXML
    public void handleAjouterEmploye() {
        try {
            // Charger la fenêtre d'ajout d'employé
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmploye.fxml"));
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
    @FXML
    public void handleAjouterProjet() {
        try {
            // Charger la fenêtre d'ajout d'employé
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProjet.fxml"));
            Parent root = loader.load();

            // Obtenir une instance du contrôleur et transmettre la référence
            AddProjetController controller = loader.getController();
            controller.setMainController(this);

            // Afficher la fenêtre d'ajout d'employé
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ajouter un projet");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Méthode pour ajouter un employé à la ListView
    public void addEmployeToList(String nom, String prenom, String contact, String role) {
        Employes newEmploye = new Employes(nom, prenom, contact, role);
        employeList.add(newEmploye); // Ajoute directement l'objet Employes
        employeListView.setItems(employeList);
    }
    public void addProjetToList(String nom, LocalDate debut, LocalDate fin) {
        Projets newProjet = new Projets(nom, debut, fin);
        projetsList.add(newProjet); // Ajoute directement l'objet Employes
        projetsListView.setItems(projetsList);
    }

    @FXML
    public void initialize() {
        employeList.addAll(Employes.getListeEmployes());
        employeListView.setItems(employeList); // Assurez-vous que la liste observable est définie ici.

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

        projetsList.addAll(Projets.getListeProjets());
        projetsListView.setItems(projetsList);

        projetsListView.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Projets projet, boolean empty) {
                super.updateItem(projet, empty);
                if (empty || projet == null) {
                    setText(null);
                } else {
                    setText(projet.getNomDeProjet() + " " + projet.getId());
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

        projetsListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-clic
                Projets selectedProjet = projetsListView.getSelectionModel().getSelectedItem();
                if (selectedProjet != null) {
                    showProjetDetails(selectedProjet);
                }
            }
        });
    }

    // Méthode pour afficher les détails d'un employé dans une nouvelle fenêtre
    private void showEmployeDetails(Employes employe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeInfo.fxml"));
            Parent root = loader.load();

            EmployeInfoController controller = loader.getController();
            controller.setEmployeDetails(employe);
            controller.setMainController(this); // Transmettre le contrôleur principal

            Stage stage = new Stage();
            stage.setTitle("Détails de l'Employé");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showProjetDetails(Projets projet) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProjetInfo.fxml"));
            Parent root = loader.load();

            ProjetInfoController controller = loader.getController();
            controller.setProjetDetails(projet);
            controller.setMainController(this); // Transmettre le contrôleur principal

            Stage stage = new Stage();
            stage.setTitle("Détails de l'Employé");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void refreshListView() {
        employeList.setAll(Employes.getListeEmployes()); // Recharge la liste globale
        employeListView.refresh(); // Rafraîchit visuellement
        projetsList.setAll(Projets.getListeProjets());
        projetsListView.refresh();
    }

    public void deleteEmployeFromList(Employes employe) {
        if (employe != null) {
            Employes.suprEmploye(employe);// Suppression de la liste globale
            employeList.remove(employe);  // Suppression de la `ObservableList`
            employeListView.refresh();    // Rafraîchir visuellement la `ListView`
        }
    }
    public void deleteProjetFromList(Projets projet) {
        if (projet != null) {
            Projets.suprProjet(projet); // Suppression de la liste globale
            projetsList.remove(projet); // Suppression de la `ObservableList`
            projetsListView.refresh();  // Rafraîchir visuellement la `ListView`
        }
    }
}
