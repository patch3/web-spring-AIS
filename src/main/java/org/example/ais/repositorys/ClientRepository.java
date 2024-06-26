package org.example.ais.repositorys;

import org.example.ais.models.Client;
import org.example.ais.projections.ClientProjection;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Transactional(readOnly = true)
    Optional<Client> findByEmail(String email);

    @NonNull
    List<Client> findAll();

    @NonNull
    List<Client> findAll(@NonNull Sort sort);

    @Transactional(readOnly = true)
    @Query("SELECT c.passportPhoto FROM Client c WHERE c.id = :id")
    Optional<byte[]> findPassportPhotoById(@Param("id") Long id);


    @Modifying
    @Query("UPDATE Client c SET c.confirmed = true WHERE c.id = :clientId")
    void confirmClientById(Long clientId);


    /*
    @Transactional
    void updateConfirmedById(Long clientId, boolean confirmed);*/
    List<Client> findByConfirmedFalse();


    @NonNull
    @Transactional(readOnly = true)
    List<Client> findByConfirmedFalse(Sort sort);


    List<ClientProjection> findProjectionByFullNameStartingWithAndEmailStartingWithAndConfirmedFalse(
            String patternFullName, String patternEmail, Sort sort
    );

    void deleteById(@NonNull Long id);
}
