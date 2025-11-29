package com.caddospace.caddoaccommodationhub.service;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.UsersDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsersService {

    ResponseEntity<APIResponse<UsersDTO>> saveUsers(UsersDTO dto);

    ResponseEntity<APIResponse<UsersDTO>> updateUsers(Long id, UsersDTO dto);

    ResponseEntity<APIResponse<String>> deleteUsers(Long id);

    ResponseEntity<APIResponse<List<UsersDTO>>> usersList();

}
