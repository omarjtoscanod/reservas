package co.com.backend.reservas.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.com.backend.reservas.model.HorarioDisponible;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class HorarioDisponibleRepositoryTest {

    @Autowired
    private HorarioDisponibleRepository horarioRepository;

    @Test
    @DisplayName("Guardar y recuperar un HorarioDisponible")
    void testSaveAndFindHorario() {
        HorarioDisponible horario = new HorarioDisponible();
        horario.setFecha(LocalDate.of(2025, 2, 1));
        horario.setHoraInicio(LocalTime.of(9, 0));
        horario.setHoraFin(LocalTime.of(10, 0));
        horario.setCuposDisponibles(5);

        HorarioDisponible saved = horarioRepository.save(horario);
        assertThat(saved.getId()).isNotNull();

        HorarioDisponible found = horarioRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getCuposDisponibles()).isEqualTo(5);
    }
}
