package org.example.ais.repositorys;

import org.example.ais.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
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
    @Override
    List<Client> findAll();

    // Запрос для получения только фото по ID
    @Query("SELECT c.passportPhoto FROM Client c WHERE c.id = :id")
    byte[] findImageDataById(@Param("id") Long id);

}
