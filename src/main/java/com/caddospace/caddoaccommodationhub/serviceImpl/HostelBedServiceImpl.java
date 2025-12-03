package com.caddospace.caddoaccommodationhub.serviceImpl;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.HostelBedDTO;
import com.caddospace.caddoaccommodationhub.model.HostelBed;
import com.caddospace.caddoaccommodationhub.model.Room;
import com.caddospace.caddoaccommodationhub.repository.HostelBedRepository;
import com.caddospace.caddoaccommodationhub.repository.RoomRepository;
import com.caddospace.caddoaccommodationhub.service.HostelBedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class HostelBedServiceImpl implements HostelBedService {

    private final HostelBedRepository hostelBedRepository;
    private final RoomRepository roomRepository;

    public HostelBedServiceImpl(HostelBedRepository hostelBedRepository, RoomRepository roomRepository) {
        this.hostelBedRepository = hostelBedRepository;
        this.roomRepository = roomRepository;
    }

    private HostelBed convertToEntity(HostelBedDTO hostelBedDTO, HostelBed entity, Room room) {

        entity.setRoom(room);
        entity.setBedNumber(hostelBedDTO.getBedNumber());
        entity.setAvailable(hostelBedDTO.getAvailable());
        return entity;
    }

    private HostelBedDTO convertToDTO(HostelBed entity) {

        HostelBedDTO hostelBedDTO = new HostelBedDTO();
        hostelBedDTO.setBedId(entity.getBedId());
        hostelBedDTO.setRoomId(entity.getRoom().getRoomId());
        hostelBedDTO.setBedNumber(entity.getBedNumber());
        hostelBedDTO.setAvailable(entity.getAvailable());
        return hostelBedDTO;
    }

    @Override
    public ResponseEntity<APIResponse<HostelBedDTO>> saveHostelBed(HostelBedDTO dto) {

        Room existing = roomRepository.findById(dto.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found with id " + dto.getRoomId()));
        HostelBed bed = convertToEntity(dto, new HostelBed(), existing);
        HostelBed saved = hostelBedRepository.save(bed);
        HostelBedDTO responseDto = convertToDTO(saved);
        return new ResponseEntity<>(new APIResponse<>(true, 201, "Hostel bed created successfully", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<HostelBedDTO>> updateHostelBed(Long id, HostelBedDTO dto) {

        HostelBed existing = hostelBedRepository.findById(id).orElseThrow(() -> new RuntimeException("Hostel bed not found with id " + id));
        convertToEntity(dto, existing, existing.getRoom());
        HostelBed saved = hostelBedRepository.save(existing);
        HostelBedDTO responseDto = convertToDTO(saved);

        return new ResponseEntity<>(new APIResponse<>(true, 200, "Hostel bed updated successfully", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> deleteHostelBed(Long id) {

        if (hostelBedRepository.existsById(id)) {
            hostelBedRepository.deleteById(id);
            return new ResponseEntity<>(new APIResponse<>(true, 201, "Bed deleted successfully", null), HttpStatus.OK);
        }
        return null;
    }

    @Override
    public ResponseEntity<APIResponse<List<HostelBedDTO>>> bedsList() {

        List<HostelBed> hostelBeds = hostelBedRepository.findAll();
        List<HostelBedDTO> responseDto = hostelBeds.stream().map(this::convertToDTO).toList();
        return new ResponseEntity<>(new APIResponse<>(true, 201, "BED LIST FETCHED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }
}
