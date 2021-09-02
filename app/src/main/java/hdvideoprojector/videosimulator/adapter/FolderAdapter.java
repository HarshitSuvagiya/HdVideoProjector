package hdvideoprojector.videosimulator.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hdvideoprojector.videosimulator.R;
import hdvideoprojector.videosimulator.utils.Util;
import hdvideoprojector.videosimulator.activity.GetVidoeActivity;
import hdvideoprojector.videosimulator.model.ModelFolder;

import java.util.ArrayList;

public class FolderAdapter extends RecyclerView.Adapter<FolderAdapter.ItemviewHolder> {

    Context context;
    ArrayList<ModelFolder> arrayList;
    LayoutInflater inflater;
    static int selectpos = 0;
    Boolean isfirsttime;

    public FolderAdapter(Context context, ArrayList<ModelFolder> arrayList, Boolean isfirsttime) {
        this.context = context;
        this.arrayList = arrayList;
        this.isfirsttime = isfirsttime;
    }

    @NonNull
    @Override
    public ItemviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_folder_new, parent, false);
        ItemviewHolder itemviewHolder = new ItemviewHolder(view, arrayList);
        return itemviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemviewHolder holder, int position) {
        holder.setdata(position);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class ItemviewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ArrayList<ModelFolder> arrayList;

        TextView txt_folder;


        public ItemviewHolder(@NonNull View itemView, ArrayList<ModelFolder> arrayList) {
            super(itemView);

            this.itemView = itemView;
            this.arrayList = arrayList;

            txt_folder = itemView.findViewById(R.id.txt_folder);

        }

        public void setdata(final int position) {
            txt_folder.setText(arrayList.get(position).getFolderName());
            if (isfirsttime == true) {
                selectpos = 0;
                isfirsttime = false;
            }
            if (selectpos == position) {
                if (Util.mActionMode != null) {
                    Util.mActionMode.finish();
                }
                txt_folder.setBackgroundColor(context.getResources().getColor(R.color.selector));
                txt_folder.setTextColor(context.getResources().getColor(R.color.white));
                Log.e("AS", ">>" + arrayList.get(position).getPath());
                GetVidoeActivity.videodata(arrayList.get(position).getPath());


            } else {
                txt_folder.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
                txt_folder.setTextColor(context.getResources().getColor(R.color.selector));
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    selectpos = position;
                    notifyDataSetChanged();

                }
            });

        }
    }
}