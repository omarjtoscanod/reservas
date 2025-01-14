package co.com.test.reservas.validation;

import org.springframework.stereotype.Component;

import co.com.test.reservas.model.Cliente;
import co.com.test.reservas.model.HorarioDisponible;
import co.com.test.reservas.repository.ClienteRepository;
import co.com.test.reservas.repository.HorarioDisponibleRepository;

@Component
public class ReservaValidator {

    private final ClienteRepository clienteRepository;

    private final HorarioDisponibleRepository horarioDisponibleRepository;

    public ReservaValidator(ClienteRepository clienteRepository,
            HorarioDisponibleRepository horarioDisponibleRepository) {
        this.clienteRepository = clienteRepository;
        this.horarioDisponibleRepository = horarioDisponibleRepository;
    }

    public Cliente validarClienteExiste(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public HorarioDisponible validarHorarioExistente(Long horarioId) {
        return horarioDisponibleRepository.findById(horarioId)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));
    }

    public void validarCuposDisponibles(HorarioDisponible horario) {
        if (horario.getCuposDisponibles() <= 0) {
            throw new RuntimeException("No hay cupos disponibles para este horario");
        }
    }

}
