package co.com.backend.reservas.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.com.backend.reservas.model.Cliente;
import co.com.backend.reservas.model.HorarioDisponible;
import co.com.backend.reservas.model.Reserva;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ReservaRepositoryTest {
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private HorarioDisponibleRepository horarioRepository;

    @Test
    @DisplayName("Guardar y recuperar una Reserva")
    void testSaveAndFindReserva() {
        // Crear cliente
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Prueba");
        cliente.setEmail("prueba@mail.com");

        // Crear horario
        HorarioDisponible horario = new HorarioDisponible();
        horario.setId(1L);
        horario.setFecha(LocalDate.of(2025, 2, 1));
        horario.setHoraInicio(LocalTime.of(9, 0));
        horario.setHoraFin(LocalTime.of(10, 0));
        horario.setCuposDisponibles(5);

        // Crear reserva
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setHorarioDisponible(horario);
        reserva.setFechaCreacion(LocalDateTime.now());
        reserva.setEstado("CONFIRMADA");

        Reserva savedReserva = reservaRepository.save(reserva);
        assertThat(savedReserva.getId()).isNotNull();

        Reserva found = reservaRepository.findById(savedReserva.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getCliente().getNombre()).isEqualTo("Prueba");
    }
}
