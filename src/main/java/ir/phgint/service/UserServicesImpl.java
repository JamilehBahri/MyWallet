package ir.phgint.service;

import ir.phgint.domain.Role;
import ir.phgint.domain.UserProfile;
import ir.phgint.domain.dto.UserProfileDto;
import ir.phgint.domain.repository.RoleDao;
import ir.phgint.domain.repository.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
 public class UserServicesImpl implements UserServices {

    @Autowired
    private UserProfileDao userProfileDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserProfileDto findByUsername(String username) {

        UserProfile userProfile = userProfileDao.findUserByUsername(username);
        UserProfileDto userProfileDto = convertUserProfileDto(userProfile);
        if (userProfileDto != null)
            return userProfileDto;
        return null;


    }

    @Override
    public UserProfileDto findByEmail(String email) {
        UserProfile userProfile = userProfileDao.findUserByEmail(email);
        if(userProfile==null)
            return null;
        else {
            UserProfileDto userProfileDto = convertUserProfileDto(userProfile);
            if (userProfileDto != null)
                return userProfileDto;
        }
        return null;

    }

    @Override
    public UserProfileDto findById(UUID uuid) {

        UserProfile userProfile = userProfileDao.findUserById(uuid);
        UserProfileDto userProfileDto = convertUserProfileDto(userProfile);
        if (userProfileDto != null)
            return userProfileDto;
        return null;

    }

    @Override
    public UserProfileDto updateUser(UserProfileDto userProfileDto) {


        UserProfile userProfile = convertUserProfile(userProfileDto);

        if (userProfile!=null) {
            userProfile = userProfileDao.save(userProfile);
            return convertUserProfileDto(userProfile);
        }
        return null;
    }

    @Override
    public void deleteUser(UserProfileDto userProfileDto) {
      //  UserProfile userProfile = convertUserProfile(userProfileDto);
        UserProfile userProfile = userProfileDao.findUserByUsername(userProfileDto.getUsername());
        userProfileDao.delete(userProfile);
    }

    @Override
    public List<UserProfileDto> getAllUsers() {
        List<UserProfileDto> userProfileDtos = new ArrayList<UserProfileDto>();

        Iterable<UserProfile> userProfile = userProfileDao.findAll();
        for (UserProfile user :userProfile) {
            userProfileDtos.add(convertUserProfileDto(user));
        }
        return userProfileDtos;

    }

    @Override
    public UserProfileDto saveUser(UserProfileDto userProfileDto) {
        try {
            UserProfile userProfile = convertUserProfile(userProfileDto);
            if (userProfile != null) {
                userProfileDao.save(userProfile);
                return convertUserProfileDto(userProfile);
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }



    public UserProfile convertUserProfile(UserProfileDto userProfileDto) {
        try {
            UserProfile userProfile = new UserProfile();
            if (userProfileDto != null) {
                Role role = new Role();
                role = roleDao.findByRoleName(userProfileDto.getRole());

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                //   String dateString = format.format( new Date()   );
                Date date = format.parse(userProfileDto.getBirthday());
                userProfile.setId(userProfileDto.getId());
                userProfile.setName(userProfileDto.getName());
                userProfile.setFamily(userProfileDto.getFamily());
                userProfile.setEmail(userProfileDto.getEmail());
                userProfile.setMobile(userProfileDto.getMobile());
                userProfile.setUsername(userProfileDto.getUsername());
                userProfile.setPassword(userProfileDto.getPassword());
                userProfile.setRole(role);
                userProfile.setNationalId(userProfileDto.getNationalId());
                userProfile.setGender(userProfileDto.getGender());
                userProfile.setBirthday(date);
                userProfile.setAddress(userProfileDto.getAddress());
                userProfile.setPhone(userProfileDto.getPhone());
                userProfile.setBalance(userProfileDto.getBalance());
                return userProfile;
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public UserProfileDto convertUserProfileDto(UserProfile userProfile) {
        UserProfileDto userProfileDto = new UserProfileDto();
        if (userProfile != null) {
            userProfileDto.setId(userProfile.getId());
            userProfileDto.setName(userProfile.getName());
            userProfileDto.setFamily(userProfile.getFamily());
            userProfileDto.setEmail(userProfile.getEmail());
            userProfileDto.setMobile(userProfile.getMobile());
            userProfileDto.setUsername(userProfile.getUsername());
            userProfileDto.setPassword(userProfile.getPassword());
            userProfileDto.setRole(userProfile.getRole().getName());
            userProfileDto.setNationalId(userProfile.getNationalId());
            userProfileDto.setGender(userProfile.getGender());
            if(userProfile.getBirthday()!=null)
                userProfileDto.setBirthday(userProfile.getBirthday().toString());
            userProfileDto.setAddress(userProfile.getAddress());
            userProfileDto.setPhone(userProfile.getPhone());

            return userProfileDto;
        }
        return null;
    }

}
