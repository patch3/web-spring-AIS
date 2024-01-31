package org.example.contosositemaven.repositorys;

import org.example.contosositemaven.models.Creditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditorRepository extends JpaRepository<Creditor, Long> {
    Optional<Creditor> findByEmail(String email);

}
