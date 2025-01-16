package Entreprise;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjetsTest {

    @Test
    void getId() {
        Projets projet = new Projets("Développement Application Mobile", LocalDate.of(2025, 1, 15), LocalDate.of(2025, 6, 15));
        projet.setId(1);
        assertEquals(1, projet.getId(), "L'ID du projet doit être 1.");
    }

    @Test
    void setId() {
        Projets projet = new Projets("Refonte du Site Web", LocalDate.of(2025, 2, 1), LocalDate.of(2025, 5, 1));
        projet.setId(2);
        assertEquals(2, projet.getId(), "L'ID du projet doit être 2 après modification.");
    }

    @Test
    void getNomDeProjet() {
        Projets projet = new Projets("Mise en place d'un CRM", LocalDate.of(2025, 3, 1), LocalDate.of(2025, 9, 1));
        assertEquals("Mise en place d'un CRM", projet.getNomDeProjet(), "Le nom du projet doit être 'Mise en place d'un CRM'.");
    }

    @Test
    void setNomDeProjet() {
        Projets projet = new Projets("Initial Name", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 12, 31));
        projet.setNomDeProjet("Projet Final");
        assertEquals("Projet Final", projet.getNomDeProjet(), "Le nom du projet doit être 'Projet Final' après modification.");
    }

    @Test
    void getDebut() {
        Projets projet = new Projets("Développement Application Mobile", LocalDate.of(2025, 1, 15), LocalDate.of(2025, 6, 15));
        assertEquals(LocalDate.of(2025, 1, 15), projet.getDebut(), "La date de début doit être '2025-01-15'.");
    }

    @Test
    void setDebut() {
        Projets projet = new Projets("Refonte du Site Web", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 6, 30));
        projet.setDebut(LocalDate.of(2025, 2, 1));
        assertEquals(LocalDate.of(2025, 2, 1), projet.getDebut(), "La date de début doit être mise à jour à '2025-02-01'.");
    }

    @Test
    void getFin() {
        Projets projet = new Projets("Mise en place d'un CRM", LocalDate.of(2025, 3, 1), LocalDate.of(2025, 9, 1));
        assertEquals(LocalDate.of(2025, 9, 1), projet.getFin(), "La date de fin doit être '2025-09-01'.");
    }

    @Test
    void setFin() {
        Projets projet = new Projets("Refonte du Site Web", LocalDate.of(2025, 1, 1), LocalDate.of(2025, 6, 30));
        projet.setFin(LocalDate.of(2025, 12, 31));
        assertEquals(LocalDate.of(2025, 12, 31), projet.getFin(), "La date de fin doit être mise à jour à '2025-12-31'.");
    }

    @Test
    void ajouterEmploye() {
        Projets projet = new Projets("Développement Application Mobile", LocalDate.of(2025, 1, 15), LocalDate.of(2025, 6, 15));
        Employes employe = new Employes("Martin", "Paul", "paul.martin@example.com", "Designer");
        projet.ajouterEmploye(employe);
        assertTrue(projet.getEmployes().contains(employe), "L'employé doit être ajouté au projet.");
    }

    @Test
    void supprimerEmploye() {
        Projets projet = new Projets("Développement Application Mobile", LocalDate.of(2025, 1, 15), LocalDate.of(2025, 6, 15));
        Employes employe = new Employes("Martin", "Paul", "paul.martin@example.com", "Designer");
        projet.ajouterEmploye(employe);
        projet.supprimerEmploye(employe);
        assertFalse(projet.getEmployes().contains(employe), "L'employé doit être supprimé du projet.");
    }

    @Test
    void ajouterTache() {
        Projets projet = new Projets("Refonte du Site Web", LocalDate.of(2025, 2, 1), LocalDate.of(2025, 5, 1));
        Taches tache = new Taches("Créer le design", "Moyenne", LocalDate.of(2025, 2, 15), "Focus sur les pages principales.");
        projet.ajouterTache(tache);
        assertTrue(projet.getTaches().contains(tache), "La tâche doit être ajoutée au projet.");
    }

    @Test
    void supprimerTache() {
        Projets projet = new Projets("Développement App KANBAN", LocalDate.of(2024, 12, 15), LocalDate.of(2025, 1, 17));
        Taches tache = new Taches("Créer l'interface", "Haute", LocalDate.of(2025, 1, 10), "Respecter les normes UX.");
        projet.ajouterTache(tache);
        projet.supprimerTache(tache);
        assertFalse(projet.getTaches().contains(tache), "La tâche doit être supprimée du projet.");
    }

    @Test
    void getTaches() {
        Projets projet = new Projets("Mise en place d'un CRM", LocalDate.of(2025, 3, 1), LocalDate.of(2025, 9, 1));
        Taches tache1 = new Taches("Phase de test", "Haute", LocalDate.of(2025, 8, 1), "Tester les fonctionnalités principales.");
        Taches tache2 = new Taches("Documentation", "Basse", LocalDate.of(2025, 8, 15), "Documenter les modules développés.");
        projet.ajouterTache(tache1);
        projet.ajouterTache(tache2);
        List<Taches> taches = projet.getTaches();
        assertEquals(2, taches.size(), "Le projet doit contenir exactement deux tâches.");
    }

    @Test
    void projetGetTache() {
        Projets projet = new Projets("Développement Application Mobile", LocalDate.of(2025, 1, 15), LocalDate.of(2025, 6, 15));
        Taches tache = new Taches("Finaliser le développement", "Haute", LocalDate.of(2025, 5, 1), "Inclure les dernières fonctionnalités.");
        projet.ajouterTache(tache);
        Taches result = projet.projetGetTache(tache.getTitre());
        assertEquals(tache, result, "La tâche récupérée doit correspondre à la tâche ajoutée.");
    }
}
