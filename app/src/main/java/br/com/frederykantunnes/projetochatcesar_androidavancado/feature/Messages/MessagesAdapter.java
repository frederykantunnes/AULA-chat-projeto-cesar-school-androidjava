package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Messages;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.frederykantunnes.projetochatcesar_androidavancado.R;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos.ContatoModel;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.ChatModel;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<MessagesModel> mUsers;
    private List<MessagesModel> mUsersFiltered;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private int SEND_MESSAGE=0, RECEIVE_MESSAGE=1;
    private int retorno;

    public MessagesAdapter(Context context, ArrayList<MessagesModel> users) {
        this.context = context;
        mUsers = users;
        mUsersFiltered = users;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tx_principal, tx_secondary;
        public ViewHolder(View itemView) {
            super(itemView);
            tx_principal =  itemView.findViewById(R.id.mensagem);
            tx_secondary =  itemView.findViewById(R.id.hora);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    context.startActivity(new Intent(context, Messages.class));
//                }
//            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int idLayoutItemMessage = 0;
        LayoutInflater inflater = null;

        if(retorno==SEND_MESSAGE){
            idLayoutItemMessage = R.layout.row_message_send;
            inflater = LayoutInflater.from(context);
        }else if(retorno==RECEIVE_MESSAGE){
            idLayoutItemMessage = R.layout.row_message_received;
            inflater = LayoutInflater.from(context);
        }

        View view = inflater.inflate(idLayoutItemMessage, parent, false);
        MessagesAdapter.ViewHolder viewHolder = new MessagesAdapter.ViewHolder(view);

return  viewHolder;
//        return new MessagesAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
//                .inflate(idLayoutItemMessage, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vh = (ViewHolder) holder;
            vh.tx_principal.setText(mUsers.get(position).getMessage());
            vh.tx_secondary.setText(mUsers.get(position).getDatahora());
        }
    }

    private void removerItem(int position) {
        mUsers.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mUsers.size());
    }

    @Override
    public int getItemCount() {
        return mUsers != null ? mUsers.size() : 0;
    }

    public void updateList(MessagesModel user) {
        insertItem(user);
    }

    private void insertItem(MessagesModel contatoModel) {
        mUsers.add(contatoModel);
        notifyItemInserted(getItemCount());
    }

    @Override
    public int getItemViewType(int position) {
        MessagesModel messagesModel = mUsers.get(position);
        if(messagesModel.getUsersId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
            retorno =  SEND_MESSAGE;
        }else {
            retorno = RECEIVE_MESSAGE;
        }
        return 0;
    }
}
