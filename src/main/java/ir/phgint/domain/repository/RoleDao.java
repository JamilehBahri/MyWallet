package ir.phgint.domain.repository;

import ir.phgint.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {

    Role findByName(String roleName);
}
