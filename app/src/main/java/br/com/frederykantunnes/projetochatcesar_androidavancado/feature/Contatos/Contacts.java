package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import br.com.frederykantunnes.projetochatcesar_androidavancado.R;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Messages.Messages;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.ChatModel;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.RecyclerViewOnClickListenerHack;

public class Contacts extends AppCompatActivity {

    private ArrayList<ContatoModel> list = new ArrayList<>();
    private ArrayList<ChatModel> listaChats = new ArrayList<>();
    LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ContatoAdapter mAdapter;
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioFire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        recyclerView = findViewById(R.id.recicle_list_contatos);
        layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAuth = FirebaseAuth.getInstance();
        usuarioFire = mAuth.getCurrentUser();
        mAdapter = new ContatoAdapter(this,list);
        recyclerView.setAdapter(mAdapter);
        setup();
//        setupChats();
        ImageButton imageButton = findViewById(R.id.btnback2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mAdapter.setRecyclerViewOnClickListenerHack(new RecyclerViewOnClickListenerHack(){
            @Override
            public void onClickListener(ContatoModel usuario){
                ArrayList<String> uid = new ArrayList<>();
                uid.add(usuarioFire.getUid());
                uid.add(usuario.getUid());
                ArrayList<ContatoModel> ctt = new ArrayList<>();
                ctt.add(new ContatoModel(usuario.getEmail(), usuario.getFirstName(), usuario.getPicture()+"", usuario.getUid()));
                ctt.add(new ContatoModel(usuarioFire.getEmail(), usuarioFire.getDisplayName(), usuarioFire.getPhotoUrl()+"", usuarioFire.getUid()));
//                Toast.makeText(Contacts.this, list.size(), Toast.LENGTH_LONG).show();
//                Toast.makeText(Contacts.this, listaChats.size(), Toast.LENGTH_LONG).show();
//                for(int i=0; i<listaChats.size();i++){
//                    if(listaChats.get(i).getUserIds().get(0).equalsIgnoreCase(usuario.getUid()) || listaChats.get(i).getUserIds().get(1).equalsIgnoreCase(usuario.getUid())){
//                        for (int j=0; j<list.size(); j++){
//                            if (list.get(j).getUid().equalsIgnoreCase(usuario.getUid())){
//                                Toast.makeText(Contacts.this, "Já Existe", Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    }
//                }
                ChatModel chat = new ChatModel("-", uid, ctt, usuarioFire.getUid()+usuario.getUid());
                Intent conversar = new Intent(Contacts.this, Messages.class);
                conversar.putExtra("chat", chat);
                startActivity(conversar);
                finish();
            }
        });
    }

    public void setup(){
        ContactsViewModel contactsViewModel = ViewModelProviders.of(this).get(ContactsViewModel.class);
        contactsViewModel.lista().observe(this, new Observer<List<ContatoModel>>() {
            @Override
            public void onChanged(List<ContatoModel> contatoModels) {
                list.clear();
                list.addAll(contatoModels);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

//    public void setupChats(){
//        ChatsViewModel chatsViewModel = ViewModelProviders.of(this).get(ChatsViewModel.class);
//        chatsViewModel.lista().observe(this, new Observer<List<ChatModel>>() {
//            @Override
//            public void onChanged(List<ChatModel> contatoModels) {
//                listaChats.clear();
//                listaChats.addAll(contatoModels);
//            }
//        });
//    }
}
