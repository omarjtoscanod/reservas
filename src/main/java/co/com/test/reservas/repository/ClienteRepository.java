package co.com.test.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.test.reservas.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
