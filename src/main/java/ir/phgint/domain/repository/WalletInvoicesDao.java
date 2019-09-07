package ir.phgint.domain.repository;

import ir.phgint.domain.WalletInvoices;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface WalletInvoicesDao extends JpaRepository<WalletInvoices,Long > {

    List<WalletInvoices> findTop10ByOrderByTimestampDesc();



//    @PersistenceContext
//    protected EntityManager entityManager;
//
//    public WalletInvoicesDao() {
//    }

//    @Transactional
//    public List<WalletInvoices> find10TopUserTransactionByOrederTimestamp(String username){
//
//        Query query=  entityManager.createQuery("Select u from WalletInvoices u where u.userProfile.username = :username ORDER BY  u.timestamp desc , u.id desc" ,WalletInvoices.class);
//        query.setMaxResults(10);
//        query.setParameter("username" ,username);
//
//        return query.getResultList();
//
//    }
//
//    @Transactional
//    public List<WalletInvoices> find10TopMerchantTransactionByOrederTimestamp(Long id){
//
//        Query query=  entityManager.createQuery("Select u from WalletInvoices u where u.merchantProfile.merchantId = :id ORDER BY  u.timestamp desc , u.id desc" ,WalletInvoices.class);
//        query.setMaxResults(10);
//        query.setParameter("id" ,id);
//
//        return query.getResultList();
//
//    }
}
