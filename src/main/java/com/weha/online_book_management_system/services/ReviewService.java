package com.weha.online_book_management_system.services;

import ch.qos.logback.core.util.StringUtil;
import com.weha.online_book_management_system.base.BaseService;
import com.weha.online_book_management_system.dtos.Review.CreateReviewDTO;
import com.weha.online_book_management_system.dtos.Review.ResponseReviewDTO;
import com.weha.online_book_management_system.entity.BookEntity;
import com.weha.online_book_management_system.entity.ReviewEntity;
import com.weha.online_book_management_system.entity.UserEntity;
import com.weha.online_book_management_system.repository.BookRepository;
import com.weha.online_book_management_system.repository.ReviewRepository;
import com.weha.online_book_management_system.utils.TokenUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService extends BaseService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewService(
            ReviewRepository reviewRepository,
            BookRepository bookRepository,
            TokenUtil tokenUtil
    ) {
        super(tokenUtil);
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    public List<ResponseReviewDTO> findByBook(Long bookId) {
        BookEntity book = new BookEntity();
        book.setId(bookId);
        Optional<List<ReviewEntity>> result = reviewRepository.findByBook(book);
        return result.map(reviewEntities -> reviewEntities
                .stream()
                .map(ResponseReviewDTO::new)
                .toList()).orElseGet(ArrayList::new);
    }

    public ResponseReviewDTO createReview(CreateReviewDTO req) throws Exception {
        validateReviewRequest(req);

        BookEntity book = new BookEntity();
        book.setId(req.bookId());

        UserEntity user = new UserEntity();
        user.setId(userId());

        ReviewEntity review = new ReviewEntity();
        review.setRating(req.rating());
        review.setComment(req.comment());
        review.setBook(book);
        review.setUser(user);

        ReviewEntity result = reviewRepository.save(review);
        return new ResponseReviewDTO(result);
    }

    public ResponseReviewDTO updateReview(Long id, CreateReviewDTO req) throws Exception {
        validateReviewRequest(req);

        Optional<ReviewEntity> data = reviewRepository.findById(id);
        if (data.isEmpty()) {
            throw new Exception("Not found review with id ".concat(String.valueOf(id)));
        }

        ReviewEntity review = data.get();
        review.setRating(req.rating());
        review.setComment(req.comment());
        review.setModifierDate(LocalDateTime.now());
        ReviewEntity result = reviewRepository.save(review);
        return new ResponseReviewDTO(result);
    }

    public boolean deleteReview(Long id) throws Exception {
        if (!reviewRepository.existsById(id)) {
            throw new Exception("Not found review with id ".concat(String.valueOf(id)));
        }
        reviewRepository.deleteById(id);
        return true;
    }

    private void validateReviewRequest(CreateReviewDTO req) throws Exception {
        if (!bookRepository.existsById(req.bookId())) {
            throw new Exception("Not found book with id ".concat(String.valueOf(req.bookId())));
        }

        if (StringUtil.isNullOrEmpty(req.comment())) {
            throw new Exception("Required Comment.");
        }
    }
}
