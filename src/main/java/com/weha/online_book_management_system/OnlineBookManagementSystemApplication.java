package com.weha.online_book_management_system;

import com.weha.online_book_management_system.services.iservice.UploadFileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineBookManagementSystemApplication implements CommandLineRunner {

    private final UploadFileService uploadFileService;

    public OnlineBookManagementSystemApplication(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineBookManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        uploadFileService.init();
    }
}
