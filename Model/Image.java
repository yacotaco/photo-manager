import java.util.ArrayList;
import java.util.List;

/**
 * Image model.  
 */
public class Image {

    private String path;
    private String author;
    private String location;
    private String date;
    private List<String> tagList = new ArrayList<String>();
    
    /** 
    @param path to image directory.
    @param author of image.
    @param location where image was taken.
    @param date when image was taken.
    @param tag for image(as list ex. family, vacation, landscape) 
    */
    
    public Image(String path, String author, String location, String date, String tag) {
        this.path = path;
        this.author = author;
        this.location = location;
        this.date = date;
        this.tagList.add(tag);
        validate();
    }

    public String getPath() {
        return this.path;
    } 

    public void setPath(String path) {
        this.path = path;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTag() {
        return this.tagList.toString();
    }

    public void setTag(String tag) {
        this.tagList.add(tag);
    }

    public String toString() {
        return this.path + " " + this.author + " " + this.location + " "
        + this.date + " " + this.tagList;
    }

    private void validate() {
        // check if path is not empty
        if(path.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be empty!");
        }
    }
}
