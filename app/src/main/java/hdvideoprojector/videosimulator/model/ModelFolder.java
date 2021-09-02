package hdvideoprojector.videosimulator.model;

public class ModelFolder {
    private String path = "/storage/emulated/0/";
    private String FolderName;
    private int numberOfvideo = 0;
    private String firstPic;

    public ModelFolder() {

    }

    public ModelFolder(String path, String folderName) {
        this.path = path;
        FolderName = folderName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFolderName() {
        return FolderName;
    }

    public void setFolderName(String folderName) {
        FolderName = folderName;
    }

    public int getNumberOfvideo() {
        return numberOfvideo;
    }

    public void setNumberOfvideo(int numberOfPics) {
        this.numberOfvideo = numberOfPics;
    }

    public void addpics() {
        this.numberOfvideo++;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }
}
