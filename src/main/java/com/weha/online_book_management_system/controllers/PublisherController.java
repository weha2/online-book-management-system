package com.weha.online_book_management_system.controllers;

import com.weha.online_book_management_system.dtos.DataState;
import com.weha.online_book_management_system.dtos.publisher.PublisherRequestDTO;
import com.weha.online_book_management_system.dtos.publisher.PublisherResponseDTO;
import com.weha.online_book_management_system.services.PublisherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@Tag(name = "Publisher", description = "APIs for managing publisher")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("")
    public ResponseEntity<DataState<List<PublisherResponseDTO>>> findAllPublisher() {
        return ResponseEntity.ok(new DataState<>(publisherService.findAllPublishers()));
    }

    @GetMapping("{id}")
    public ResponseEntity<DataState<PublisherResponseDTO>> findPublisherById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new DataState<>(publisherService.findPublisherById(id)));
    }

    @PostMapping("")
    public ResponseEntity<DataState<PublisherResponseDTO>> createPublisher(@RequestBody PublisherRequestDTO req) {
        return ResponseEntity.ok(new DataState<>(publisherService.createPublisher(req)));
    }

    @PutMapping("{id}")
    public ResponseEntity<DataState<PublisherResponseDTO>> updatePublisher(
            @PathVariable Long id,
            @RequestBody PublisherRequestDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(publisherService.updatePublisher(id, req)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataState<Boolean>> deletePublisher(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new DataState<>(publisherService.deletePublisher(id)));
    }
}
