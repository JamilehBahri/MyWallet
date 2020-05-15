package ir.phgint.domain.repository;

import ir.phgint.domain.Role;
import ir.phgint.domain.UserProfile;
import ir.phgint.domain.repository.manually.DaoRepositoryImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@Repository
 public class RoleDaoImpl extends DaoRepositoryImpl<Role,Integer> implements RoleDao{


 @Override
 public Role findByRoleName(String roleName) {

        CriteriaQuery<Role> query = entityManager.getCriteriaBuilder().createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        ParameterExpression<String> roleNameParam = entityManager.getCriteriaBuilder().parameter(String.class);

        query.select(root).distinct(true).where(
                entityManager.getCriteriaBuilder().equal(root.get("name") ,roleNameParam));

        TypedQuery<Role> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(roleNameParam,roleName);
        try {
         return typedQuery.getSingleResult();
        } catch (NoResultException ex) {
//         logger.info("findByRolename() => query.getSingleResult failed" +
//                 " ,exception: " + ex.getMessage(), ex);
        }

        return null;

 }
}
