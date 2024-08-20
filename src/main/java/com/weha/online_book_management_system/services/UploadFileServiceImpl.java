package com.weha.online_book_management_system.services;

import com.weha.online_book_management_system.services.iservice.UploadFileService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            if (file != null) {
                String fileName = file.getOriginalFilename();
                if (fileName == null) throw new RuntimeException("Not found file name!");
                Files.copy(file.getInputStream(), root.resolve(fileName));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Resource load(String name) {
        try {
            Path file = root.resolve(name);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new RuntimeException("Cloud not read the file!");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(root, 1)
                    .filter(path -> !path.equals(root))
                    .map(Path::getRoot);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
