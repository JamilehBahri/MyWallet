package ir.phgint.service;

import ir.phgint.domain.dto.UserProfileDto;

import java.util.List;
import java.util.UUID;

public interface UserServices {

     UserProfileDto findByUsername(String username);
     UserProfileDto findByEmail(String email);
     UserProfileDto saveUser(UserProfileDto userProfileDto);
     UserProfileDto findById(UUID uuid);
     UserProfileDto updateUser(UserProfileDto userProfileDto);
     void deleteUser(UserProfileDto userProfileDto);
     List<UserProfileDto> getAllUsers();

}
