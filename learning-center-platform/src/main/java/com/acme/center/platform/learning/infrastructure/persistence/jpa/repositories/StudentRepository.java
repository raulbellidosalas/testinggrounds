package com.acme.center.platform.learning.infrastructure.persistence.jpa.repositories;

import com.acme.center.platform.learning.domain.model.aggregates.Student;
import com.acme.center.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByAcmeStudentRecordId(Long studentRecordId);

    Optional<Student> findByProfileId(Long profileId);

    boolean existsByAcmeStudentRecordId(AcmeStudentRecordId studentRecordId);
}
