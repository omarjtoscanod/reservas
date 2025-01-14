package co.com.test.reservas.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.test.reservas.model.Cliente;
import co.com.test.reservas.model.HorarioDisponible;
import co.com.test.reservas.model.Reserva;
import co.com.test.reservas.repository.ClienteRepository;
import co.com.test.reservas.repository.HorarioDisponibleRepository;
import co.com.test.reservas.repository.ReservaRepository;
import co.com.test.reservas.validation.ReservaValidator;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;

    private final HorarioDisponibleRepository horarioDisponibleRepository;

    private final ReservaValidator reservaValidator;

    public ReservaService(ReservaRepository reservaRepository, HorarioDisponibleRepository horarioDisponibleRepository,
            ClienteRepository clienteRepository, ReservaValidator reservaValidator) {
        this.reservaRepository = reservaRepository;
        this.horarioDisponibleRepository = horarioDisponibleRepository;
        this.reservaValidator = reservaValidator;
    }

    @Transactional
    public Reserva crearReserva(Long clientId, Long horarioId) {
        Cliente cliente = reservaValidator.validarClienteExiste(clientId);

        HorarioDisponible horarioDisponible = reservaValidator.validarHorarioExistente(horarioId);

        reservaValidator.validarCuposDisponibles(horarioDisponible);

        horarioDisponible.setCuposDisponibles(horarioDisponible.getCuposDisponibles() - 1);
        horarioDisponibleRepository.save(horarioDisponible);

        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setHorarioDisponible(horarioDisponible);
        reserva.setFechaCreacion(LocalDateTime.now());
        reserva.setEstado("CONFIRMADA");

        return reservaRepository.save(reserva);
    }

    public List<Reserva> obtenerReservasPorFecha(LocalDate fecha) {
        return reservaRepository.findAll()
                .stream()
                .filter(r -> r.getHorarioDisponible().getFecha().equals(fecha))
                .toList();
    }

    @Transactional
    public Reserva actualizarReserva(Long reservaId, String nuevoEstado) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado(nuevoEstado);
        return reservaRepository.save(reserva);
    }

    @Transactional
    public void eliminarReserva(Long reservaId) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        HorarioDisponible horario = reserva.getHorarioDisponible();
        horario.setCuposDisponibles(horario.getCuposDisponibles() + 1);
        horarioDisponibleRepository.save(horario);

        reservaRepository.delete(reserva);
    }

}
