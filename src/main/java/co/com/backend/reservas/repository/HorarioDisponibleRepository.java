package co.com.backend.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.backend.reservas.model.HorarioDisponible;

public interface HorarioDisponibleRepository extends JpaRepository<HorarioDisponible, Long>{
    
}
