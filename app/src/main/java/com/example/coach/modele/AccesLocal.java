package com.example.coach.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {
    private String nomBase = "bddCoach.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private static AccesLocal instance;
    private SQLiteDatabase bd;

    /**
     * Constructeur : création du lien avec la bdd au format SQLite
     *
     * @param unContext
     */
    private AccesLocal(Context unContext) {
        accesBD = new MySQLiteOpenHelper(unContext, nomBase, versionBase);
    }

    /**
     * Création d'une instance unique de la classe
     * @param unContext
     * @return instance unique de la classe
     */
    public static AccesLocal getInstance(Context unContext) {
        if ( instance == null){
            instance = new AccesLocal(unContext);
        }
        return instance;
    }

    /**
     * Ajout un profil dans la bdd
     * ContentValues permet d'éviter les injection SQL
     * @param unProfil
     */
    public void Ajout(Profil unProfil){
        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("poids", unProfil.getPoids());
        values.put("taille", unProfil.getTaille());
        values.put("age", unProfil.getAge());
        values.put("sexe", unProfil.getSexe());
        values.put("dateMesure", unProfil.getDateMesure().toString());
        bd.insert("profil",null,values);
        bd.close();
    }

    public Profil recupDernier(){
        Profil unProfil = null;
        bd = accesBD.getReadableDatabase();
        String req ="select * from profil";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date dateMesure= MesOutils.convertStringToDate(curseur.getString(0));
            Log.d("date","*********** date="+dateMesure);
            Integer poids = curseur.getInt(1);
            Integer taille = curseur.getInt(2);
            Integer age = curseur.getInt(3);
            Integer sexe = curseur.getInt(4);
            unProfil = new Profil( poids, taille, age, sexe, dateMesure);
        }
        curseur.close();
        bd.close();
        return unProfil;

        }
    }

