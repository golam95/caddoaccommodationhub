package com.caddospace.caddoaccommodationhub.serviceImpl;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.UsersDTO;
import com.caddospace.caddoaccommodationhub.model.UserRole;
import com.caddospace.caddoaccommodationhub.model.Users;
import com.caddospace.caddoaccommodationhub.repository.UserRoleRepository;
import com.caddospace.caddoaccommodationhub.repository.UsersRepository;
import com.caddospace.caddoaccommodationhub.service.UsersService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserRoleRepository userRoleRepository;

    public UsersServiceImpl(UsersRepository usersRepository, UserRoleRepository userRoleRepository) {
        this.usersRepository = usersRepository;
        this.userRoleRepository = userRoleRepository;
    }

    // CONVERT RESPONSE DTO INTO ENTITY
    private Users convertDtoEntity(UsersDTO dto, Users entity, UserRole role) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhone(dto.getPhone());
        entity.setUserRole(role);
        return entity;
    }

    //    CONVERT ENTITY RESPONSE INTO DTO
    private UsersDTO convertDto(Users entity) {
        UsersDTO dto = new UsersDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setPhone(entity.getPhone());
        dto.setRoleId(entity.getUserRole().getRoleId());
        return dto;
    }

    @Override
    public ResponseEntity<APIResponse<UsersDTO>> saveUsers(UsersDTO dto) {

        UserRole existingRole = userRoleRepository.findById(dto.getRoleId()).orElseThrow(() -> new RuntimeException("Role Not Found with id: " + dto.getRoleId()));
        Users user = convertDtoEntity(dto, new Users(), existingRole);
        Users savedUser = usersRepository.save(user);
        UsersDTO responseDto = convertDto(savedUser);
        return new ResponseEntity<>(new APIResponse<>(true, 201, "USER CREATED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<UsersDTO>> updateUsers(Long id, UsersDTO dto) {

        Users existingUser = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found with id: " + id));
        UserRole existingRole = userRoleRepository.findById(dto.getRoleId()).orElseThrow(() -> new RuntimeException("Role Not Found with id: " + dto.getRoleId()));

        convertDtoEntity(dto, existingUser, existingRole);
        Users updatedUser = usersRepository.save(existingUser);
        UsersDTO responseDto = convertDto(updatedUser);

        return new ResponseEntity<>(new APIResponse<>(true, 200, "USER UPDATED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> deleteUsers(Long id) {

        if (!usersRepository.existsById(id)) {
            return new ResponseEntity<>(new APIResponse<>(false, 404, "USER NOT FOUND WITH ID: " + id, null), HttpStatus.NOT_FOUND);
        }
        usersRepository.deleteById(id);
        return new ResponseEntity<>(new APIResponse<>(true, 201, "USER DELETED SUCCESSFULLY", null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<List<UsersDTO>>> usersList() {
        List<Users> usersList = usersRepository.findAll();
        List<UsersDTO> responseDto = usersList.stream().map(this::convertDto).toList();
        return new ResponseEntity<>(new APIResponse<>(true, 201, "ALL USER FETCHED SUCCESSFULLY", responseDto), HttpStatus.OK);
    }
}
