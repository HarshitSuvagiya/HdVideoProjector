package hdvideoprojector.videosimulator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import hdvideoprojector.videosimulator.R;
import hdvideoprojector.videosimulator.model.Modelvideo;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.viewHolder> {

    Context context;
    ArrayList<Modelvideo> videoArrayList;
    public OnItemClickListener onItemClickListener;

    View view;
    int pos;

    public VideoAdapter(Context context, ArrayList<Modelvideo> videoArrayList, int pos) {
        this.context = context;
        this.videoArrayList = videoArrayList;
        this.pos = pos;
    }

    @Override
    public VideoAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (pos == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.raw_video_list_top, viewGroup, false);
        } else if (pos == 2) {
            view = LayoutInflater.from(context).inflate(R.layout.raw_video_list_bottom, viewGroup, false);
        }
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VideoAdapter.viewHolder holder, final int i) {
        holder.title.setText(videoArrayList.get(i).getName());
        holder.duration.setText(videoArrayList.get(i).getDuration());
    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, duration;
        ImageView image;

        public viewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            duration = itemView.findViewById(R.id.duration);
            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition(), v);
                }
            });
        }

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, View v);
    }
}
