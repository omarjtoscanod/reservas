package co.com.test.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.test.reservas.model.HorarioDisponible;

public interface HorarioDisponibleRepository extends JpaRepository<HorarioDisponible, Long>{
    
}
