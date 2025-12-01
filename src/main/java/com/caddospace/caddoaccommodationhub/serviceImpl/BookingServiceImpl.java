package com.caddospace.caddoaccommodationhub.serviceImpl;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.BookingDTO;
import com.caddospace.caddoaccommodationhub.model.Booking;
import com.caddospace.caddoaccommodationhub.model.Property;
import com.caddospace.caddoaccommodationhub.model.Room;
import com.caddospace.caddoaccommodationhub.model.Users;
import com.caddospace.caddoaccommodationhub.repository.BookingRepository;
import com.caddospace.caddoaccommodationhub.repository.PropertyRepository;
import com.caddospace.caddoaccommodationhub.repository.RoomRepository;
import com.caddospace.caddoaccommodationhub.repository.UsersRepository;
import com.caddospace.caddoaccommodationhub.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UsersRepository usersRepository;
    private final PropertyRepository propertyRepository;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, UsersRepository usersRepository, PropertyRepository propertyRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.usersRepository = usersRepository;
        this.propertyRepository = propertyRepository;
        this.roomRepository = roomRepository;
    }

    private Booking convertDtoToEntity(BookingDTO dto, Booking entity, Users user, Property property, Room room) {

        entity.setUsers(user);
        entity.setProperty(property);
        entity.setRoom(room);
        entity.setCheckInDate(dto.getCheckInDate());
        entity.setCheckOutDate(dto.getCheckOutDate());
        entity.setTotalPrice(dto.getTotalPrice());
        entity.setBookingStatus(dto.getStatus());
        return entity;
    }

    private BookingDTO convertEntityToDto(Booking entity) {

        BookingDTO dto = new BookingDTO();
        dto.setBookingId(entity.getBookingId());
        dto.setCheckInDate(entity.getCheckInDate());
        dto.setCheckOutDate(entity.getCheckOutDate());
        dto.setTotalPrice(entity.getTotalPrice());
        dto.setStatus(entity.getBookingStatus());
        dto.setUserId(entity.getUsers().getUserId());
        dto.setPropertyId(entity.getProperty().getPropertyId());
        dto.setRoomId(entity.getRoom().getRoomId());
        return dto;
    }

    @Override
    public ResponseEntity<APIResponse<BookingDTO>> saveBooking(BookingDTO dto) {

        Users existingUser = usersRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));
        Property existingProperty = propertyRepository.findById(dto.getPropertyId()).orElseThrow(() -> new RuntimeException("Property not found with id " + dto.getPropertyId()));
        Room existingRoom = roomRepository.findById(dto.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found with id " + dto.getRoomId()));

        Booking booking = convertDtoToEntity(dto, new Booking(), existingUser, existingProperty, existingRoom);
        Booking bookingSaved = bookingRepository.save(booking);
        BookingDTO responseDto = convertEntityToDto(bookingSaved);

        return new ResponseEntity<>(new APIResponse<>(true, 201, "BOOKING CREATED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<BookingDTO>> updateBooking(Long id, BookingDTO dto) {
        Booking existing = bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
        convertDtoToEntity(dto, existing, existing.getUsers(), existing.getProperty(), existing.getRoom());
        Booking update = bookingRepository.save(existing);
        BookingDTO responseDto = convertEntityToDto(update);

        return new ResponseEntity<>(new APIResponse<>(true, 200, "BOOKING UPDATED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return new ResponseEntity<>(new APIResponse<>(true, 201, "BOOKING DELETED ", null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new APIResponse<>(false, 404, "BOOKING NOT FOUND WITH ID: " + id, null), HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<APIResponse<List<BookingDTO>>> bookingList() {
        List<Booking> bookingList = bookingRepository.findAll();
        List<BookingDTO> dtoList = bookingList.stream().map(this::convertEntityToDto).toList();
        return new ResponseEntity<>(new APIResponse<>(true, 200, "BOOKING LIST FETCHED SUCCESSFULLY", dtoList), HttpStatus.OK);
    }
}
