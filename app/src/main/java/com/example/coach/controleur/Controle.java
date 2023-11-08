package com.example.coach.controleur;

import com.example.coach.modele.Profil;

public final class Controle {
    private static Controle instance = null;
    private static Profil profil;

    private Controle() {
        super();
    }

    public static final Controle getInstance() {
        if (Controle.instance == null) {
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    public void  creerProfil(int poids, int taille, int age, int sexe) {
        this.profil = new Profil(poids, taille, age, sexe);
    }

    public float getImg() {
        if (this.profil == null) {
            return 0;
        } else {
            return this.profil.getImg();
        }
    }

    public String getMessage() {
        if (this.profil == null) {
            return "";
        } else {
            return this.profil.getMessage();
        }

    }
}
