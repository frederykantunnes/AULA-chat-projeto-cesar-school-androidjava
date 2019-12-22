package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Messages;

import java.util.ArrayList;

import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos.ContatoModel;

public class MessagesModel {

    private String message, datahora;
    private String usersId;

    public MessagesModel(){

    }

    public MessagesModel(String message, String datahora, String usersId) {
        this.message = message;
        this.datahora = datahora;
        this.usersId = usersId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatahora() {
        return datahora;
    }

    public void setDatahora(String datahora) {
        this.datahora = datahora;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }
}
