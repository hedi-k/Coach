package com.example.coach.modele;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;

public class ProfilTest {
    //création d'un profil (femme 67kg 1m65 35ans + date du jour)
    private Profil profil = new Profil(67,165,35,0, new Date());
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