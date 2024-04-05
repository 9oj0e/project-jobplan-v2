package shop.mtcoding.projectjobplan._core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImageConstants {
    private static Map<String, String>DEFAULT_IMG_FILENAME_MAP = new HashMap<>();

    static {
        Map<String, String> aMap = new HashMap<>();
        aMap.put("defaultBusiness", "default/business.png");
        aMap.put("defaultAvatar", "default/avatar.png");
        aMap.put("user1", "user1.jpg");
        aMap.put("user2", "user2.jpg");
        aMap.put("user3", "user3.jpg");
        aMap.put("user4", "user4.jpg");
        aMap.put("user5", "user5.jpg");
        aMap.put("emp1", "emp1.jpg");
        aMap.put("emp2", "emp2.jpg");
        aMap.put("emp3", "emp3.jpg");
        aMap.put("emp4", "emp4.jpg");
        aMap.put("emp5", "emp5.jpg");

        DEFAULT_IMG_FILENAME_MAP = Collections.unmodifiableMap(aMap);
    }

    public static Map<String, String> getDefaultImgFilenameMap() {
        return DEFAULT_IMG_FILENAME_MAP;
    }
}
