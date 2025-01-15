package org.example.demo;

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
import java.util.List;

public class KanbanController {

    @FXML
    private VBox tacheAFaire;
    @FXML
    private VBox tacheEnCours;
    @FXML
    private VBox tacheTerminee;

    private Projets projet;
    private List<Taches> taches;

    public void handleAjouterTache() {
        try {
            // Charger la fenêtre d'ajout de tache
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTache.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle fenêtre pour la tâche
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
            for (Taches tache : taches) {
                Label tacheLabel = new Label(tache.getTitre());
                tacheLabel.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");

                switch (tache.getStatut()) {
                    case "A faire" -> tacheAFaire.getChildren().add(tacheLabel);
                    case "En cours" -> tacheEnCours.getChildren().add(tacheLabel);
                    case "Terminée" -> tacheTerminee.getChildren().add(tacheLabel);
                }

                // Activer le drag-and-drop et le menu contextuel pour chaque tâche
                enableDragAndDrop(tacheLabel);
                enableContextMenu(tacheLabel);
            }
        }
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
                    Label newTaskLabel = new Label(db.getString());
                    newTaskLabel.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");
                    column.getChildren().add(newTaskLabel);

                    VBox sourceColumn = (VBox) taskLabel.getParent();
                    sourceColumn.getChildren().remove(taskLabel);

                    // Mettre à jour la logique métier (statut de la tâche)
                    if (column == tacheAFaire) {
                        System.out.println("Statut mis à jour : A faire");
                    } else if (column == tacheEnCours) {
                        System.out.println("Statut mis à jour : En cours");
                    } else if (column == tacheTerminee) {
                        System.out.println("Statut mis à jour : Terminée");
                    }
                }
                event.setDropCompleted(true);
                event.consume();
            });
        }
    }

    private void enableContextMenu(Label taskLabel) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem("Supprimer");
        deleteItem.setOnAction(event -> {
            VBox parentColumn = (VBox) taskLabel.getParent();
            parentColumn.getChildren().remove(taskLabel);
            System.out.println("Tâche supprimée : " + taskLabel.getText());
        });
        contextMenu.getItems().add(deleteItem);

        taskLabel.setOnContextMenuRequested(event -> contextMenu.show(taskLabel, event.getScreenX(), event.getScreenY()));
    }
}
