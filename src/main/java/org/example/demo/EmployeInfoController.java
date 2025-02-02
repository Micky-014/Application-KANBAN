package org.example.demo;

import Entreprise.Employes;
import Entreprise.Projets;
import Entreprise.Taches;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    private MainViewController mainController;
    public void setMainController(MainViewController mainController) {
        this.mainController = mainController;
    }


    public void setEmployeDetails(Employes employe) {
        this.employe = employe;
        if (employe != null) {
            nomField.setText(employe.getNom());
            prenomField.setText(employe.getPrenom());
            contactField.setText(employe.getContact());
            roleField.setText(employe.getRole());
            idLabel.setText(String.valueOf(employe.getId()));
            afficherHistorique();
        }
        nomField.setEditable(false);
        prenomField.setEditable(false);
        contactField.setEditable(false);
        roleField.setEditable(false);
    }

    private void afficherHistorique() {
        historiqueListView.getItems().clear();
        for (Projets projet : employe.getProjetsAssocies()) {
            historiqueListView.getItems().add(projet.getNomDeProjet());
        }
    }

    @FXML
    private void handleEdit() {
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
            nomField.setEditable(false);
            prenomField.setEditable(false);
            contactField.setEditable(false);
            roleField.setEditable(false);
            if (mainController != null) {
                mainController.refreshListView();
            }
        }
    }

    private void afficherMessage(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleDelete() {
        if (employe != null && mainController != null) {
            if (showConfirmationDialog("Confirmation", "Voulez-vous vraiment supprimer cet employé ?")) {
                mainController.deleteEmployeFromList(employe);
                afficherMessage("Suppression réussie", "L'employé a été supprimé.");
                idLabel.getScene().getWindow().hide();
                for (Projets projets : Projets.getListeProjets()){
                    if (projets.getEmployes().contains(employe)) {
                        projets.getEmployes().remove(employe);
                        for (Taches taches : projets.getTaches()){
                            if (taches.getEquipe().contains(employe)) {
                                taches.getEquipe().remove(employe);
                            }
                            else if (taches.getEquipeDisponible().contains(employe)) {
                                taches.getEquipeDisponible().remove(employe);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean showConfirmationDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
    }
}

