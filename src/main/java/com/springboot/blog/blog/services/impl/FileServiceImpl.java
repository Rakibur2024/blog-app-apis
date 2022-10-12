package com.springboot.blog.blog.services.impl;

import com.springboot.blog.blog.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //File Name
        String name = file.getOriginalFilename();

        //random name generation
        String randomId = UUID.randomUUID().toString();
        String fineName1 = randomId.concat(name.substring(name.lastIndexOf(".")));

        //Full Path
        String filePath = path + File.separator + fineName1;



        //create folder if doesn't exist
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fineName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath = path+File.separator+fileName; // File.separator mane / bosabe
        InputStream is = new FileInputStream(fullPath);
        //db logic to return input stream
        return is;
    }

}
