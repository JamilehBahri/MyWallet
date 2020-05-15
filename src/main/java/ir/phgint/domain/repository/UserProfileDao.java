package ir.phgint.domain.repository;

import ir.phgint.domain.UserProfile;
import ir.phgint.domain.repository.manually.DaoRepository;

import java.util.List;
import java.util.UUID;

public interface UserProfileDao extends DaoRepository<UserProfile,Long> {
    UserProfile findUserByUsername(String username);
    UserProfile findUserByEmail(String email);
    UserProfile findUserById(UUID useruuid);
//    void  saveUser(UserProfile userProfile);
//    UserProfile updateUser(UserProfile newUserProfile);
//    void deleteUser(UserProfile userProfile);
//    void mergeDetachObj(UserProfile userProfile);
//    List<UserProfile> getAllUsers();

}
