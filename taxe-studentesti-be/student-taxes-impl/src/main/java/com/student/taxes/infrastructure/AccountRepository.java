package com.student.taxes.infrastructure;

import com.student.taxes.domain.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByEmail(String email);

    AccountEntity findByCnp(String cnp);
}
