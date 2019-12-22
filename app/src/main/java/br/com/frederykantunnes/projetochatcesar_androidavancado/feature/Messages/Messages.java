package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Messages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.frederykantunnes.projetochatcesar_androidavancado.Perfil;
import br.com.frederykantunnes.projetochatcesar_androidavancado.R;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos.Contacts;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos.ContatoModel;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.ChatModel;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.Chats;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.ChatsAdapter;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.ChatsViewModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class Messages extends AppCompatActivity {

    private ArrayList<MessagesModel> list = new ArrayList<>();
    LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private MessagesAdapter mAdapter;
    private FirebaseAuth mAuth;
    private FirebaseUser usuario;
    public static String idDocument;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_privado);
        final ChatModel chatModel = (ChatModel) getIntent().getSerializableExtra("chat");
        usuario = FirebaseAuth.getInstance().getCurrentUser();
        CircleImageView img = findViewById(R.id.img_chat_pv);
        TextView texto = findViewById(R.id.textView3);
        TextView email = findViewById(R.id.textView4);

        if (chatModel.getUsers().get(0).getEmail().equalsIgnoreCase(usuario.getEmail())) {
            texto.setText(chatModel.getUsers().get(1).getFirstName());
            email.setText(chatModel.getUsers().get(1).getEmail());
            Picasso.with(this).load(chatModel.getUsers().get(1).getPicture()).error(R.drawable.ic_avatar_placeholder).into(img);
        } else {
            texto.setText(chatModel.getUsers().get(0).getFirstName());
            email.setText(chatModel.getUsers().get(0).getEmail());
            Picasso.with(this).load(chatModel.getUsers().get(0).getPicture()).error(R.drawable.ic_avatar_placeholder).into(img);
        }

        ImageButton imageButton = findViewById(R.id.btnback);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recicle_list_messages);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        mAuth = FirebaseAuth.getInstance();
        usuario = mAuth.getCurrentUser();
        mAdapter = new MessagesAdapter(this, list);
        recyclerView.setAdapter(mAdapter);
        setup(chatModel.getIdDocument());

        final EditText message = findViewById(R.id.txt_new_message);
        ImageButton send = findViewById(R.id.btn_send_new_message);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDateTime agora = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:m, dd 'de' MMM");
                if (!message.getText().toString().equalsIgnoreCase("")) {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    MessagesModel messagesModel1 = new MessagesModel(message.getText().toString(), agora.format(formatter), usuario.getUid());
                    db.collection("channels").document(chatModel.getIdDocument()).collection("messages").document().set(messagesModel1);
                    chatModel.setLastMessage(message.getText().toString());
                    db.collection("channels").document(chatModel.getIdDocument()).set(chatModel);
                    message.setText("");
                }
            }
        });



    }

    public void setup(String idDoc) {
        idDocument = idDoc;
        final MessagesViewModel chatsViewModel = ViewModelProviders.of(this).get(MessagesViewModel.class);
        chatsViewModel.lista().observe(this, new Observer<List<MessagesModel>>() {
            @Override
            public void onChanged(List<MessagesModel> contatoModels) {
                list.clear();
                list.addAll(contatoModels);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}