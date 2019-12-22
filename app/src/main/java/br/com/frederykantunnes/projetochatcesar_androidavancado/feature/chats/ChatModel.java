package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos.ContatoModel;

public class ChatModel implements Serializable {


    private String lastMessage, idDocument;
    private ArrayList<String> userIds;
    private ArrayList<ContatoModel> users;

    public ChatModel(){}

    public ChatModel(String lastMessage, ArrayList<String> userIds, ArrayList<ContatoModel> users, String idDocument) {
        this.lastMessage = lastMessage;
        this.userIds = userIds;
        this.users = users;
        this.idDocument = idDocument;
    }


    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public ArrayList<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(ArrayList<String> userIds) {
        this.userIds = userIds;
    }

    public ArrayList<ContatoModel> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<ContatoModel> users) {
        this.users = users;
    }

    public String getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(String idDocument) {
        this.idDocument = idDocument;
    }
}
