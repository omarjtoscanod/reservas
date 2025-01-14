package co.com.backend.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.backend.reservas.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
