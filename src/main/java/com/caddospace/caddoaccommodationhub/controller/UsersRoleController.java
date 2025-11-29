package com.caddospace.caddoaccommodationhub.controller;

import com.caddospace.caddoaccommodationhub.constant.APIRoute;
import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.UserRoleDTO;
import com.caddospace.caddoaccommodationhub.service.UserRoleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-role")
public class UsersRoleController {

    private final UserRoleService userRoleService;

    public UsersRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping(APIRoute.SAVE_USER_ROLE)
    public ResponseEntity<APIResponse<UserRoleDTO>> saveUserRole(@Valid @RequestBody UserRoleDTO userRoleDTO) {
        return userRoleService.saveUserRole(userRoleDTO);
    }

    @PutMapping(APIRoute.UPDATE_USER_ROLE)
    public ResponseEntity<APIResponse<UserRoleDTO>> updateUserRole(@PathVariable Long id, @Valid @RequestBody UserRoleDTO userRoleDTO) {
        return userRoleService.updateUserRole(id, userRoleDTO);
    }

    @DeleteMapping(APIRoute.DELETE_USER_ROLE)
    public ResponseEntity<APIResponse<String>> deleteUserRole(@PathVariable Long id) {
        return  userRoleService.deleteUserRole(id);
    }


    @GetMapping(APIRoute.USER_ROLE_LIST)
    public ResponseEntity<APIResponse<List<UserRoleDTO>>> listUserRole() {
        return userRoleService.getUserRoles();
    }
}
