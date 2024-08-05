package bg.softuni.pathfinder.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeLinkConverter {

    public static String convert(String youtubeLink) {
        Pattern pattern = Pattern.compile("http(?:s?):\\/\\/(?:www\\.)?youtu(?:be\\.com\\/watch\\?v=|\\.be\\/)([\\w\\-\\_]*)(&(amp;)?\u200C\u200B[\\w\\?\u200C\u200B=]*)?");
        Matcher matcher = pattern.matcher(youtubeLink);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }
}
