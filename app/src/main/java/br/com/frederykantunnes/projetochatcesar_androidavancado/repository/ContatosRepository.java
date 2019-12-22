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
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos.ContatoModel;

public class ContatosRepository {
    private MutableLiveData<List<ContatoModel>> mutableLiveData;
    List<ContatoModel> list;

    public LiveData<List<ContatoModel>> contatosList() {
        if(mutableLiveData ==null){
            mutableLiveData = new MutableLiveData<>();
            pegar();
        }
        return mutableLiveData;
    }

    public void pegar(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore.getInstance().collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    list = queryDocumentSnapshots.toObjects(ContatoModel.class);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getEmail().equals(user.getEmail())){
                            list.remove(list.get(i));
                        }
                    }
                    mutableLiveData.setValue(list);
                }else{
                }}
    });
}

    }
