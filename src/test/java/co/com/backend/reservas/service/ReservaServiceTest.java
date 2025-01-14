package co.com.backend.reservas.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.backend.reservas.model.Cliente;
import co.com.backend.reservas.model.HorarioDisponible;
import co.com.backend.reservas.model.Reserva;
import co.com.backend.reservas.repository.ClienteRepository;
import co.com.backend.reservas.repository.HorarioDisponibleRepository;
import co.com.backend.reservas.repository.ReservaRepository;
import co.com.backend.reservas.validation.ReservaValidator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(SpringExtension.class)
public class ReservaServiceTest {
    @Mock
    private ReservaRepository reservaRepository;
    @Mock
    private HorarioDisponibleRepository horarioRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ReservaValidator reservaValidator;

    @InjectMocks
    private ReservaService reservaService;

    private Cliente mockCliente;
    private HorarioDisponible mockHorario;

    @BeforeEach
    void setUp() {
        mockCliente = new Cliente();
        mockCliente.setId(1L);
        mockCliente.setNombre("Cliente Mock");

        mockHorario = new HorarioDisponible();
        mockHorario.setId(10L);
        mockHorario.setCuposDisponibles(5);
        mockHorario.setFecha(LocalDate.of(2025, 2, 1));
    }

    @Test
    void testCrearReserva() {
        given(reservaValidator.validarClienteExiste(1L)).willReturn(mockCliente);
        given(reservaValidator.validarHorarioExistente(10L)).willReturn(mockHorario);

        willDoNothing().given(reservaValidator).validarCuposDisponibles(mockHorario);

        Reserva mockReservaSaved = new Reserva();
        mockReservaSaved.setId(100L);
        given(reservaRepository.save(any(Reserva.class))).willReturn(mockReservaSaved);

        Reserva nuevaReserva = reservaService.crearReserva(1L, 10L);

        assertThat(nuevaReserva.getId()).isEqualTo(100L);
        assertThat(mockHorario.getCuposDisponibles()).isEqualTo(4);
        verify(horarioRepository).save(mockHorario);
    }

    @Test
    void testObtenerReservasPorFecha() {
        Reserva r1 = new Reserva();
        HorarioDisponible h1 = new HorarioDisponible();
        h1.setFecha(LocalDate.of(2025, 2, 1));
        r1.setHorarioDisponible(h1);

        Reserva r2 = new Reserva();
        HorarioDisponible h2 = new HorarioDisponible();
        h2.setFecha(LocalDate.of(2025, 2, 2));
        r2.setHorarioDisponible(h2);

        given(reservaRepository.findAll()).willReturn(List.of(r1, r2));

        List<Reserva> reservas = reservaService.obtenerReservasPorFecha(LocalDate.of(2025, 2, 1));
        assertThat(reservas).hasSize(1);
    }

    @Test
    void testActualizarReserva() {
        Reserva existing = new Reserva();
        existing.setId(200L);
        existing.setEstado("CONFIRMADA");
        given(reservaRepository.findById(200L)).willReturn(Optional.of(existing));

        given(reservaRepository.save(existing)).willReturn(existing);

        Reserva updated = reservaService.actualizarReserva(200L, "CANCELADA");
        assertThat(updated.getEstado()).isEqualTo("CANCELADA");
    }

    @Test
    void testEliminarReserva() {
        Reserva existing = new Reserva();
        existing.setId(300L);
        existing.setHorarioDisponible(mockHorario); // cupos 5
        given(reservaRepository.findById(300L)).willReturn(Optional.of(existing));

        reservaService.eliminarReserva(300L);

        verify(reservaRepository).delete(existing);

        assertThat(mockHorario.getCuposDisponibles()).isEqualTo(6);
        verify(horarioRepository).save(mockHorario);
    }
}
