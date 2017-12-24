package xmu.crms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Base64;

@RestController
public class UploadController {

    @Autowired
    private ResourceLoader resourceLoader;

    @Value("mp.upload.base-dir")
    private String baseDir;

    //    private static final String BASE_DIR = "/var/mywebapp/data";
    @PostMapping("/upload/avatar")
    public Object uploadAvatar(@RequestParam MultipartFile avatar) {

        String curTime = String.valueOf(System.currentTimeMillis());
        String filename = Base64.getEncoder().encodeToString(curTime.getBytes());
        File file = new File(baseDir + filename);
        try {
            avatar.transferTo(file);
            return new Object() {
                public String file = filename;
            };
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/avatar/{avatar}")
    public HttpEntity avatar(@PathVariable("avatar") String avatar) {
        File file = new File(baseDir + avatar);
        if (file.exists() == false) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                    .body(resourceLoader.getResource("file:" + baseDir + avatar));
        }
    }
}
