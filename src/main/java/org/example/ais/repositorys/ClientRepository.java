package org.example.ais.repositorys;

import org.example.ais.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    @NonNull
    List<Client> findAll();

    @Query("SELECT c.passportPhoto FROM Client c WHERE c.id = :id")
    Optional<byte[]> findPassportPhotoById(@Param("id") Long id);


    @Modifying
    @Query("UPDATE Client c SET c.confirmed = true WHERE c.id = :id")
    void enableClientById(@Param("id") Long id);

    void deleteById(@NonNull Long id);
}
