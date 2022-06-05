package com.student.taxes.infrastructure;

import com.student.taxes.domain.ActiveFeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveFeeRepository extends JpaRepository<ActiveFeeEntity, Long> {
}
