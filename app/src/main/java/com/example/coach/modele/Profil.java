package com.example.coach.modele;

import android.database.sqlite.SQLiteDatabase;

import com.example.coach.outils.Serializer;

import java.io.Serializable;
import java.util.Date;


/**
 * Classe métier Profil
 * Contient les informations d'un profil
 */
public class Profil implements Serializable {
    private static final Integer minFemme = 15;//maigre si en dessous
    private static final Integer maxFemme = 30;//gros si au dessus
    private static final Integer minHomme = 10;//maigre si en dessous
    private static final Integer maxHomme = 25;//gros si au dessus
    private int poids;
    private int taille;
    private int age;
    private int sexe;
    private float img = 0;
    private String message = "";
    private Date dateMesure;


    /**
     * Constructeur de la classe profil, il valorise les propriétés nécessaires à la création.
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public Profil(int poids, int taille, int age, int sexe, Date dateMesure) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.dateMesure = dateMesure;
    }

    public int getPoids() {
        return poids;
    }

    public int getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public int getSexe() {
        return sexe;
    }
    public Date getDateMesure(){
        return dateMesure;
    }

    /**
     * Fonction qui calcule l'img et le retourne
     *
     * @return img
     */
    public float getImg() {
        if (img == 0) {
            float tailleCm = ((float) taille) / 100;
            img = ((1.2f * poids / (tailleCm * tailleCm)) + (0.23f * age) - (10.83f * sexe) - 5.4f);
        }
        return img;
    }

    /**
     * Retourne le message correspondant en fonction de l'img
     *
     * @return la proriété message valorisé par (trop maigre, normal ou trop de graisse) en fonction de l'img
     */
    public String getMessage() {
        if (message == "") {
            img = getImg();
            if (sexe == 0) {
                if (img > maxFemme) {
                    message = "trop de graisse";
                } else if (img < minFemme) {
                    message = "trop maigre";
                } else {
                    message = "normal";
                }
            } else {
                if (img > maxHomme) {
                    message = "trop de graisse";
                } else if (img < minHomme) {
                    message = "trop maigre";
                } else {
                    message = "normal";
                }
            }
        }
        return message;
    }


}
