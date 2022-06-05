package com.student.taxes.infrastructure;

import com.student.taxes.domain.HostelFeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelFeeRepository extends JpaRepository<HostelFeeEntity, Long> {
}
