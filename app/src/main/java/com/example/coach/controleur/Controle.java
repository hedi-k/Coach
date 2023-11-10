package com.example.coach.controleur;

import android.content.Context;
import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;
import java.util.Date;

public final class Controle {
    private static String nomFic = "saveprofil";
    private static Controle instance = null;
    private static Profil profil;
    private static AccesLocal accesLocal;

    private Controle() {
        super();
    }

    public static final Controle getInstance(Context unContext) {
        if (Controle.instance == null) {
            Controle.instance = new Controle();
            // recupSerialize(unContext);    utilisée pour la sérialisation
            accesLocal = AccesLocal.getInstance(unContext);
            profil = accesLocal.recupDernier();
        }
        return Controle.instance;
    }

    public void creerProfil(int poids, int taille, int age, int sexe, Context unContext) {

        this.profil = new Profil(poids, taille, age, sexe, new Date());
        //Serializer.serialize(nomFic, profil, unContext);  utilisée pour la sérialisation
        accesLocal.Ajout(profil);
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

    private static void recupSerialize(Context unContext) {
        profil = (Profil) Serializer.deSerialize(nomFic, unContext);
    }

    public int getPoids() {
        if (this.profil == null) {
            return 0;
        } else {
            return this.profil.getPoids();
        }
    }

    public int getTaille() {
        if (this.profil == null) {
            return 0;
        } else {
            return this.profil.getTaille();
        }
    }

    public int getAge() {
        if (this.profil == null) {
            return 0;
        } else {
            return this.profil.getAge();
        }
    }

    public int getSexe() {
        if (this.profil == null) {
            return 0;
        } else {
            return this.profil.getSexe();
        }
    }
}
