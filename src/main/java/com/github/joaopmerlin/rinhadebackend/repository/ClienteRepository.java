package com.github.joaopmerlin.rinhadebackend.repository;

import com.github.joaopmerlin.rinhadebackend.entity.Cliente;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    @Query("select * from cliente where id = :id for update")
    Optional<Cliente> findByIdForUpdate(Integer id);
}
