package com.caddospace.caddoaccommodationhub.controller;

import com.caddospace.caddoaccommodationhub.constant.APIRoute;
import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.ReviewsDTO;
import com.caddospace.caddoaccommodationhub.service.ReviewsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @PostMapping(APIRoute.REVIEW_SAVE)
    public ResponseEntity<APIResponse<ReviewsDTO>> save(@Valid @RequestBody ReviewsDTO dto) {
        return reviewsService.saveReviews(dto);
    }

    @PutMapping(APIRoute.REVIEW_UPDATE)
    public ResponseEntity<APIResponse<ReviewsDTO>> update(@PathVariable Long id, @Valid @RequestBody ReviewsDTO dto) {
        return reviewsService.updateReviews(id, dto);
    }

    @DeleteMapping(APIRoute.REVIEW_DELETE)
    public ResponseEntity<APIResponse<String>> delete(@PathVariable Long id) {
        return reviewsService.deleteReviews(id);
    }

    @GetMapping(APIRoute.REVIEW_LIST)
    public ResponseEntity<APIResponse<List<ReviewsDTO>>> reviewList() {
        return reviewsService.reviewList();
    }
}
