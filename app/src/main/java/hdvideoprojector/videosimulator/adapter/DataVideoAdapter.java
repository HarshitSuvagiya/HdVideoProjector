package hdvideoprojector.videosimulator.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import hdvideoprojector.videosimulator.R;
import hdvideoprojector.videosimulator.utils.Util;
import hdvideoprojector.videosimulator.activity.VideoProjectionActivity;
import hdvideoprojector.videosimulator.model.Modelvideo;

import java.io.File;
import java.util.ArrayList;

public class DataVideoAdapter extends RecyclerView.Adapter<DataVideoAdapter.ItemviewHolder> {

    Context context;
    public ArrayList<Modelvideo> arrayList;
    LayoutInflater inflater;
    public ArrayList<Modelvideo> multiselect_list;
    int value_pass;

    public DataVideoAdapter(Context context, ArrayList<Modelvideo> arrayList, ArrayList<Modelvideo> multiselect_list, int value_pass) {
        this.context = context;
        this.arrayList = arrayList;
        this.multiselect_list = multiselect_list;
        this.value_pass = value_pass;
    }

    @NonNull
    @Override
    public ItemviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_video_data, parent, false);
        ItemviewHolder itemviewHolder = new ItemviewHolder(view, arrayList);
        return itemviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemviewHolder holder, int position) {
        holder.setdata(position);
        Util.getArrayList1 = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ItemviewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ArrayList<Modelvideo> arrayList;

        TextView time, video_name, nodata;
        ImageView thumb_video;
        LinearLayout lv_bg;

        public ItemviewHolder(@NonNull View itemView, ArrayList<Modelvideo> arrayList) {
            super(itemView);

            this.itemView = itemView;
            this.arrayList = arrayList;

            time = itemView.findViewById(R.id.time);
            thumb_video = itemView.findViewById(R.id.thumb_video);
            video_name = itemView.findViewById(R.id.video_name);
            lv_bg = itemView.findViewById(R.id.lv_bg);

        }

        @SuppressLint("ResourceAsColor")
        public void setdata(final int position) {
            if (value_pass == 1) {

            } else {


                if (multiselect_list.contains(arrayList.get(position))) {

                    video_name.setTextColor(ContextCompat.getColor(context, R.color.white));
                } else {

                    video_name.setTextColor(ContextCompat.getColor(context, R.color.black));
                }


                time.setText(getDuration(Integer.parseInt(arrayList.get(position).getDuration())));
                video_name.setText(arrayList.get(position).getName());
                video_name.setSelected(true);


                Glide.with(context)
                        .asBitmap()
                        .load(Uri.fromFile(new File(arrayList.get(position).getPath())))
                        .centerCrop()
                        .into(thumb_video);


                thumb_video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//                        MyApplication.getInstance().showScreenVideoActivity(context,0,position,arrayList.get(position).getPath());


                        Intent intent = new Intent(context, VideoProjectionActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("video", arrayList.get(position).getPath());
                        context.startActivity(intent);

                    }
                });
            }
        }
    }


    public String getDuration(int msec) {
        int sec = msec / 1000;
        int second = sec % 60;
        int minute = sec / 60;
        if (minute >= 60) {
            int hour = minute / 60;
            minute %= 60;
            return hour + ":" + (minute < 10 ? "0" + minute : minute) + ":" + (second < 10 ? "0" + second : second);
        }
        return minute + ":" + (second < 10 ? "0" + second : second);

    }
}