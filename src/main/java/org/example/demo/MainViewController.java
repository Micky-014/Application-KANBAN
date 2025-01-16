package org.example.demo;

import Entreprise.Employes;
import Entreprise.Projets;
import Entreprise.Taches;
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

    Employes employe1 = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
    Employes employe2 = new Employes("Martin", "Claire", "claire.martin@email.com", "Chef de projet");
    Employes employe3 = new Employes("Bernard", "Pierre", "pierre.bernard@email.com", "Analyste");
    Employes employe4 = new Employes("Lemoine", "Sophie", "sophie.lemoine@email.com", "Responsable RH");
    Employes employe5 = new Employes("Durand", "Marc", "marc.durand@email.com", "Designer");
    Employes employe6 = new Employes("Dumetz", "Théotim","theotim.dumetz@eleve.isep.fr","Développeur Java");
    Employes employe7 = new Employes("Anton Nixon","Kevin","kevin.anton-nixon@eleve.isep.fr","Développeur Java");
    Employes employe8 = new Employes("Harault","Micky","micky.harault@eleve.isep.fr","Développeur Java");

    Projets projet1 = new Projets("Développement Application Mobile", LocalDate.of(2025, 1, 15), LocalDate.of(2025, 6, 15));
    Projets projet2 = new Projets("Refonte du Site Web", LocalDate.of(2025, 2, 1), LocalDate.of(2025, 5, 1));
    Projets projet3 = new Projets("Mise en place d'un CRM", LocalDate.of(2025, 3, 1), LocalDate.of(2025, 9, 1));
    Projets projet4 = new Projets("Développement App KANBAN", LocalDate.of(2024, 12, 15), LocalDate.of(2025, 1, 17));

    Taches tache1 = new Taches("Rédaction du rapport", "Haute", LocalDate.of(2025, 1, 20), "Doit inclure les données les plus récentes.");
    Taches tache2 = new Taches("Préparation de la présentation", "Moyenne", LocalDate.of(2025, 1, 22), "Inclure les graphiques pour le projet.");
    Taches tache3 = new Taches("Révision du code", "Basse", LocalDate.of(2025, 1, 25), "Vérifier les bugs et améliorer la lisibilité.");
    Taches tache4 = new Taches("Réunion avec le client", "Haute", LocalDate.of(2025, 1, 18), "Confirmer les besoins et finaliser les exigences.");

    @FXML
    private ListView<Employes> employeListView;

    private ObservableList<Employes> employeList = FXCollections.observableArrayList();

    @FXML
    private ListView<Projets> projetsListView;

    private ObservableList<Projets> projetsList = FXCollections.observableArrayList();

    @FXML
    public void handleAjouterEmploye() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEmploye.fxml"));
            Parent root = loader.load();
            AddEmployeController controller = loader.getController();
            controller.setMainController(this);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddProjet.fxml"));
            Parent root = loader.load();
            AddProjetController controller = loader.getController();
            controller.setMainController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ajouter un projet");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEmployeToList(String nom, String prenom, String contact, String role) {
        Employes newEmploye = new Employes(nom, prenom, contact, role);
        employeList.add(newEmploye);
        employeListView.setItems(employeList);
    }

    public void addProjetToList(String nom, LocalDate debut, LocalDate fin) {
        Projets newProjet = new Projets(nom, debut, fin);
        projetsList.add(newProjet);
        projetsListView.setItems(projetsList);
    }

    @FXML
    public void initialize() {
        projet4.ajouterTache(tache1);
        projet4.ajouterTache(tache2);
        projet4.ajouterTache(tache3);
        projet4.ajouterTache(tache4);
        projet4.ajouterEmploye(employe6);
        projet4.ajouterEmploye(employe7);
        projet4.ajouterEmploye(employe8);
        tache1.addEquipe(employe6);
        tache2.addEquipeDisponible(employe7);

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
        projetsList.addAll(Projets.getListeProjets());
        projetsListView.setItems(projetsList);

        projetsListView.setCellFactory(param -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Projets projet, boolean empty) {
                super.updateItem(projet, empty);
                if (empty || projet == null) {
                    setText(null);
                } else {
                    setText(projet.getId() + " - " + projet.getNomDeProjet());
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

    private void showEmployeDetails(Employes employe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeInfo.fxml"));
            Parent root = loader.load();

            EmployeInfoController controller = loader.getController();
            controller.setEmployeDetails(employe);
            controller.setMainController(this);
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
            controller.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Détails de l'Employé");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshListView() {
        employeList.setAll(Employes.getListeEmployes());
        employeListView.refresh();
        projetsList.setAll(Projets.getListeProjets());
        projetsListView.refresh();
    }

    public void deleteEmployeFromList(Employes employe) {
        if (employe != null) {
            Employes.suprEmploye(employe);
            employeList.remove(employe);
            employeListView.refresh();
        }
    }

    public void deleteProjetFromList(Projets projet) {
        if (projet != null) {
            Projets.suprProjet(projet);
            projetsList.remove(projet);
            projetsListView.refresh();
            for (Employes employes : projet.getEmployes()) {
                employes.suprHistoriqueProjets(projet);
            }
        }
    }

    public void showKanban(Projets projet) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("KANBAN.fxml"));
            Parent root = loader.load();
            KanbanController controller = loader.getController();
            controller.setProjet(projet);
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Kanban - " + projet.getNomDeProjet());
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
