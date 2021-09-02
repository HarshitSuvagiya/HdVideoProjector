package hdvideoprojector.videosimulator.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hdvideoprojector.videosimulator.R;
import hdvideoprojector.videosimulator.adapter.DataVideoAdapter;
import hdvideoprojector.videosimulator.adapter.FolderAdapter;

import hdvideoprojector.videosimulator.model.ModelFolder;
import hdvideoprojector.videosimulator.model.Modelvideo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static hdvideoprojector.videosimulator.activity.VideoProjectionActivity.PERMISSION_READ;


public class GetVidoeActivity extends AppCompatActivity {
    int counter = 0;
    RecyclerView rv_fiolder_list;
    static RecyclerView rv_video_list;
    static Context context;
    public static int value_pass = 0;
    public static ArrayList<Modelvideo> videos;
    public static ArrayList<Modelvideo> multiselect_list;
    static DataVideoAdapter dataVideoAdapter;
    public static ArrayList<ModelFolder> arrayList;
    public static String FOLDER_NAME = "All Video";
    FolderAdapter folderAdapter;
    Boolean isfirsttime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_vidoe);

        context = GetVidoeActivity.this;
        this.counter = 0;

        bindview();
        init();
    }


    private void init() {
        arrayList = new ArrayList<>();
        ModelFolder modelFolder = new ModelFolder();
        modelFolder.setFolderName(FOLDER_NAME);

        arrayList = getPicturePaths();
        arrayList.add(modelFolder);

        Collections.sort(arrayList, new Comparator<ModelFolder>() {
            public int compare(ModelFolder obj1, ModelFolder obj2) {
                return obj1.getFolderName().compareToIgnoreCase(obj2.getFolderName());
            }
        });

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rv_fiolder_list.setLayoutManager(horizontalLayoutManagaer);
        rv_fiolder_list.setHasFixedSize(true);
        folderAdapter = new FolderAdapter(context, arrayList, isfirsttime);
        rv_fiolder_list.setAdapter(folderAdapter);
    }

    private void bindview() {

        rv_fiolder_list = findViewById(R.id.rv_fiolder_list);
        rv_video_list = findViewById(R.id.rv_video_list);
    }

    private ArrayList<ModelFolder> getPicturePaths() {
        ArrayList<ModelFolder> picFolders = new ArrayList<>();

        ArrayList<String> picPaths = new ArrayList<>();
        Uri allImagesuri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Video.Media.DATA, MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.BUCKET_DISPLAY_NAME, MediaStore.Video.Media.BUCKET_ID};
        Cursor cursor = getContentResolver().query(allImagesuri, projection, null, null, null);

        try {
            if (cursor != null) {
                cursor.moveToFirst();
            }
            do {
                ModelFolder folds = new ModelFolder();
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

                //String folderpaths =  datapath.replace(name,"");
                String folderpaths = datapath.substring(0, datapath.lastIndexOf(folder + "/"));
                folderpaths = folderpaths + folder + "/";
                if (!picPaths.contains(folderpaths)) {


                    if (!folder.equals("0")) {
                        picPaths.add(folderpaths);
                        folds.setPath(folderpaths);
                        folds.setFolderName(folder);
                        folds.setFirstPic(datapath);
                        folds.addpics();
                        picFolders.add(folds);
                    }

                } else {
                    for (int i = 0; i < picFolders.size(); i++) {
                        if (picFolders.get(i).getPath().equals(folderpaths)) {
                            picFolders.get(i).setFirstPic(datapath);
                            picFolders.get(i).addpics();
                          /*  File dir = new File(datapath);
                            File[] files = dir.listFiles();
                            int numberOfFiles = files.length;

                            picFolders.get(i).setNumberOfvideo(numberOfFiles);*/
                        }
                    }
                }
            } while (cursor.moveToNext());
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < picFolders.size(); i++) {
            Log.d("video folders", picFolders.get(i).getFolderName() + " and path = " + picFolders.get(i).getPath() + " " + picFolders.get(i).getNumberOfvideo());
        }


        return picFolders;
    }

    public static void videodata(String path) {

        videos = new ArrayList<>();
        multiselect_list = new ArrayList<>();
        Uri allVideosuri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {MediaStore.Video.Media.DATA, MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE, MediaStore.Video.Media._ID, MediaStore.Video.Media.DURATION, MediaStore.Video.Media.HEIGHT, MediaStore.Video.Media.WIDTH, MediaStore.Video.Media.RESOLUTION};
        Cursor cursor = context.getContentResolver().query(allVideosuri, projection, MediaStore.Images.Media.DATA + " like ? ", new String[]{"%" + path + "%"}, null);

        try {
            cursor.moveToFirst();
            do {
                int size = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
                int height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT));
                int width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH));
                int dur = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));


                if (size > 0 && height > 0 && width > 0 && dur > 0) {

                    Modelvideo modelvideo = new Modelvideo();
                    String pathgss = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                    modelvideo.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));
                    modelvideo.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
                    modelvideo.setDuration(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)));
                    modelvideo.setSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));
                    modelvideo.setResolution(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION)));

                    videos.add(modelvideo);
                }
            } while (cursor.moveToNext());
            cursor.close();

            ArrayList<Modelvideo> reSelection = new ArrayList<>();
            for (int i = videos.size() - 1; i > -1; i--) {
                reSelection.add(videos.get(i));
            }
            videos = reSelection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        rv_video_list.setLayoutManager(gridLayoutManager);
        rv_video_list.setHasFixedSize(true);

        dataVideoAdapter = new DataVideoAdapter(context, videos, multiselect_list, value_pass);
        rv_video_list.setAdapter(dataVideoAdapter);
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_READ: {
                if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(getApplicationContext(), "Please allow storage permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(GetVidoeActivity.this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

    }
}