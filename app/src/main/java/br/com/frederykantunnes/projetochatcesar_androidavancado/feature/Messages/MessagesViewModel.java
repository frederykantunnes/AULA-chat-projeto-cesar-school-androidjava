package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Messages;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.ChatModel;
import br.com.frederykantunnes.projetochatcesar_androidavancado.repository.ChatsRepository;
import br.com.frederykantunnes.projetochatcesar_androidavancado.repository.MessagesRepository;

public class MessagesViewModel extends ViewModel {

    private final LiveData<List<MessagesModel>> liveData;
    private MessagesRepository chatsRepository;

    public MessagesViewModel(){
        chatsRepository = new MessagesRepository();
        liveData = chatsRepository.chatsList(Messages.idDocument);
    }

    public LiveData<List<MessagesModel>> lista (){
        return liveData;
    }

}
