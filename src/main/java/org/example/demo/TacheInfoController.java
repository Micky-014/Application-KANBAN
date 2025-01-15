package org.example.demo;

import Entreprise.Taches;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;


import java.awt.*;
import java.time.LocalDate;

public class TacheInfoController {
    @FXML
    private Taches tache;
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

    public void setTache(Taches tache) {
        this.tache=tache;
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
        }
    }
    public void initialize() {
        prioriteComboBox.getItems().addAll("Haute", "Moyenne", "Basse");
        setFieldsEditable(false);
    }
    public void setFieldsEditable(boolean editable) {
        nomTacheField.setEditable(editable);
        prioriteComboBox.setDisable(!editable); // Disable pour ComboBox
        jourField.setEditable(editable);
        moisField.setEditable(editable);
        anneeField.setEditable(editable);
        commentairesField.setEditable(editable);
    }
    @FXML
    private void handleModifier(ActionEvent event) {
        setFieldsEditable(true);
    }
    @FXML
    private void handleSave(ActionEvent event) {
        tache.setTitre(nomTacheField.getText());
        tache.setPriorite(prioriteComboBox.getSelectionModel().getSelectedItem());
        tache.setCommentaires(commentairesField.getText());
        int jour = Integer.parseInt(jourField.getText());
        int mois = Integer.parseInt(moisField.getText());
        int annee = Integer.parseInt(anneeField.getText());
        tache.setDateLimite(LocalDate.of(annee,mois,jour));
        setFieldsEditable(false);
    }
}