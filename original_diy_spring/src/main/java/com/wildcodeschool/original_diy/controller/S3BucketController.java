package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.service.AmazonS3BucketService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class S3BucketController {

    private AmazonS3BucketService amazonS3BucketService;

    S3BucketController(AmazonS3BucketService amazonS3BucketService) {
        this.amazonS3BucketService = amazonS3BucketService;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        return this.amazonS3BucketService.uploadFile(file);
    }

    @PostMapping("/deleteFile")
    public String deleteFile(@RequestBody String fileURL) {
        return this.amazonS3BucketService.deleteFileFromBucket(fileURL);
    }
}