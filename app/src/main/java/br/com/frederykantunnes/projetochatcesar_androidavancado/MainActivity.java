package br.com.frederykantunnes.projetochatcesar_androidavancado;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent a = new Intent(this, Login.class);
        startActivity(a);
        finish();
    }
}
