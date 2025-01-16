package org.example.demo;

import Entreprise.Employes;
import Entreprise.Projets;
import Entreprise.Taches;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KanbanController {

    @FXML
    private VBox tacheAFaire;
    @FXML
    private VBox tacheEnCours;
    @FXML
    private VBox tacheTerminee;
    @FXML
    private VBox Employes;

    private Projets projet;
    private List<Taches> taches;

    public void handleAjouterTache() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTache.fxml"));
            Parent root = loader.load();

            AddTacheController addTacheController = loader.getController();
            addTacheController.setMainController(this);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Ajouter une Tâche");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setProjet(Projets projet) {
        this.projet = projet;
        initializeKanban();
    }

    @FXML
    private void initializeKanban() {
        if (projet != null) {
            taches = projet.getTaches();
            List<Employes> listeEmployes = new ArrayList<>(projet.getEmployes());
            for (Taches tache : taches) {
                Label tacheLabel = new Label(tache.getTitre());
                tacheLabel.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");
                switch (tache.getStatut()) {
                    case "A faire" -> tacheAFaire.getChildren().add(tacheLabel);
                    case "En cours" -> tacheEnCours.getChildren().add(tacheLabel);
                    case "Terminée" -> tacheTerminee.getChildren().add(tacheLabel);
                }
                enableDragAndDrop(tacheLabel);
                enableContextMenu(tacheLabel);
            }
            for (Employes employes : listeEmployes) {
                Label empLabel = new Label(employes.getNom()+" "+employes.getPrenom());
                enableContextMenu2(empLabel, employes);
                empLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
                Employes.getChildren().add(empLabel);
            }
        }
    }


    private void enableContextMenu2(Label employeLabel, Employes employe) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Supprimer");
        deleteItem.setOnAction(event -> {
            Parent parent = employeLabel.getParent();
            if (parent instanceof VBox parentVBox) {
                parentVBox.getChildren().remove(employeLabel); // Supprimer le label de l'interface
                System.out.println("Employé supprimé : " + employe.getNom() + " " + employe.getPrenom());
                projet.supprimerEmploye(employe);
                for (Taches tache : projet.getTaches()) {
                    if (tache.getEquipeDisponible().contains(employe)) {
                        tache.suprEquipeDisponible(employe);
                    } else if (tache.getEquipe().contains(employe)) {
                        tache.suprEquipe(employe);
                    }
                }
            } else {
                System.err.println("Erreur : le parent du label n'est pas une VBox.");
            }
        });

        contextMenu.getItems().add(deleteItem);
        employeLabel.setOnContextMenuRequested(event -> {
            contextMenu.show(employeLabel, event.getScreenX(), event.getScreenY());
        });
    }

    public void ajouterTacheKanban(Taches tache) {
        Label tacheLabel = new Label(tache.getTitre());
        tacheLabel.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");
        tacheAFaire.getChildren().add(tacheLabel);
        enableDragAndDrop(tacheLabel);
        enableContextMenu(tacheLabel);

        System.out.println("Tâche ajoutée dans l'interface via le contrôleur principal : " + tache);
    }


    private void enableDragAndDrop(Label taskLabel) {
        VBox[] columns = {tacheAFaire, tacheEnCours, tacheTerminee};

        taskLabel.setOnDragDetected(event -> {
            Dragboard db = taskLabel.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(taskLabel.getText());
            db.setContent(content);
            event.consume();
        });
        for (VBox column : columns) {
            column.setOnDragOver(event -> {
                if (event.getGestureSource() != column && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            });
            column.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    String nomTache = db.getString();
                    Label newTaskLabel = new Label(nomTache);
                    newTaskLabel.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");
                    enableContextMenu(newTaskLabel);
                    column.getChildren().add(newTaskLabel);

                    Label taskLabelDrag = (Label) event.getGestureSource();
                    VBox sourceColumn = (VBox) taskLabelDrag.getParent();
                    sourceColumn.getChildren().remove(taskLabelDrag);

                    enableDragAndDrop(newTaskLabel);
                    Taches tache = projet.projetGetTache(nomTache);
                    if (column == tacheAFaire) {
                        tache.setStatut("A faire");
                        System.out.println("Statut mis à jour : A faire");
                    } else if (column == tacheEnCours) {
                        tache.setStatut("En cours");
                        System.out.println("Statut mis à jour : En cours");
                    } else if (column == tacheTerminee) {
                        tache.setStatut("Terminée");
                        System.out.println("Statut mis à jour : Terminée");
                    }
                }
                event.setDropCompleted(true);
                event.consume();
            });
        }
        enableDoubleClick(taskLabel);
    }

    private void enableContextMenu(Label taskLabel) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Supprimer");
        deleteItem.setOnAction(event -> {
            VBox parentColumn = (VBox) taskLabel.getParent();
            parentColumn.getChildren().remove(taskLabel);
            Taches tache = projet.projetGetTache(taskLabel.getText());
            projet.supprimerTache(tache);
            projet.suprimerChercheTache(taskLabel.getText());
            System.out.println("Tâche supprimée : " + taskLabel.getText());
        });
        contextMenu.getItems().add(deleteItem);

        taskLabel.setOnContextMenuRequested(event -> contextMenu.show(taskLabel, event.getScreenX(), event.getScreenY()));
    }

    private void enableDoubleClick(Label taskLabel) {
        taskLabel.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TacheInfo.fxml"));
                    Parent root = loader.load();
                    TacheInfoController infoTacheController = loader.getController();
                    Taches tache = projet.projetGetTache(taskLabel.getText());
                    infoTacheController.setProjet(projet);
                    infoTacheController.setTache(tache);
                    infoTacheController.initialize();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Informations sur la Tâche");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
