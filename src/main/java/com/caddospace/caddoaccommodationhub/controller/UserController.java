package com.caddospace.caddoaccommodationhub.controller;

import com.caddospace.caddoaccommodationhub.constant.APIRoute;
import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.UsersDTO;
import com.caddospace.caddoaccommodationhub.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(APIRoute.SAVE_USER)
    public ResponseEntity<APIResponse<UsersDTO>> saveUser(@Valid @RequestBody UsersDTO dto) {
        return usersService.saveUsers(dto);
    }

    @PutMapping(APIRoute.UPDATE_USER)
    public ResponseEntity<APIResponse<UsersDTO>> updateUser(@PathVariable Long id, @Valid @RequestBody UsersDTO dto) {
        return usersService.updateUsers(id, dto);
    }

    @DeleteMapping(APIRoute.DELETE_USER)
    public ResponseEntity<APIResponse<String>> deleteUser(@PathVariable Long id) {
        return usersService.deleteUsers(id);
    }

    @GetMapping(APIRoute.USER_LIST)
    public ResponseEntity<APIResponse<List<UsersDTO>>> getAllUsers() {
        return usersService.usersList();
    }
}
