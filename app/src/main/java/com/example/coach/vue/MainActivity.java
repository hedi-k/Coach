package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;




import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {
    private Controle controle;
    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;
    private RadioButton rdFemme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Fait le lien entre les propriétés et les valeurs entrées dans l'application.
     */
    private void init() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = (EditText) findViewById(R.id.txtTaille);
        txtAge = (EditText) findViewById(R.id.txtAge);
        rdHomme = (RadioButton) findViewById(R.id.rdHomme);
        imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
        btnCalc = (Button) findViewById(R.id.btnCalc);
        lblIMG = (TextView) findViewById(R.id.lblIMG);
        this.controle = Controle.getInstance(this);
        ecouteCalcul();
        rdFemme = (RadioButton) findViewById(R.id.rdFemme);
        recupProfil();

    }


    /**
     * Action du bouton calculer
     * Sur le bouton, on applique la méthode setOnClickListener qui permet d’affecter un listener (donc une
     * écoute) afin de capturer l’événement du clic sur le bouton.
     * <p>
     * Ensuite, on va tenter de récupérer les saisies, mais attention, uniquement si elles sont convertibles( try/catch)
     * if pour le sexe et if pour controle
     * appel la méthode afficheResult avec les propriétés valorisés
     */
    private void ecouteCalcul() {
        btnCalc.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Integer poids = 0, taille = 0, age = 0, sexe = 0;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch (Exception e) {
                }

                if (rdHomme.isChecked()) {
                    sexe = 1;
                }
                if (poids == 0 || taille == 0 || age == 0) {
                    Toast.makeText(MainActivity.this, "Tout les champs doivent être remplis !", Toast.LENGTH_SHORT).show();
                } else {
                    affichResult(poids, taille, age, sexe);
                }

            }
        });

    }

    /**
     * 1  créer le profil en utilisant la méthode creerProfil de la classe Controle
     * 2 récupérer dans des variables locales le message généré et le résultat du calcul de l’img
     * 3 test le message pour savoir quelle image utiliser
     * 3bis mettez le texte en vert si l’img est normal, en rouge dans les autres cas
     * 4 afficher aussi la valeur de l’img (avec la méthode setText sur lblIMG)
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void affichResult(int poids, int taille, int age, int sexe) {
        controle.creerProfil(poids, taille, age, sexe, this);
        String leMessage = controle.getMessage();
        float resultImg = controle.getImg();

        switch (leMessage) {
            case "normal":
                imgSmiley.setImageResource(R.drawable.normal);
                lblIMG.setTextColor(Color.GREEN);
                break;
            case "trop maigre":
                imgSmiley.setImageResource(R.drawable.maigre);
                lblIMG.setTextColor(Color.RED);
                break;
            case "trop de graisse":
                imgSmiley.setImageResource(R.drawable.graisse);
                lblIMG.setTextColor(Color.RED);
                break;
        }


        lblIMG.setText(resultImg + " : IMG " + leMessage);

    }

    private void recupProfil() {
        if (controle.getTaille() != 0) {
            txtTaille.setText(""+controle.getTaille());
            txtPoids.setText((""+controle.getPoids()));
            txtAge.setText(""+controle.getAge());
            if(controle.getSexe() == 0){
                rdFemme.setChecked(true);
            }else{
                rdHomme.setChecked(true);
            }
            btnCalc.performClick(); //Simule l'appui du btn clique
        }


    }

}
