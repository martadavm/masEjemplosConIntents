package com.example.usuario.masejemplosconintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button compartirWhatsapp;
    Button compartirFacebook;
    Button web;
    Button mapa;
    EditText texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto=findViewById(R.id.editText);
        compartirWhatsapp=findViewById(R.id.button);
        compartirFacebook=findViewById(R.id.buttonFacebook);
        mapa=findViewById(R.id.button3);
        web=findViewById(R.id.button4);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=texto.getText().toString();
                Intent intentWeb=new Intent(Intent.ACTION_VIEW);
                intentWeb.setData(Uri.parse(url));

                Intent seleccionador=Intent.createChooser(intentWeb,"Elige tu navegador.");
                startActivity(seleccionador);
            }
        });

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coordenadas="geo:38.2983421,-5.2736782";
                Intent intentMapa=new Intent(Intent.ACTION_VIEW);
                intentMapa.setData(Uri.parse(coordenadas));

                Intent seleccionador=Intent.createChooser(intentMapa,"Elige tu programa favorito.");
                startActivity(seleccionador);
            }
        });
        compartirFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentFacebook=new Intent(Intent.ACTION_SEND);
                intentFacebook.setType("text/plain");
                intentFacebook.putExtra(Intent.EXTRA_TEXT,texto.getText().toString());
                intentFacebook.setPackage("com.facebook.katana");

                //comprobar que existe whatsapp.
                Intent aux=getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                if(aux!=null)
                {
                    startActivity(intentFacebook);
                }else{
                    Toast.makeText(MainActivity.this, "Debes instalar Facebook.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        compartirWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWhatsapp=new Intent(Intent.ACTION_SEND);
                intentWhatsapp.setType("text/plain");
                intentWhatsapp.putExtra(Intent.EXTRA_TEXT,texto.getText().toString());
                intentWhatsapp.setPackage("com.whatsapp");

                //comprobar que existe whatsapp.
                Intent aux=getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                if(aux!=null)
                {
                    startActivity(intentWhatsapp);
                }else{
                    Toast.makeText(MainActivity.this, "Debes instalar Whatsapp.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
