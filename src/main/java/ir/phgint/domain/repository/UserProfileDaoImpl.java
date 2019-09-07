package ir.phgint.domain.repository;

import ir.phgint.domain.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@Repository
 public class UserProfileDaoImpl implements UserProfileDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public UserProfileDaoImpl() {
    }

    @Override
    @Transactional
    public UserProfile findUserByUsername(String username) {
        try {

            Query query = entityManager.createQuery("Select u from UserProfile u where u.username = :username");
            query.setParameter("username", username);
            return (UserProfile) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    @Transactional
    public UserProfile findByEmail(String email) {
        try {

            Query query = entityManager.createQuery("Select u from UserProfile u where u.email = :email");
            query.setParameter("email", email);
            return (UserProfile) query.getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    @Transactional
    public UserProfile findUserById(UUID uuid) {
        try {
            return entityManager.find(UserProfile.class, uuid);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public void saveUser(UserProfile userProfile) {

//        userProfile.setBirthday("1370-11-20");
        entityManager.persist(userProfile);
//        entityManager.flush();

    }

    @Override
    @Transactional
    public UserProfile updateUser(UserProfile newUserProfile) {

      return entityManager.merge(newUserProfile);

    }

    @Override
    @Transactional
    public void deleteUser(UserProfile userProfile) {

        entityManager.remove(userProfile);
        entityManager.flush();

    }

    @Override
    public void mergeDetachObj(UserProfile userProfile) {
        entityManager.merge(userProfile);
    }

    @Override
    public List<UserProfile> getAllUsers() {
        List<UserProfile> allUsers = entityManager.createQuery("Select u from UserProfile u", UserProfile.class).getResultList();
        System.out.println("allUsers :‌" +allUsers);
        return allUsers;
    }
}
