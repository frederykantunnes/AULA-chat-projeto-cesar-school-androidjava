package br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Contatos;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.frederykantunnes.projetochatcesar_androidavancado.R;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.Messages.Messages;
import br.com.frederykantunnes.projetochatcesar_androidavancado.feature.chats.RecyclerViewOnClickListenerHack;
import de.hdodenhof.circleimageview.CircleImageView;

public class ContatoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ContatoModel> mUsers;
    private List<ContatoModel> mUsersFiltered;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public ContatoAdapter(Context context, ArrayList<ContatoModel> users) {
        this.context = context;
        mUsers = users;
        mUsersFiltered = users;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tx_principal, tx_secondary;
        public CircleImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            tx_principal =  itemView.findViewById(R.id.tvName);
            tx_secondary =  itemView.findViewById(R.id.tvLastMessage);
            img = itemView.findViewById(R.id.imgUser);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContatoAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_contact, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        if (holder instanceof ViewHolder) {

                ViewHolder vh = (ViewHolder) holder;
                vh.tx_principal.setText(mUsers.get(position).getFirstName());
                vh.tx_secondary.setText(mUsers.get(position).getEmail());
                Picasso.with(context).load(mUsers.get(position).getPicture()).error(R.drawable.ic_avatar_placeholder).into(vh.img);

                vh.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mRecyclerViewOnClickListenerHack.onClickListener(mUsers.get(position));
                    }
                });
        }else{
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

    public void updateList(ContatoModel user) {
        insertItem(user);
    }

    private void insertItem(ContatoModel contatoModel) {
        mUsers.add(contatoModel);
        notifyItemInserted(getItemCount());
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack response){
        mRecyclerViewOnClickListenerHack = response;
    }
}
