package com.student.taxes.infrastructure;

import com.student.taxes.domain.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity, Long> {
}
