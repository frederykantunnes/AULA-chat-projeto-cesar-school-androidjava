package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import br.com.frederykantunnes.projetochatcesar_androidavancado.repository.ContatosRepository;
import br.com.frederykantunnes.projetochatcesar_androidavancado.repository.UserRepository;

public class ContactsViewModel extends ViewModel {

    private final LiveData<List<ContatoModel>> liveData;
    private ContatosRepository contatosRepository;
    private UserRepository userRepository = new UserRepository();

    public ContactsViewModel(){
        contatosRepository = new ContatosRepository();
        liveData = contatosRepository.contatosList();
    }

    public LiveData<List<ContatoModel>> lista (){
        return liveData;
    }

    public FirebaseUser currentUser (){
        return userRepository.usuario();
    }
}
