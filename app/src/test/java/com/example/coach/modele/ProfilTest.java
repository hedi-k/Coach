package com.example.coach.modele;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProfilTest {
    //création d'un profil (femme 67kg 1m65 35ans)
    private Profil profil = new Profil(67,165,35,0);
    //résultat de l'img correspondant
    private float img = 32.2f;
    //message correspondant
    private String message ="trop de graisse";

    @Test
    public void getImg() {
        assertEquals(img, profil.getImg(), (float)0.1);
    }

    @Test
    public void getMessage() {
        assertEquals(message, profil.getMessage());
    }
}