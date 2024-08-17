package com.weha.online_book_management_system.controllers;

import com.weha.online_book_management_system.dtos.DataState;
import com.weha.online_book_management_system.dtos.Review.ReviewRequestDTO;
import com.weha.online_book_management_system.dtos.Review.ReviewResponseDTO;
import com.weha.online_book_management_system.services.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "Review", description = "APIs for managing review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("{bookId}")
    public ResponseEntity<DataState<List<ReviewResponseDTO>>> findReviewByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(new DataState<>(reviewService.findByBook(bookId)));
    }

    @PostMapping("")
    public ResponseEntity<DataState<ReviewResponseDTO>> createReview(@RequestBody ReviewRequestDTO req) throws Exception {
        return ResponseEntity.ok(new DataState<>(reviewService.createReview(req)));
    }

    @PutMapping("{id}")
    public ResponseEntity<DataState<ReviewResponseDTO>> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewRequestDTO req
    ) throws Exception {
        return ResponseEntity.ok(new DataState<>(reviewService.updateReview(id, req)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DataState<Boolean>> deleteReview(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(new DataState<>(reviewService.deleteReview(id)));
    }

}
