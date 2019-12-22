package br.com.frederykantunnes.projetochatcesar_androidavancado.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import javax.annotation.Nullable;

import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.ChatModel;

public class ChatsRepository {
    private MutableLiveData<List<ChatModel>> mutableLiveData;
    List<ChatModel> list;

    public LiveData<List<ChatModel>> chatsList() {
        if(mutableLiveData ==null){
            mutableLiveData = new MutableLiveData<>();
            pegar();
        }
        return mutableLiveData;
    }

    public void pegar(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore.getInstance().collection("channels").whereArrayContains("userIds", FirebaseAuth.getInstance().getCurrentUser().getUid()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    list = queryDocumentSnapshots.toObjects(ChatModel.class);
                    mutableLiveData.setValue(list);
                }else{
                }}
    });
}}
