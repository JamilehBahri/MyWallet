package ir.phgint.domain.repository;

import ir.phgint.domain.MerchantProfile;
import ir.phgint.domain.repository.manually.DaoRepository;
import ir.phgint.domain.repository.manually.DaoRepositoryImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
// public interface MerchantProfileDaoImpl extends JpaRepository<MerchantProfile, Long> {
public class MerchantProfileDaoImpl extends DaoRepositoryImpl <MerchantProfile,Long> implements MerchantProfileDao {





}
