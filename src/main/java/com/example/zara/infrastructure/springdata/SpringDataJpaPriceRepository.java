package com.example.zara.infrastructure.springdata;

import com.example.zara.infrastructure.model.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpringDataJpaPriceRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterAndPriorityOrderByPriorityDesc(
            Long brandId, Long productId, LocalDateTime startDate, LocalDateTime endDate, Integer priority);
}