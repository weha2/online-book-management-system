package com.weha.online_book_management_system.services.iservice;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface UploadFileService {
    void init() throws IOException;

    void save(MultipartFile file);

    Resource load(String name);

    void deleteAll();

    Stream<Path> loadAll();
}
