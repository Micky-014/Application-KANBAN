package org.example.demo;

import Entreprise.Employes;
import Entreprise.Projets;
import Entreprise.Taches;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ContextMenu;


import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class  TacheInfoController {
    @FXML
    private Taches tache;
    @FXML
    private Projets projet;
    @FXML
    private TextField nomTacheField;
    @FXML
    private ComboBox<String> prioriteComboBox;
    @FXML
    private TextField jourField;
    @FXML
    private TextField moisField;
    @FXML
    private TextField anneeField;
    @FXML
    private TextArea commentairesField;
    @FXML
    private Label statutField;
    @FXML
    private ComboBox<Employes> employesComboBox;
    @FXML
    private VBox employesField;
    @FXML
    KanbanController kanbanController;
    @FXML
    private Label taskLabel;
    @FXML
    private List<Employes> employesProjet;

    public void setController(KanbanController controller) {
        this.kanbanController = controller;
    }
    public void setLabel(Label label) {
        this.taskLabel = label;
    }
    public void setProjet(Projets projet) {
        this.projet = projet;
        if (projet != null) {
            System.out.println("Nom du projet = " + projet.getNomDeProjet());
        }
        else {
            System.out.println("Erreur chargement projet");
        }
    }

    public void setTache(Taches tache) {
        this.tache=tache;
        System.out.println(projet.getEmployes());
        List<Employes> listEmployes = new ArrayList<>(projet.getEmployes());
        tache.setEquipeDisponible(listEmployes);
        if (tache == null) {
            System.err.println("Erreur : L'objet 'tache' est null !");
        } else {
            System.out.println("Titre de la tâche : " + tache.getTitre());
            nomTacheField.setText(tache.getTitre());
            prioriteComboBox.getSelectionModel().select(tache.getPriorite());
            jourField.setText(String.valueOf(tache.getDateLimite().getDayOfMonth()));
            moisField.setText(String.valueOf(tache.getDateLimite().getMonthValue()));
            anneeField.setText(String.valueOf(tache.getDateLimite().getYear()));
            commentairesField.setText(tache.getCommentaires());
            statutField.setText(String.valueOf(tache.getStatut()));
            for (Employes employes : tache.getEquipe()){
                if (tache.getEquipeDisponible().contains(employes)){
                    tache.suprEquipeDisponible(employes);
                }
                System.out.println(employes);
                
                Label newLabel = new Label(employes.getNom()+" "+employes.getPrenom());
                newLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;"); // Exemple de style
                enableContextMenu(newLabel, employes);
                employesField.getChildren().add(newLabel);
            }
            employesComboBox.getItems().addAll(tache.getEquipeDisponible());
        }
    }

    public void initialize() {
        prioriteComboBox.getItems().addAll("Haute", "Moyenne", "Basse");
        setFieldsEditable(false);
    }

    public void setFieldsEditable(boolean editable) {
        nomTacheField.setEditable(editable);
        prioriteComboBox.setDisable(!editable);
        jourField.setEditable(editable);
        moisField.setEditable(editable);
        anneeField.setEditable(editable);
        commentairesField.setEditable(editable);
        employesComboBox.setDisable(!editable);
    }

    @FXML
    private void handleModifier(ActionEvent event) {
        setFieldsEditable(true);
    }

    @FXML
    private void handleSave(ActionEvent event) {
        taskLabel.setText(nomTacheField.getText());
        projet.suprimerChercheTache(tache.getTitre());
        tache.setTitre(nomTacheField.getText());
        projet.projetAddTache(nomTacheField.getText(),tache);
        tache.setPriorite(prioriteComboBox.getSelectionModel().getSelectedItem());
        tache.setCommentaires(commentairesField.getText());
        int jour = Integer.parseInt(jourField.getText());
        int mois = Integer.parseInt(moisField.getText());
        int annee = Integer.parseInt(anneeField.getText());
        tache.setDateLimite(LocalDate.of(annee,mois,jour));
        setFieldsEditable(false);
    }

    private void reinitSuprComboBox(Employes employe) {
        employesComboBox.getItems().clear();
        tache.addEquipe(employe);
        tache.suprEquipeDisponible(employe);
        employesComboBox.getItems().addAll(tache.getEquipeDisponible());
    }

    @FXML
    private void handleAjouterEmploye(ActionEvent event) {
        Employes employe = employesComboBox.getSelectionModel().getSelectedItem();
        Label newLabel = new Label(employe.getNom()+" "+employe.getPrenom());
        reinitSuprComboBox(employe);
        newLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
        employesField.getChildren().add(newLabel);
        enableContextMenu(newLabel, employe);
    }

    private void enableContextMenu(Label taskLabel, Employes employe) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Supprimer");
        deleteItem.setOnAction(event -> {
            VBox parentColumn = (VBox) taskLabel.getParent();
            parentColumn.getChildren().remove(taskLabel);
            tache.suprEquipe(employe);
            tache.addEquipeDisponible(employe);
            employesComboBox.getItems().clear();
            employesComboBox.getItems().addAll(tache.getEquipeDisponible());
            System.out.println("Employé supprimé : " + employe.getNom() + " " + employe.getPrenom());
        });
        contextMenu.getItems().add(deleteItem);
        taskLabel.setOnContextMenuRequested(event -> {
            contextMenu.show(taskLabel, event.getScreenX(), event.getScreenY());
        });
    }

}