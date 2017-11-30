package com.footballscore.footballscore.ui.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.footballscore.footballscore.R;
import com.footballscore.footballscore.interfaces.OnAdapterClickListener;
import com.footballscore.footballscore.model.Player;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private List<Player> mItems;

    public PlayerAdapter() {
    }

    public void setItems(List<Player> mItems) {
        this.mItems = mItems;
        notifyDataSetChanged();
    }

    @Override
    public PlayerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlayerAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_player, parent, false));
    }

    @Override
    public void onBindViewHolder(PlayerAdapter.ViewHolder holder, int position) {
        Player currentItem = mItems.get(position);
        holder.nameTextView.setText(currentItem.getName());
        holder.jerseyName.setText(String.valueOf(currentItem.getJerseyNumber()));
        holder.nationality.setText(currentItem.getNationality());
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_player_name_text_view)
        TextView nameTextView;
        @BindView(R.id.item_player_parent_layout)
        LinearLayout mParentLayout;
        @BindView(R.id.item_player_jersey_text)
        TextView jerseyName;
        @BindView(R.id.item_player_nationality_text_view)
        TextView nationality;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
