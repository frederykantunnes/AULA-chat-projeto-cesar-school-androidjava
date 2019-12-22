package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.frederykantunnes.projetochatcesar_androidavancado.R;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos.ContatoModel;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Messages.Messages;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ChatModel> mUsers;
    private List<ChatModel> mUsersFiltered;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private RecyclerViewOnClickListenerHackChats mRecyclerViewOnClickListenerHackChats;

    public ChatsAdapter(Context context, ArrayList<ChatModel> users) {
        this.context = context;
        mUsers = users;
        mUsersFiltered = users;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tx_principal, tx_secondary;
        public CircleImageView img;
        public ViewHolder(final View itemView) {
            super(itemView);
            tx_principal =  itemView.findViewById(R.id.tvName);
            tx_secondary =  itemView.findViewById(R.id.tvLastMessage);
            img = itemView.findViewById(R.id.imgUser);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChatsAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vh = (ViewHolder) holder;
            ContatoModel contatoModel;

            if(mUsers.get(position).getUsers().get(0).getEmail().equalsIgnoreCase(auth.getCurrentUser().getEmail())){
                contatoModel = mUsers.get(position).getUsers().get(1);
            }else{
                contatoModel = mUsers.get(position).getUsers().get(0);
            }

            vh.tx_principal.setText(contatoModel.getFirstName());
            vh.tx_secondary.setText(mUsers.get(position).getLastMessage());
            Picasso.with(context).load(contatoModel.getPicture()).error(R.drawable.ic_avatar_placeholder).into(vh.img);


            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        mRecyclerViewOnClickListenerHackChats.onClickListener(mUsers.get(position));
                }
            });

        }else{

        }
    }


    public void setRecyclerViewOnClickListenerHackChat(RecyclerViewOnClickListenerHackChats response){
        mRecyclerViewOnClickListenerHackChats = response;
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

    public void updateList(ChatModel user) {
        insertItem(user);
    }

    private void insertItem(ChatModel contatoModel) {
        mUsers.add(contatoModel);
        notifyItemInserted(getItemCount());
    }
}
