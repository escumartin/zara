package com.example.zara.infrastructure.springdata;

import com.example.zara.infrastructure.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpringDataJpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p " + "WHERE p.brandId = :brandId " + "AND p.productId = :productId "
            + "AND p.startDate <= :applicationDate " + "AND p.endDate > :applicationDate " + "ORDER BY p.priority DESC")
    List<PriceEntity> findApplicablePrices(@Param("brandId") Long brandId, @Param("productId") Long productId,
            @Param("applicationDate") LocalDateTime applicationDate);

}
