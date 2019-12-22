package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.frederykantunnes.projetochatcesar_androidavancado.repository.ChatsRepository;

public class ChatsViewModel extends ViewModel {

    private final LiveData<List<ChatModel>> liveData;
    private ChatsRepository chatsRepository;

    public ChatsViewModel(){
        chatsRepository = new ChatsRepository();
        liveData = chatsRepository.chatsList();
    }

    public LiveData<List<ChatModel>> lista (){
        return liveData;
    }

}
