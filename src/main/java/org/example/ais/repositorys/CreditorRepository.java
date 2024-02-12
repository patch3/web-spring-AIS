package org.example.ais.repositorys;

import org.example.ais.models.Creditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditorRepository extends JpaRepository<Creditor, Long> {
    Optional<Creditor> findByEmail(String email);
    Long countByEmail(String email);


}
