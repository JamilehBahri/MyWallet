package ir.phgint.domain.repository;

import ir.phgint.domain.Role;
import ir.phgint.domain.repository.manually.DaoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

//public interface RoleDao extends JpaRepository<Role, Integer> {
//
//    Role findByRoleName(String roleName);
//}

public interface RoleDao extends DaoRepository<Role, Integer> {

    Role findByRoleName(String roleName);
}