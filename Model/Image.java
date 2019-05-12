import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * Image model.  
 */
public class Image implements Comparator<Image> {

    private String imageName;
    private String path;
    private String author;
    private String location;
    private String date;
    private List<String> tagList = new ArrayList<String>();
    
    /**
    @param imageName name of image. 
    @param path to image directory.
    @param author of image.
    @param location where image was taken.
    @param date when image was taken.
    @param tagList stores image tags.
    */
    
    public Image(String imageName, String path, String author, String location, String date, List<String> tagList) {
        this.imageName = imageName;
        this.path = path;
        this.author = author;
        this.location = location;
        this.date = date;
        this.tagList = tagList;
        validate();
    }

    public String getImageName() {
        return this.imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public List<String> getTag() {
        return this.tagList;
    }

    public void setTag(List<String> tagList) {
        this.tagList = tagList;
    }

    public String toString() {
        return this.path + ";" + this.author + ";" + this.location + ";"
        + this.date + ";" + parseTagList(this.tagList);
    }

    public String parseTagList(List<String> tagList){
        Stream<String> stream = tagList.stream();
        return stream.collect(Collectors.joining(", "));     
    }

    private void validate() {
        // check if path is not empty
        if(path.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be empty!");
        }
    }

    public static Comparator<Image> authorComparator = new Comparator<Image>() {         

        @Override         
    
        public int compare(Image image1, Image image2) {             
    
          return (int) (image1.getAuthor().compareTo(image2.getAuthor()));         
    
        }     
    
    };
    
    public static Comparator<Image> dateComparator = new Comparator<Image>() {         

        @Override         
    
        public int compare(Image image1, Image image2) {             
    
          return (int) (image1.getDate().compareTo(image2.getDate()));         
    
        }     
    
    };      

    public static Comparator<Image> locationComparator = new Comparator<Image>() {         

        @Override         
    
        public int compare(Image image1, Image image2) {             
    
          return (int) (image1.getLocation().compareTo(image2.getLocation()));         
    
        }     
    
    };
}
