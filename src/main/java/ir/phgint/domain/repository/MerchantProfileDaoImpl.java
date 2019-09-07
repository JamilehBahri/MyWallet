package ir.phgint.domain.repository;

import ir.phgint.domain.MerchantProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
 public interface MerchantProfileDaoImpl extends JpaRepository<MerchantProfile, Long> {





}
