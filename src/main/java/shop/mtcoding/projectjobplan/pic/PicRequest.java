package shop.mtcoding.projectjobplan.pic;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class PicRequest {

    @Data
    public static class UploadDTO {
        private MultipartFile imgFile;
    }
}