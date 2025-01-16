package Entreprise;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TachesTest {

    @Test
    void getId() {
        // Supposez que l'ID est défini automatiquement ou via le constructeur
        Taches tache = new Taches("Tâche 1", "Haute", LocalDate.of(2025, 1, 15), "Commentaires initiaux");
        assertNotNull(tache.getId(), "L'ID de la tâche ne doit pas être nul.");
    }

    @Test
    void getTitre() {
        Taches tache = new Taches("Tâche 2", "Moyenne", LocalDate.of(2025, 2, 15), "Commentaires initiaux");
        assertEquals("Tâche 2", tache.getTitre(), "Le titre de la tâche doit être 'Tâche 2'.");
    }

    @Test
    void setTitre() {
        Taches tache = new Taches("Ancien titre", "Moyenne", LocalDate.of(2025, 3, 15), "Commentaires");
        tache.setTitre("Nouveau titre");
        assertEquals("Nouveau titre", tache.getTitre(), "Le titre de la tâche doit être 'Nouveau titre'.");
    }

    @Test
    void getPriorite() {
        Taches tache = new Taches("Tâche 3", "Haute", LocalDate.of(2025, 4, 15), "Commentaires divers");
        assertEquals("Haute", tache.getPriorite(), "La priorité de la tâche doit être 'Haute'.");
    }

    @Test
    void setPriorite() {
        Taches tache = new Taches("Tâche 4", "Basse", LocalDate.of(2025, 5, 15), "Commentaires légers");
        tache.setPriorite("Moyenne");
        assertEquals("Moyenne", tache.getPriorite(), "La priorité de la tâche doit être 'Moyenne'.");
    }

    @Test
    void getDateLimite() {
        Taches tache = new Taches("Tâche 5", "Basse", LocalDate.of(2025, 6, 15), "Commentaires limites");
        assertEquals(LocalDate.of(2025, 6, 15), tache.getDateLimite(), "La date limite doit être '2025-06-15'.");
    }

    @Test
    void setDateLimite() {
        Taches tache = new Taches("Tâche 6", "Moyenne", LocalDate.of(2025, 7, 15), "Commentaires réglés");
        tache.setDateLimite(LocalDate.of(2025, 8, 15));
        assertEquals(LocalDate.of(2025, 8, 15), tache.getDateLimite(), "La date limite doit être '2025-08-15'.");
    }

    @Test
    void getCommentaires() {
        Taches tache = new Taches("Tâche 7", "Haute", LocalDate.of(2025, 9, 15), "Commentaires initiaux");
        assertEquals("Commentaires initiaux", tache.getCommentaires(), "Le commentaire doit être 'Commentaires initiaux'.");
    }

    @Test
    void setCommentaires() {
        Taches tache = new Taches("Tâche 8", "Moyenne", LocalDate.of(2025, 10, 15), "Commentaires secondaires");
        tache.setCommentaires("Nouveau commentaire");
        assertEquals("Nouveau commentaire", tache.getCommentaires(), "Le commentaire doit être 'Nouveau commentaire'.");
    }

    @Test
    void getResponsable() {
        Employes responsable = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
        Taches tache = new Taches("Tâche 9", "Basse", LocalDate.of(2025, 11, 15), "Commentaires");
        tache.setResponsable(responsable);
        assertEquals(responsable, tache.getResponsable(), "Le responsable doit être 'Jean Dupont'.");
    }

    @Test
    void setResponsable() {
        Employes responsable = new Employes("Martin", "Paul", "paul.martin@email.com", "Analyste");
        Taches tache = new Taches("Tâche 10", "Moyenne", LocalDate.of(2026, 1, 15), "Commentaires finaux");
        tache.setResponsable(responsable);
        assertEquals(responsable, tache.getResponsable(), "Le responsable doit être 'Paul Martin'.");
    }

    @Test
    void modifier() {
        Employes responsable = new Employes("Durand", "Claire", "claire.durand@email.com", "Chef de projet");
        Taches tache = new Taches("Tâche 11", "Haute", LocalDate.of(2026, 2, 15), "Commentaires à modifier");
        tache.modifier("Tâche modifiée", "Basse", LocalDate.of(2026, 3, 15), responsable);
        assertEquals("Tâche modifiée", tache.getTitre(), "Le titre doit être 'Tâche modifiée'.");
        assertEquals("Basse", tache.getPriorite(), "La priorité doit être 'Basse'.");
        assertEquals(LocalDate.of(2026, 3, 15), tache.getDateLimite(), "La date limite doit être '2026-03-15'.");
        assertEquals(responsable, tache.getResponsable(), "Le responsable doit être 'Claire Durand'.");
    }
}
