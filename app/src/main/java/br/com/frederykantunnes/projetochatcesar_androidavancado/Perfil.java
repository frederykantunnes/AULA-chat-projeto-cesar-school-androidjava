package br.com.frederykantunnes.projetochatcesar_androidavancado;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos.ContactsViewModel;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.Chats;
import de.hdodenhof.circleimageview.CircleImageView;

public class Perfil extends AppCompatActivity {

    FirebaseUser usuario;
    ContactsViewModel contactsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        contactsViewModel = new ContactsViewModel();
        usuario = contactsViewModel.currentUser();

        CircleImageView img = findViewById(R.id.imgUser);
        Picasso.with(this).load(usuario.getPhotoUrl()).error(R.drawable.ic_avatar_placeholder).into(img);

        ImageButton imageButton = findViewById(R.id.btnback1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Perfil.this, Login.class));
                finish();
            }
        });
        TextView nome = findViewById(R.id.tvName);
        nome.setText(usuario.getDisplayName());
        TextView email = findViewById(R.id.tvEmail);
        email.setText(usuario.getEmail());


        Button logout = findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(Perfil.this, Login.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Perfil.this, Chats.class));
    }
}
