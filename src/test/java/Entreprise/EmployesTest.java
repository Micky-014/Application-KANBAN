package Entreprise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployesTest {

    @Test
    void getId() {
        Employes employe = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
        employe.setId(1);
        assertEquals(1, employe.getId(), "L'ID de l'employé doit être 1.");
    }

    @Test
    void setId() {
        Employes employe = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
        employe.setId(2);
        assertEquals(2, employe.getId(), "L'ID de l'employé doit être 2 après modification.");
    }

    @Test
    void getNom() {
        Employes employe = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
        assertEquals("Dupont", employe.getNom(), "Le nom de l'employé doit être 'Dupont'.");
    }

    @Test
    void setNom() {
        Employes employe = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
        employe.setNom("Durand");
        assertEquals("Durand", employe.getNom(), "Le nom de l'employé doit être 'Durand' après modification.");
    }

    @Test
    void getPrenom() {
        Employes employe = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
        assertEquals("Jean", employe.getPrenom(), "Le prénom de l'employé doit être 'Jean'.");
    }

    @Test
    void setPrenom() {
        Employes employe = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
        employe.setPrenom("Paul");
        assertEquals("Paul", employe.getPrenom(), "Le prénom de l'employé doit être 'Paul' après modification.");
    }

    @Test
    void getContact() {
        Employes employe = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
        assertEquals("jean.dupont@email.com", employe.getContact(),
                "Le contact de l'employé doit être 'jean.dupont@email.com'.");
    }

    @Test
    void setContact() {
        Employes employe = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
        employe.setContact("jean.durand@email.com");
        assertEquals("jean.durand@email.com", employe.getContact(),
                "Le contact de l'employé doit être 'jean.durand@email.com'.");
    }

    @Test
    void getRole() {
        Employes employe = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
        assertEquals("Développeur", employe.getRole(), "Le rôle de l'employé doit être 'Développeur'.");
    }

    @Test
    void setRole() {
        Employes employe = new Employes("Dupont", "Jean", "jean.dupont@email.com", "Développeur");
        employe.setRole("Chef de projet");
        assertEquals("Chef de projet", employe.getRole(), "Le rôle de l'employé doit être 'Chef de projet'.");
    }


}
