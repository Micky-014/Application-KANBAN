package org.example.demo;

import Entreprise.Projets;
import Entreprise.Taches;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CalendrierController {

    @FXML
    private Label monthLabel;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private ListView<String> eventListView;

    private YearMonth currentMonth;
    private List<Projets> projets; // Liste des projets à afficher

    @FXML
    public void initialize() {
        currentMonth = YearMonth.now();
        projets = Projets.getListeProjets(); // Charger tous les projets
        afficherMois();
    }

    @FXML
    private void handlePreviousMonth() {
        currentMonth = currentMonth.minusMonths(1);
        afficherMois();
    }

    @FXML
    private void handleNextMonth() {
        currentMonth = currentMonth.plusMonths(1);
        afficherMois();
    }

    private void afficherMois() {
        calendarGrid.getChildren().clear();
        eventListView.getItems().clear();
        monthLabel.setText(currentMonth.getMonth() + " " + currentMonth.getYear());

        LocalDate firstDayOfMonth = currentMonth.atDay(1);
        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue(); // 1 = Lundi, 7 = Dimanche

        LocalDate currentDate = firstDayOfMonth.minusDays(dayOfWeek - 1);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 7; col++) {
                LocalDate date = currentDate.plusDays(row * 7 + col);
                Button dayButton = new Button(String.valueOf(date.getDayOfMonth()));
                dayButton.setPrefSize(50, 50);

                // Ajouter les événements associés à la date
                dayButton.setOnAction(event -> afficherEvenements(date));
                calendarGrid.add(dayButton, col, row);
            }
        }
    }

    private void afficherEvenements(LocalDate date) {
        eventListView.getItems().clear();

        for (Projets projet : projets) {
            if ((projet.getDebut().isEqual(date) || projet.getDebut().isBefore(date)) &&
                    (projet.getFin().isEqual(date) || projet.getFin().isAfter(date))) {
                eventListView.getItems().add("Projet : " + projet.getNomDeProjet());
            }

            for (Taches tache : projet.getTaches()) {
                if (tache.getDateLimite().isEqual(date)) {
                    eventListView.getItems().add("Tâche : " + tache.getTitre());
                }
            }
        }
    }
}

