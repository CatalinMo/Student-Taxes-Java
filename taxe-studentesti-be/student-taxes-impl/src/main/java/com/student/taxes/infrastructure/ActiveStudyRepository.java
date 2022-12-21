package com.student.taxes.infrastructure;

import com.student.taxes.domain.ActiveStudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveStudyRepository extends JpaRepository<ActiveStudyEntity, Long> {
}
