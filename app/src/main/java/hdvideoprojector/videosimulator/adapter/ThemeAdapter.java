package hdvideoprojector.videosimulator.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import hdvideoprojector.videosimulator.R;
import hdvideoprojector.videosimulator.activity.VideoThemeProjectionActivity;

public class ThemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    LayoutInflater inflater;
    Integer[] videoTheme;


    public ThemeAdapter(Context context, Integer[] videoTheme) {
        this.context = context;
        this.videoTheme = videoTheme;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.i("Tag111", "Len : " + videoTheme.length);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.raw_video_thumb, parent, false);
        return new ItemviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemviewHolder) {
            ItemviewHolder viewHolder = (ItemviewHolder) holder;

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(context, VideoThemeProjectionActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("background", position);
                    context.startActivity(intent);
                }
            });

            Glide.with(context)
                    .load(videoTheme[position])
                    .into(viewHolder.ivThumb);
        }


    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return videoTheme.length;
    }


    public class ItemviewHolder extends RecyclerView.ViewHolder {
        public ImageView ivThumb;

        public ItemviewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumb = itemView.findViewById(R.id.ivThemeThumb);
        }
    }
 }