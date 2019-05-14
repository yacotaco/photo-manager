import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Validator
 */
public class Validator {

    public Validator() {};

    public static void validatePath(String path) {
        if(path.isEmpty()) {
          throw new IllegalArgumentException("Path cannot be empty!");
        }
      }

    public static void validateDate(String date) {
        String regex = "[0-3][0-9].[0-1][0-9].[0-9][0-9][0-9][0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        boolean matcherResult = matcher.matches();

        if(date != "") {
            if(matcherResult == false) {
                throw new IllegalArgumentException("Date in wrong format!");
            }
        }    
    }

}
