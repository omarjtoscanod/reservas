package co.com.test.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.test.reservas.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
}
