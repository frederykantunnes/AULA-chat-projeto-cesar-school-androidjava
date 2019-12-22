package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import br.com.frederykantunnes.projetochatcesar_androidavancado.Perfil;
import br.com.frederykantunnes.projetochatcesar_androidavancado.R;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos.Contacts;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos.ContatoModel;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Messages.Messages;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Messages.MessagesModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class Chats extends AppCompatActivity {
    private ArrayList<ChatModel> list = new ArrayList<>();
    LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ChatsAdapter mAdapter;
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        recyclerView = findViewById(R.id.recicle_list);
        layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();

        mAdapter = new ChatsAdapter(this,list);
        recyclerView.setAdapter(mAdapter);
        CircleImageView img = findViewById(R.id.imageProfile);
        Picasso.with(this).load(usuario.getPhotoUrl()).error(R.drawable.ic_avatar_placeholder).into(img);
        setup();

        FloatingActionButton fb = findViewById(R.id.floatingActionButton);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chats.this, Contacts.class));

//                ArrayList<String> uid = new ArrayList<>();
//                uid.add("Yf93FthpDZbrnbsIrvjnTcDHIoz1");
//                uid.add(usuario.getUid());
//                ArrayList<ContatoModel> ctt = new ArrayList<>();
//                ctt.add(new ContatoModel("fernandaferreira31121998","Fernanda Ferreira","https://lh5.googleusercontent.com/-Id1qY-tT0lI/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3rdsILsOC-VgMRVmA8Jj4brWdO9Dew/s96-c/photo.jpg"));
//                ctt.add(new ContatoModel(usuario.getEmail(), usuario.getDisplayName(), usuario.getPhotoUrl()+""));
//                ChatModel chat = new ChatModel("ultima msg foi essa", uid, ctt, "Yf93FthpDZbrnbsIrvjnTcDHIoz1"+usuario.getUid());
//                FirebaseFirestore db = FirebaseFirestore.getInstance();
//                db.collection("channels").document("Yf93FthpDZbrnbsIrvjnTcDHIoz1"+usuario.getUid()).set(chat);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Chats.this, Perfil.class));
                finish();
            }
        });

        mAdapter.setRecyclerViewOnClickListenerHackChat(new RecyclerViewOnClickListenerHackChats (){
            @Override
            public void onClickListener(ChatModel chatModel){
                Intent conversar = new Intent(Chats.this, Messages.class);
                conversar.putExtra("chat", chatModel);
                startActivity(conversar);
            }
        });

        FirebaseFirestore.getInstance().collection("channels").document();
    }
    public void setup(){
        ChatsViewModel chatsViewModel = ViewModelProviders.of(this).get(ChatsViewModel.class);
        chatsViewModel.lista().observe(this, new Observer<List<ChatModel>>() {
            @Override
            public void onChanged(List<ChatModel> contatoModels) {
                list.clear();
                list.addAll(contatoModels);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}








////                MessagesModel messagesModel = new MessagesModel("olá tudo bem",usuario.getUid(),usuario.getUid(),"asdasd","2019-01-01");//
//                ArrayList<String> uid = new ArrayList<>();
//                uid.add("jklaskljdhsadjhdkjhasd");
//                uid.add("asdsadljdhsadjhdkjhasd");
//
//                ArrayList<ContatoModel> ctt = new ArrayList<>();
//                ctt.add(new ContatoModel("sadsdfsdf","sdfsdfsdf","sdfsdfs","sdfsdfsdf"));
//                ctt.add(new ContatoModel("sadsdfsdf","sdfsdfsdf","sdfsdfs","sdfsdfsdf"));
//
//                ChatModel chat = new ChatModel("lkjaslkjd", uid, ctt);
//                FirebaseFirestore db = FirebaseFirestore.getInstance();
//                db.collection("channels").document().set(chat).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//                                Toast.makeText(Chats.this,  task.getResult()+"", Toast.LENGTH_LONG).show();
//                        } else {
//                        }
//                    }
//                });

//        MessagesModel messagesModel = new MessagesModel("ola, tudo bem?","2019-01-01","asdsadljdhsadjhdkjhasd");
//        MessagesModel messagesModel2 = new MessagesModel("tudo e ctg?","2019-01-01","jklaskljdhsadjhdkjhasd");
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("channels").document("wIPTLYuvmGZXeLhet3Uk").collection("messages").document().set(messagesModel);
//        db.collection("channels").document("wIPTLYuvmGZXeLhet3Uk").collection("messages").document().set(messagesModel2);
//
//        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        FirebaseFirestore.getInstance().collection("channels").document("wIPTLYuvmGZXeLhet3Uk").collection("messages").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//                if (!queryDocumentSnapshots.isEmpty()) {
//                    Log.e("verificar", queryDocumentSnapshots.getDocuments().toString());
//                }else{
//                }}
//        });
