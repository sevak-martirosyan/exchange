package repository;

import model.CurrencyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyModel, Long> {
    CurrencyModel findTopByOrderByTimeReceiptDesc();
}
