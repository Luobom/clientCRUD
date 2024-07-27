package com.w3lsolucoes.clientCRUD.repositories;

import com.w3lsolucoes.clientCRUD.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
