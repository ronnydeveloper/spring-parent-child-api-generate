package org.api.spring.generate.service.impl;

import id.co.ptap.circleaf.core.dto.ApiResponse;
import id.co.ptap.circleaf.core.enums.ResponseCode;
import org.api.spring.generate.entity.User;
import org.api.spring.generate.dto.UserDTO;
import org.api.spring.generate.repository.UserRepo;
import org.api.spring.generate.service.UserService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;
import java.util.ArrayList;

@Service("userService")
public class UserServiceImpl implements UserService {

    static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepo userRepository;

    @Override
    public User createOrUpdateUser(User user) {
         Optional<User> userOptional = userRepository.findById(user.getId());
         if(userOptional.isPresent())
         {
             ModelMapper modelMapper = new ModelMapper();
             User newUser = modelMapper.map(user, User.class);
             newUser = userRepository.save(newUser);
             return newUser;
         } else {
             user = userRepository.save(user);
             return user;
         }
    }

    @Override
    public void deleteUserById(Long id) throws EntityNotFoundException {
         Optional<User> userOptional = userRepository.findById(id);
         if(userOptional.isPresent())
         {
            userRepository.deleteById(id);
         } else {
            throw new EntityNotFoundException("No User record exist for given id");
         }
    }

    @Override
    public User getUserById(Long id) throws EntityNotFoundException {
         Optional<User> userOptional = userRepository.findById(id);
         if(userOptional.isPresent())
         {
            return userOptional.get();
         } else {
            throw new EntityNotFoundException("No User record exist for given id");
         }
    }

    @Override
    public ApiResponse<List<UserDTO>> doView() {
         ApiResponse apiResponse = new ApiResponse();
         try {
             List<UserDTO> userList = this.findAll();
             apiResponse.setData(userList);
         } catch (Exception var3) {
             logger.error(var3);
             apiResponse.setResponseCodeEnum(ResponseCode._999);
             apiResponse.setResponseMessage(var3.getMessage());
         }
         return apiResponse;
    }

    @Override
    public ApiResponse doAdd(User user) {
         ApiResponse apiResponse = new ApiResponse();
         try {
            if (user == null) {
                throw new NullPointerException("User cannot be null");
            }
            else {
                long max = 0;
                long count = userRepository.count();
                if(count < 1) {
                    max = 1;
                } else {
                    max = userRepository.max();
                }
                user.setId(max);
            }
            Optional<User> userOptional = userRepository.findById(user.getId());
            if(userOptional.isPresent()) {
                throw new EntityExistsException("There is a User record exist for given id");
            } else {
                this.createOrUpdateUser(user);
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doEdit(User user) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (user == null) {
                throw new NullPointerException("user cannot be null");
            }
            this.createOrUpdateUser(user);
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doDelete(List<User> userList) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if (userList.size() < 1) {
                throw new NullPointerException("user cannot be null");
            }
            for (User obj : userList) {
                this.deleteUserById(obj.getId());
            }
        } catch (Exception var5) {
            logger.error(var5);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var5.getMessage());
        }
        return apiResponse;
    }

    @Override
    public ApiResponse doPreview(UserDTO userDTO) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(this.getUserById(userDTO.getId()));
        } catch (Exception var3) {
            logger.error(var3);
            apiResponse.setResponseCodeEnum(ResponseCode._999);
            apiResponse.setResponseMessage(var3.getMessage());
        }
        return apiResponse;
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> newList = new ArrayList<>();
        for(User p : userRepository.findAll()) {
             UserDTO userDTO = UserDTO.builder()
                     .id(p.getId())
                     .name(p.getName())
                     .companyID(p.getCompanyID()).build();
             newList.add(userDTO);
        }
        return newList;
    }

} 