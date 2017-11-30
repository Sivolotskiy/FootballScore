package com.footballscore.footballscore.ui.adapters;


import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.footballscore.footballscore.R;
import com.footballscore.footballscore.interfaces.OnAdapterClickListener;
import com.footballscore.footballscore.model.Team;
import com.footballscore.footballscore.ui.views.SvgDecoder;
import com.footballscore.footballscore.ui.views.SvgDrawableTranscoder;
import com.footballscore.footballscore.ui.views.SvgSoftwareLayerSetter;

import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeasonTableAdapter extends RecyclerView.Adapter<SeasonTableAdapter.ViewHolder> {

    private List<Team> mItems;
    private OnAdapterClickListener mListener;
    private Context mContext;

    public SeasonTableAdapter(Context context) {
        this.mContext = context;
    }

    public void setItems(List<Team> mItems) {
        this.mItems = mItems;
        notifyDataSetChanged();
    }

    public void setListener(OnAdapterClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public SeasonTableAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SeasonTableAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_team, parent, false));
    }

    @Override
    public void onBindViewHolder(SeasonTableAdapter.ViewHolder holder, int position) {
        Team currentItem = mItems.get(position);
        holder.nameTextView.setText(currentItem.getTeamName());
        holder.pointsTextView.setText(String.valueOf(currentItem.getPoints()));

        holder.positionTextView.setText(String.valueOf(currentItem.getPosition()));
        holder.parentLayout.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.onClick(view, position);
            }
        });

        if (holder.winsTextView != null && holder.loseTextView != null) {
            holder.winsTextView.setText(String.valueOf(currentItem.getWins()));
            holder.loseTextView.setText(String.valueOf(currentItem.getLosses()));
        }

        if (!TextUtils.isEmpty(currentItem.getCrestURI())) {
            switch (currentItem.getTypeCrestURI()) {
                case SvgDecoder.SVG_TAG:
                    loadSVGIntoImageView(Uri.parse(currentItem.getCrestURI()), holder.imageView);
                    break;
                default:
                    Glide.with(mContext)
                            .load(currentItem.getCrestURI())
                            .into(holder.imageView);
                    break;
            }
        } else {
            Glide.with(mContext)
                    .load(R.drawable.ic_error)
                    .into(holder.imageView);
        }
    }

    private void loadSVGIntoImageView(Uri uri, ImageView imageView) {
        GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder = Glide.with(mContext)
                .using(Glide.buildStreamModelLoader(Uri.class, mContext), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .error(R.drawable.ic_error)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<>());

        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .load(uri)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_team_name_text_view)
        TextView nameTextView;
        @BindView(R.id.item_team_position_text_view)
        TextView positionTextView;
        @Nullable
        @BindView(R.id.item_team_wins_text_view)
        TextView winsTextView;
        @Nullable
        @BindView(R.id.item_team_lose_text_view)
        TextView loseTextView;
        @BindView(R.id.item_team_points_text_view)
        TextView pointsTextView;
        @BindView(R.id.item_team_parent_layout)
        RelativeLayout parentLayout;
        @BindView(R.id.item_team_image_view)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
