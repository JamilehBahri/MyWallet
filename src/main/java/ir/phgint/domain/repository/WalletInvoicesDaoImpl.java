package ir.phgint.domain.repository;

import ir.phgint.domain.Role;
import ir.phgint.domain.WalletInvoices;
import ir.phgint.domain.repository.manually.DaoRepositoryImpl;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
 public class WalletInvoicesDaoImpl extends DaoRepositoryImpl<WalletInvoices,Long> implements WalletInvoicesDao{


 @Override
 public List<WalletInvoices> findTop10ByOrderByTimestampDesc() {
//  Query query=  entityManager.createQuery("Select u from WalletInvoices u where u.userProfile.username = :username ORDER BY  u.timestamp desc , u.id desc" ,WalletInvoices.class);
//        query.setMaxResults(10);
//        query.setParameter("username" ,username);
//
//        return query.getResultList();

     CriteriaQuery<WalletInvoices> query = entityManager.getCriteriaBuilder().createQuery(WalletInvoices.class);
     Root<WalletInvoices> root = query.from(WalletInvoices.class);
     query.select(root);
     TypedQuery<WalletInvoices> typedQuery = entityManager.createQuery(query);
     typedQuery.setMaxResults(10);
//     typedQuery.setParameter("username" , typedQuery.getParameter("username"));
     return typedQuery.getResultList();


 }

}
