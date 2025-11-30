package com.caddospace.caddoaccommodationhub.controller;

import com.caddospace.caddoaccommodationhub.constant.APIRoute;
import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.RoomDTO;
import com.caddospace.caddoaccommodationhub.model.Room;
import com.caddospace.caddoaccommodationhub.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(APIRoute.ROOM_SAVE)
    public ResponseEntity<APIResponse<RoomDTO>> saveRoom(@Valid @RequestBody RoomDTO roomDTO) {
        return roomService.saveRoom(roomDTO);
    }

    @PutMapping(APIRoute.ROOM_UPDATE)
    public ResponseEntity<APIResponse<RoomDTO>> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomDTO roomDTO) {
        return roomService.updateRoom(id, roomDTO);
    }

    @DeleteMapping(APIRoute.ROOM_DELETE)
    public ResponseEntity<APIResponse<String>> deleteRoom(@PathVariable Long id) {
        return roomService.deleteRoom(id);
    }

    @GetMapping(APIRoute.ROOM_UPDATE)
    public ResponseEntity<APIResponse<List<RoomDTO>>> roomList() {
        return roomService.roomList();
    }
}
