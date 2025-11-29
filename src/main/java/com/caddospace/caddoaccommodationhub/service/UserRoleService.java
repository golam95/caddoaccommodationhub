package com.caddospace.caddoaccommodationhub.service;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.UserRoleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserRoleService {

    ResponseEntity<APIResponse<UserRoleDTO>> saveUserRole(UserRoleDTO Dto);

    ResponseEntity<APIResponse<UserRoleDTO>> updateUserRole(Long id, UserRoleDTO Dto);

    ResponseEntity<APIResponse<String>> deleteUserRole(Long id);

    ResponseEntity<APIResponse<List<UserRoleDTO>>> getUserRoles();
}
