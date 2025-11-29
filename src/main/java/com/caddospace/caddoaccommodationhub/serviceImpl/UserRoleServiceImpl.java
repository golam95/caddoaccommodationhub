package com.caddospace.caddoaccommodationhub.serviceImpl;

import com.caddospace.caddoaccommodationhub.dto.APIResponse;
import com.caddospace.caddoaccommodationhub.dto.UserRoleDTO;
import com.caddospace.caddoaccommodationhub.model.UserRole;
import com.caddospace.caddoaccommodationhub.repository.UserRoleRepository;
import com.caddospace.caddoaccommodationhub.service.UserRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public ResponseEntity<APIResponse<UserRoleDTO>> saveUserRole(UserRoleDTO Dto) {

        UserRole userRole = new UserRole();
        userRole.setRoleType(Dto.getRoleType());
        UserRole saved = userRoleRepository.save(userRole);

        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRoleId(saved.getRoleId());
        userRoleDTO.setRoleType(saved.getRoleType());

        return new ResponseEntity<>(new APIResponse<>(true, 201, "USER ROLE CREATED SUCCESSFULLY", userRoleDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<UserRoleDTO>> updateUserRole(Long id, UserRoleDTO Dto) {

        UserRole existingUserRole = userRoleRepository.findById(id).orElseThrow(() -> new RuntimeException("UserRole Not Found with id: " + id));
        existingUserRole.setRoleType(Dto.getRoleType());
        UserRole saved = userRoleRepository.save(existingUserRole);

        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setRoleId(saved.getRoleId());
        userRoleDTO.setRoleType(saved.getRoleType());
        return new ResponseEntity<>(new APIResponse<>(true, 201, "USER ROLE UPDATED SUCCESSFULLY", userRoleDTO), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<String>> deleteUserRole(Long id) {
        if (!userRoleRepository.existsById(id)) {
            return new ResponseEntity<>(new APIResponse<>(false, 404, "ROLE NOT FOUND WITH ID: " + id, null), HttpStatus.NOT_FOUND);
        }
        userRoleRepository.deleteById(id);
        return new ResponseEntity<>(new APIResponse<>(true, 201, "USER ROLE DELETED SUCCESSFULLY", null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse<List<UserRoleDTO>>> getUserRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();
        List<UserRoleDTO> dtoList = userRoles.stream().map(userRole -> {
            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setRoleId(userRole.getRoleId());
            userRoleDTO.setRoleType(userRole.getRoleType());
            return userRoleDTO;
        }).toList();

        return new ResponseEntity<>(new APIResponse<>(true, 200, "USER ROLE LIST FETCHED SUCCESSFULLY", dtoList), HttpStatus.OK);
    }
}
