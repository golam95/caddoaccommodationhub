package com.caddospace.caddoaccommodationhub.serviceImpl;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.RoomDTO;
import com.caddospace.caddoaccommodationhub.model.Property;
import com.caddospace.caddoaccommodationhub.model.Room;
import com.caddospace.caddoaccommodationhub.repository.PropertyRepository;
import com.caddospace.caddoaccommodationhub.repository.RoomRepository;
import com.caddospace.caddoaccommodationhub.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final PropertyRepository propertyRepository;

    public RoomServiceImpl(RoomRepository roomRepository, PropertyRepository propertyRepository) {
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
    }

    private Room convertDtoToEntity(RoomDTO dto, Room entity, Property property) {
        entity.setProperty(property);
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setType(dto.getType());
        entity.setCapacity(dto.getCapacity());
        entity.setPricePerNight(dto.getPricePerNight());
        entity.setDescription(dto.getDescription());
        entity.setAmenities(dto.getAmenities());
        return entity;

    }

    private RoomDTO convertEntityToDTO(Room entity) {
        RoomDTO dto = new RoomDTO();
        dto.setRoomId(entity.getRoomId());
        dto.setPropertyId(entity.getProperty().getPropertyId());
        dto.setRoomNumber(entity.getRoomNumber());
        dto.setType(entity.getType());
        dto.setCapacity(entity.getCapacity());
        dto.setPricePerNight(entity.getPricePerNight());
        dto.setDescription(entity.getDescription());
        dto.setAmenities(entity.getAmenities());
        return dto;
    }

    @Override
    public ResponseEntity<APIResponse<RoomDTO>> saveRoom(RoomDTO dto) {

        Property existing = propertyRepository.findById(dto.getPropertyId()).orElseThrow(() -> new RuntimeException("Property Not Found with ID: " + dto.getPropertyId()));
        Room room = convertDtoToEntity(dto, new Room(), existing);
        Room savedRoom = roomRepository.save(room);
        RoomDTO responseDto = convertEntityToDTO(savedRoom);

        return new ResponseEntity<>(new APIResponse<>(true, 201, "ROOM CREATED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<RoomDTO>> updateRoom(Long id, RoomDTO dto) {

        Room existing = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room Not Found with ID: " + id));
        Property existingProperty = propertyRepository.findById(dto.getPropertyId()).orElseThrow(() -> new RuntimeException("Property Not Found with ID: " + dto.getPropertyId()));
        convertDtoToEntity(dto, existing, existingProperty);
        Room updatedRoom = roomRepository.save(existing);
        RoomDTO responseDto = convertEntityToDTO(updatedRoom);

        return new ResponseEntity<>(new APIResponse<>(true, 200, "ROOM UPDATED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            new ResponseEntity<>(new APIResponse<>(true, 200, "ROOM DELETED SUCCESSFULLY", HttpStatus.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(new APIResponse<>(false, 400, "Room Not Found with ID: " + id, null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<List<RoomDTO>>> roomList() {
        List<Room> roomList = roomRepository.findAll();
        List<RoomDTO> dtoList = roomList.stream().map(this::convertEntityToDTO).toList();
        return new ResponseEntity<>(new APIResponse<>(true, 201, "ALL ROOM FETCHED SUCCESSFULLY", dtoList), HttpStatus.OK);
    }
}
