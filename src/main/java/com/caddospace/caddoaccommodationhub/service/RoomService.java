package com.caddospace.caddoaccommodationhub.service;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.RoomDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {

    ResponseEntity<APIResponse<RoomDTO>> saveRoom(RoomDTO dto);

    ResponseEntity<APIResponse<RoomDTO>> updateRoom(Long id, RoomDTO dto);

    ResponseEntity<APIResponse<String>> deleteRoom(Long id);

    ResponseEntity<APIResponse<List<RoomDTO>>> roomList();
}
