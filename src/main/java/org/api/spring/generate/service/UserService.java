package org.api.spring.generate.service;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import org.api.spring.generate.entity.User;
import org.api.spring.generate.dto.UserDTO;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface UserService {

    User createOrUpdateUser(User user);

    void deleteUserById(Long id) throws EntityNotFoundException;

    User getUserById(Long id) throws EntityNotFoundException;

    List<UserDTO> findAll();

    ApiResponse<List<UserDTO>> doView();

    ApiResponse doAdd(User user);

    ApiResponse doEdit(User user);

    ApiResponse doDelete(List<User> userList);

    ApiResponse doPreview(UserDTO userDTO);

} 