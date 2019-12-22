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

import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Messages.Messages;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Messages.MessagesModel;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.ChatModel;

public class MessagesRepository {
    private MutableLiveData<List<MessagesModel>> mutableLiveData=null;
    List<MessagesModel> list;

    public LiveData<List<MessagesModel>> chatsList(String idDocument) {
        if(mutableLiveData==null){
            mutableLiveData = new MutableLiveData<>();
            pegar(idDocument);
            return mutableLiveData;
        }else{
            return mutableLiveData;
        }

    }

    public void pegar(String idDocument){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore.getInstance().collection("channels").document(idDocument).collection("messages").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    list = queryDocumentSnapshots.toObjects(MessagesModel.class);
                    mutableLiveData.setValue(list);
                }else{
                }}
    });
}

    }
