package org.example.demo;

import Entreprise.Taches;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.*;
import java.time.LocalDate;

public class AddTacheController {
    @FXML
    private TextField nomTacheField;
    @FXML
    private TextField prioriteField;
    @FXML
    private TextField jourField;
    @FXML
    private TextField moisField;
    @FXML
    private TextField anneeField;
    @FXML
    private TextField commentairesField;

    @FXML
    public void handleAjouterTache(ActionEvent event) {
        // Récupérer les données des champs de texte
        String nom = nomTacheField.getText();
        String priorite = prioriteField.getText();
        int jour = Integer.parseInt(jourField.getText());
        int mois = Integer.parseInt(moisField.getText());
        int annee = Integer.parseInt(anneeField.getText());
        String commentaires = commentairesField.getText();
        if ((nom != "") && (priorite != "") && (jour > 0 && mois > 0 && annee > 1990) && (jour < 31 && mois < 12)){
            Taches tache = new Taches(nom,priorite,LocalDate.of(annee,mois,jour),commentaires);
        }
    }
}