package com.caddospace.caddoaccommodationhub.serviceImpl;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.ReviewsDTO;
import com.caddospace.caddoaccommodationhub.model.Property;
import com.caddospace.caddoaccommodationhub.model.Reviews;
import com.caddospace.caddoaccommodationhub.model.Users;
import com.caddospace.caddoaccommodationhub.repository.PropertyRepository;
import com.caddospace.caddoaccommodationhub.repository.ReviewsRepository;
import com.caddospace.caddoaccommodationhub.repository.UsersRepository;
import com.caddospace.caddoaccommodationhub.service.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {
    private final ReviewsRepository repository;
    private final UsersRepository usersRepository;
    private final PropertyRepository propertyRepository;

    public ReviewsServiceImpl(ReviewsRepository repository, UsersRepository usersRepository, PropertyRepository propertyRepository) {
        this.repository = repository;
        this.usersRepository = usersRepository;
        this.propertyRepository = propertyRepository;
    }

    private Reviews convertToEntity(ReviewsDTO dto, Reviews entity, Users user, Property property) {

        entity.setProperty(property);
        entity.setUser(user);
        entity.setRating(dto.getRating());
        entity.setComment(dto.getComment());
        return entity;
    }

    private ReviewsDTO convertToDTO(Reviews entity) {

        ReviewsDTO dto = new ReviewsDTO();
        dto.setReviewId(entity.getReviewId());
        dto.setPropertyId(entity.getProperty().getPropertyId());
        dto.setUserId(entity.getUser().getUserId());
        dto.setRating(entity.getRating());
        dto.setComment(entity.getComment());
        return dto;
    }

    @Override
    public ResponseEntity<APIResponse<ReviewsDTO>> saveReviews(ReviewsDTO dto) {

        Property existingProperty = propertyRepository.findById(dto.getPropertyId()).orElseThrow(() -> new RuntimeException("Property not found with id " + dto.getPropertyId()));
        Users existingUsers = usersRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));

        Reviews review = convertToEntity(dto, new Reviews(), existingUsers, existingProperty);
        Reviews savedReview = repository.save(review);
        ReviewsDTO responseDto = convertToDTO(savedReview);

        return new ResponseEntity<>(new APIResponse<>(true, 201, "PAYMENT DONE SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<ReviewsDTO>> updateReviews(Long id, ReviewsDTO dto) {

        Reviews existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Review not found with id " + id));

        convertToEntity(dto, existing, existing.getUser(), existing.getProperty());
        Reviews updatedReview = repository.save(existing);
        ReviewsDTO responseDto = convertToDTO(updatedReview);

        return new ResponseEntity<>(new APIResponse<>(true, 200, "UPDATED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> deleteReviews(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return new ResponseEntity<>(new APIResponse<>(true, 201, "PAYMENT DELETED SUCCESSFULLY", null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new APIResponse<>(false, 401, "PAYMENT NOT DELETED WITH ID: " + id, null), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<APIResponse<List<ReviewsDTO>>> reviewList() {

        List<Reviews> reviews = repository.findAll();
        List<ReviewsDTO> responseDto = reviews.stream().map(this::convertToDTO).toList();
        return new ResponseEntity<>(new APIResponse<>(true, 200, "REVIEW LIST FETCHED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }
}
