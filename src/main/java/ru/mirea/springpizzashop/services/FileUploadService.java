package ru.mirea.springpizzashop.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadService {
    public String upload(MultipartFile image){
        String fileName = System.currentTimeMillis() + ".png";
        try {
            Path absolutePath = Paths.get("src").toAbsolutePath();
            byte[] bytes = image.getBytes();
            Path path = Paths.get(absolutePath + "/main/resources/static/img/" + fileName);
            System.out.println(path);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "/img/" + fileName; // TODO: Refact this
    }

}
