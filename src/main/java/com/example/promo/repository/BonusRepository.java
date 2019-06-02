package com.example.promo.repository;

import com.example.promo.model.Bonus;
import com.example.promo.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {
    List<Bonus> findBonusByPromo(Promo promo);
    List<Bonus> findBonusByPromoAndBonusNameContaining(Promo promo, String filter);
    List<Bonus> findBonusByPromoAndInStockGreaterThan(Promo promo, int inStock);
}