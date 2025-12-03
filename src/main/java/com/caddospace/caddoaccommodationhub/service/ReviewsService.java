package com.caddospace.caddoaccommodationhub.service;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.ReviewsDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReviewsService {

    ResponseEntity<APIResponse<ReviewsDTO>> saveReviews(ReviewsDTO dto);

    ResponseEntity<APIResponse<ReviewsDTO>> updateReviews(Long id, ReviewsDTO dto);

    ResponseEntity<APIResponse<String>> deleteReviews(Long id);

    ResponseEntity<APIResponse<List<ReviewsDTO>>> reviewList();
}
