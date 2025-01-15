package org.example.demo;

import Entreprise.Taches;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TacheInfoController {
    @FXML
    private Taches tache;
    @FXML
    private Label titreLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label prioriteLabel;
    @FXML
    private Label statutLabel;

    public void setTache(Taches tache) {
        this.tache=tache;
    }
}