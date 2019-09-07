package ir.phgint.domain.repository;

import ir.phgint.domain.UserProfile;

import java.util.List;
import java.util.UUID;

public interface UserProfileDao {
    UserProfile findUserByUsername(String username);
    UserProfile findByEmail(String email);
    UserProfile findUserById(UUID username);
    void  saveUser(UserProfile userProfile);
    UserProfile updateUser(UserProfile newUserProfile);
    void deleteUser(UserProfile userProfile);
    void mergeDetachObj(UserProfile userProfile);
    List<UserProfile> getAllUsers();

}
