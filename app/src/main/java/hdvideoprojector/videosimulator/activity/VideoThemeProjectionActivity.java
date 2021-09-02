package hdvideoprojector.videosimulator.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import hdvideoprojector.videosimulator.adapter.VideoAdapter;
import hdvideoprojector.videosimulator.R;
import hdvideoprojector.videosimulator.utils.Util;
import hdvideoprojector.videosimulator.model.ModelFolder;
import hdvideoprojector.videosimulator.model.Modelvideo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static hdvideoprojector.videosimulator.activity.GetVidoeActivity.FOLDER_NAME;


public class VideoThemeProjectionActivity extends Activity implements View.OnClickListener {
    ArrayList<String> allData = new ArrayList<>();
    AnimationDrawable b;
    VideoView videoView;
    ImageView prev, next, pause;
    SeekBar seekBar;
    int video_index = 0;
    double current_pos, total_duration;
    TextView current, total;
    LinearLayout showProgress;
    Handler mHandler, handler;
    boolean isVisible = true;
    RelativeLayout relativeLayout;
    public static final int PERMISSION_READ = 0;
    public ArrayList<Integer> imageList;
    private ImageView ivLightA, ivLightB;
    LinearLayout ivNext;
    private LinearLayout ivPrevious;
    private ImageView ivScreen;
    private LinearLayout ll1;
    private LinearLayout ll2;
    private LinearLayout llVideo1;
    private LinearLayout llVideo2;

    static Context context;

    private int position;
    RelativeLayout rlMainPlayer;

    private VideoView vdImage;
    public static ArrayList<Modelvideo> videos;
    //    public static ArrayList<Modelvideo> multiselect_list;
    public static ArrayList<Modelvideo> videoArrayList;

    int id;
    LinearLayout ll_back;
    //    String video;
    int positionID;
    public static int value_pass = 0;
    //    public static ActionMode mActionMode;
//    public static ArrayList<ModelFolder> arrayList1;
//    public static ArrayList<Modelvideo> getArrayList1;
    ArrayList<Modelvideo> modelvideos = new ArrayList<>();

    public RecyclerView rvAllVideoListBottom, rvAllVideoListTop;


    VideoAdapter adapterTop, adapterBottom;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_video_projection);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("background", 0);
        positionID = extras.getInt("position", 0);
        position = getIntent().getIntExtra("background", 0);

        bindview();
        init();
        addListener();
        setList();
        setVideo();
        getVideos();
        ivScreen.setImageResource(imageList.get(id).intValue());

//        videoplay(video);
//        playVideo(video);
    }

    public void bindview() {
        this.ll1 = findViewById(R.id.ll1);
        this.ll2 = findViewById(R.id.ll2);
        this.llVideo1 = findViewById(R.id.llVideo1);
        this.llVideo2 = findViewById(R.id.llVideo2);
        this.ivScreen = findViewById(R.id.ivScreen);
        this.ivLightA = findViewById(R.id.ivLightA);
        this.ivLightB = findViewById(R.id.ivLightB);
        this.ivLightB = findViewById(R.id.ivLightB);
        this.ivPrevious = findViewById(R.id.ivPrevious);
        this.ivNext = findViewById(R.id.ivNext);
        this.vdImage = findViewById(R.id.vdImage);
        ll_back = findViewById(R.id.ll_back);
        rvAllVideoListTop = findViewById(R.id.rvAllVideoListTop);
        rvAllVideoListBottom = findViewById(R.id.rvAllVideoListBottom);
        rlMainPlayer = findViewById(R.id.rlMainPlayer);
    }

    public void init() {
        this.ivLightB.setBackgroundResource(R.drawable.movie_light);
        this.b = (AnimationDrawable) this.ivLightB.getBackground();


        ModelFolder modelFolder = new ModelFolder();
        modelFolder.setFolderName(FOLDER_NAME);

        Util.arrayList1 = getPicturePaths();
        Util.arrayList1.add(modelFolder);

        videoArrayList = new ArrayList<>();
        Util.getArrayList1 = new ArrayList<>();

        Collections.sort(Util.arrayList1, new Comparator<ModelFolder>() {
            public int compare(ModelFolder obj1, ModelFolder obj2) {
                return obj1.getFolderName().compareToIgnoreCase(obj2.getFolderName());
            }
        });
    }

    public void addListener() {
        this.ivPrevious.setOnClickListener(this);
        this.ivNext.setOnClickListener(this);
        ll_back.setOnClickListener(this);
    }

    private void setList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        imageList = arrayList;
        arrayList.add(Integer.valueOf(R.drawable.image_01));
        imageList.add(Integer.valueOf(R.drawable.image_02));
        imageList.add(Integer.valueOf(R.drawable.image_03));
        imageList.add(Integer.valueOf(R.drawable.image_04));
        imageList.add(Integer.valueOf(R.drawable.image_05));
        imageList.add(Integer.valueOf(R.drawable.image_06));
        imageList.add(Integer.valueOf(R.drawable.image_07));
        imageList.add(Integer.valueOf(R.drawable.image_08));
        imageList.add(Integer.valueOf(R.drawable.image_09));
        imageList.add(Integer.valueOf(R.drawable.image_10));
        imageList.add(Integer.valueOf(R.drawable.image_11));
        imageList.add(Integer.valueOf(R.drawable.image_12));
        imageList.add(Integer.valueOf(R.drawable.image_13));
        imageList.add(Integer.valueOf(R.drawable.image_14));
        imageList.add(Integer.valueOf(R.drawable.image_15));
        imageList.add(Integer.valueOf(R.drawable.image_16));
        imageList.add(Integer.valueOf(R.drawable.image_17));
        imageList.add(Integer.valueOf(R.drawable.image_18));
        imageList.add(Integer.valueOf(R.drawable.image_19));
        imageList.add(Integer.valueOf(R.drawable.image_20));
        imageList.add(Integer.valueOf(R.drawable.image_21));
    }


    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                onBackPressed();
                return;

            case R.id.ivNext:
                if (this.id < this.imageList.size() - 1) {
                    ImageView imageView = this.ivScreen;
                    ArrayList<Integer> arrayList = this.imageList;
                    int i = this.id + 1;
                    this.id = i;
                    imageView.setImageResource(arrayList.get(i));
                    return;
                }
                return;
            case R.id.ivPrevious:
                int i2 = this.id;
                if (i2 > 0) {
                    ImageView imageView2 = this.ivScreen;
                    ArrayList<Integer> arrayList2 = this.imageList;
                    int i3 = i2 - 1;
                    this.id = i3;
                    imageView2.setImageResource(arrayList2.get(i3));
                    return;
                }
                return;

            default:
                return;
        }
    }

    public void setVideo() {
        videoView = findViewById(R.id.videoview);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        pause = findViewById(R.id.pause);
        seekBar = findViewById(R.id.seekbar);
        current = findViewById(R.id.current);
        total = findViewById(R.id.total);
        showProgress = findViewById(R.id.showProgress);
        relativeLayout = findViewById(R.id.relative);


        mHandler = new Handler();
        handler = new Handler();


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                setVideoProgress();
            }
        });

        prevVideo();
        nextVideo();
        setPause();
        hideLayout();
    }

    public void videoplay(String s) {
        vdImage.setVideoPath(s);
        vdImage.start();
        // vdImage.setVideoURI(Uri.parse(s));
    }

    // play video file
    public void playVideo(String video) {
        try {
            videoView.setVideoPath(video);
            videoView.start();
            //videoView.setVideoURI(Uri.parse(video));

            pause.setImageResource(R.drawable.ic_pause_circle_filled_black);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // display video progress
    public void setVideoProgress() {
        //get the video duration
        current_pos = videoView.getCurrentPosition();
        total_duration = videoView.getDuration();

        //display video duration
        total.setText(timeConversion((long) total_duration));
        current.setText(timeConversion((long) current_pos));
        seekBar.setMax((int) total_duration);
        final Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    current_pos = videoView.getCurrentPosition();
                    current.setText(timeConversion((long) current_pos));
                    seekBar.setProgress((int) current_pos);
                    handler.postDelayed(this, 1000);
                } catch (IllegalStateException ed) {
                    ed.printStackTrace();
                }
            }
        };

        handler.postDelayed(runnable, 1000);

        //seekbar change listner
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                current_pos = seekBar.getProgress();
                videoView.seekTo((int) current_pos);
                vdImage.seekTo((int) current_pos);
            }
        });
    }

    public void prevVideo() {
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (positionID > -1) {
                    positionID--;
                    if (positionID == -1) {
                        positionID = 0;
                    }
                    String s = Util.getArrayList1.get(positionID).getPath();
                    Log.e("s::", "" + s);

                    playVideo(s);
                    videoplay(s);
                } else {
                    positionID = 0;
                    String s1 = Util.getArrayList1.get(positionID).getName();
                    Log.e("s1::", "" + s1);
                    playVideo(s1);
                    videoplay(s1);
                }
            }
        });
    }

    public void nextVideo() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (positionID > -1) {
                    positionID++;
                    if (positionID == Util.getArrayList1.size()) {
                        positionID = Util.getArrayList1.size() - 1;
                    }
                    String s1 = Util.getArrayList1.get(positionID).getPath();
                    Log.e("s1::", "" + positionID);
                    playVideo(s1);
                    videoplay(s1);
                } else {
                    positionID = 0;
                    String s1 = Util.getArrayList1.get(positionID).getPath();
                    Log.e("s1::", "" + positionID);
                    playVideo(s1);
                    videoplay(s1);
                }
            }
        });
    }

    public void setPause() {
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                    vdImage.pause();
                    pause.setImageResource(R.drawable.ic_play_circle_filled_black);
                } else {
                    videoView.start();
                    vdImage.start();
                    pause.setImageResource(R.drawable.ic_pause_circle_filled_black);
                }
            }
        });
    }

    //time conversion
    public String timeConversion(long value) {
        String songTime;
        int dur = (int) value;
        int hrs = (dur / 3600000);
        int mns = (dur / 60000) % 60000;
        int scs = dur % 60000 / 1000;

        if (hrs > 0) {
            songTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
        } else {
            songTime = String.format("%02d:%02d", mns, scs);
        }
        return songTime;
    }

    // hide progress when the video is playing
    public void hideLayout() {

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                showProgress.setVisibility(View.GONE);
                isVisible = false;
            }
        };
        handler.postDelayed(runnable, 5000);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.removeCallbacks(runnable);
                if (isVisible) {
                    showProgress.setVisibility(View.GONE);
                    isVisible = false;
                } else {
                    showProgress.setVisibility(View.VISIBLE);
                    mHandler.postDelayed(runnable, 5000);
                    isVisible = true;
                }
            }
        });

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


//    public static void videodata(String path) {
//
//        videos = new ArrayList<>();
//        multiselect_list = new ArrayList<>();
//        videoArrayList = new ArrayList<>();
//
//        Uri allVideosuri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//        String[] projection = {MediaStore.Video.Media.DATA, MediaStore.Video.Media.DISPLAY_NAME,
//                MediaStore.Video.Media.SIZE, MediaStore.Video.Media._ID, MediaStore.Video.Media.DURATION, MediaStore.Video.Media.HEIGHT, MediaStore.Video.Media.WIDTH, MediaStore.Video.Media.RESOLUTION};
//        Cursor cursor = context.getContentResolver().query(allVideosuri, projection, MediaStore.Images.Media.DATA + " like ? ", new String[]{"%" + path + "%"}, null);
//
//        ContentResolver cr = context.getContentResolver();
//        try {
//            cursor.moveToFirst();
//            do {
//                int size = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE));
//                int height = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT));
//                int width = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH));
//                int dur = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
//
//
//                if (size > 0 && height > 0 && width > 0 && dur > 0) {
//
//                    Modelvideo modelvideo = new Modelvideo();
//                    String pathgss = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
//                    modelvideo.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));
//                    modelvideo.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));
//                    modelvideo.setDuration(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)));
//                    modelvideo.setSize(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));
//                    modelvideo.setResolution(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION)));
//
////                Bitmap b = MediaStore.Video.Thumbnails.getThumbnail(cr, id, MediaStore.Video.Thumbnails.MINI_KIND, null);
////                Bitmap bm = new LoadVideoThumbnail().execute(pathgss)..get();
//
////                Bitmap bMap = ThumbnailUtils.createVideoThumbnail(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)), MediaStore.Video.Thumbnails.MICRO_KIND);
////                modelvideo.setVideothumb(b);
//
//                    videos.add(modelvideo);
//                }
//            } while (cursor.moveToNext());
//            cursor.close();
//
//            ArrayList<Modelvideo> reSelection = new ArrayList<>();
//            for (int i = videos.size() - 1; i > -1; i--) {
//                reSelection.add(videos.get(i));
//            }
//            videos = reSelection;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public int scroll_offset;

    ///////////////////////////////////////////////////////////////
    //get video files from storage
    public void getVideos() {
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        //looping through all rows and adding to list
        if (cursor != null && cursor.moveToFirst()) {
            do {

                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                Modelvideo videoModel = new Modelvideo();
                videoModel.setName(title);
                videoModel.setPath(data);
                videoModel.setDuration(timeConversion(Long.parseLong(duration)));
                videoArrayList.add(videoModel);
                Util.getArrayList1.add(videoModel);
            } while (cursor.moveToNext());
        }

        rvAllVideoListTop.setLayoutManager(new LinearLayoutManager(VideoThemeProjectionActivity.this, LinearLayoutManager.VERTICAL, false));
        rvAllVideoListTop.setItemAnimator(new DefaultItemAnimator());
        adapterTop = new VideoAdapter(VideoThemeProjectionActivity.this, videoArrayList, 1);
        rvAllVideoListTop.setAdapter(adapterTop);

        rvAllVideoListBottom.setLayoutManager(new LinearLayoutManager(VideoThemeProjectionActivity.this, LinearLayoutManager.VERTICAL, false));
        rvAllVideoListBottom.setItemAnimator(new DefaultItemAnimator());
        adapterBottom = new VideoAdapter(VideoThemeProjectionActivity.this, videoArrayList, 2);
        rvAllVideoListBottom.setAdapter(adapterBottom);

        final RecyclerView.OnScrollListener[] scrollListeners = new RecyclerView.OnScrollListener[2];
        scrollListeners[0] = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                rvAllVideoListTop.removeOnScrollListener(scrollListeners[1]);
                rvAllVideoListTop.scrollBy(dx, dy);
                rvAllVideoListTop.addOnScrollListener(scrollListeners[1]);
            }
        };

        scrollListeners[1] = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                rvAllVideoListBottom.removeOnScrollListener(scrollListeners[0]);
                rvAllVideoListBottom.scrollBy(dx, dy);
                rvAllVideoListBottom.addOnScrollListener(scrollListeners[0]);
            }
        };
        rvAllVideoListBottom.addOnScrollListener(scrollListeners[0]);
        rvAllVideoListTop.addOnScrollListener(scrollListeners[1]);


        adapterTop.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, View v) {
//                rvAllVideoListBottom.setVisibility(View.GONE);
//                rvAllVideoListTop.setVisibility(View.GONE);
//                rlMainPlayer.setVisibility(View.VISIBLE);
//
//                playVideo(videoArrayList.get(pos).getPath());
//                videoplay(videoArrayList.get(pos).getPath());
            }
        });

        adapterBottom.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, View v) {
                rvAllVideoListBottom.setVisibility(View.GONE);
                rvAllVideoListTop.setVisibility(View.GONE);
                rlMainPlayer.setVisibility(View.VISIBLE);

                playVideo(videoArrayList.get(pos).getPath());
                videoplay(videoArrayList.get(pos).getPath());
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (videoView.isPlaying()) {
            videoView.stopPlayback();
            vdImage.stopPlayback();

            rlMainPlayer.setVisibility(View.GONE);
            rvAllVideoListBottom.setVisibility(View.VISIBLE);
            rvAllVideoListTop.setVisibility(View.VISIBLE);
        } else {
            startActivity(new Intent(VideoThemeProjectionActivity.this, VideoThemeActivity.class));
        }
    }

}