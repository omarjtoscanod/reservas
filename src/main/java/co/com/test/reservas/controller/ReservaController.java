package co.com.test.reservas.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.com.test.reservas.model.Reserva;
import co.com.test.reservas.service.ReservaService;

public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public Reserva crearReserva(@RequestParam Long clienteId,
            @RequestParam Long horarioId) {
        return reservaService.crearReserva(clienteId, horarioId);
    }

    @GetMapping("/{fecha}")
    public List<Reserva> obtenerReservasPorDia(@PathVariable String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        return reservaService.obtenerReservasPorFecha(localDate);
    }

    @PutMapping("/{reservaId}")
    public Reserva actualizarReserva(@PathVariable Long reservaId,
            @RequestParam String nuevoEstado) {
        return reservaService.actualizarReserva(reservaId, nuevoEstado);
    }

    @DeleteMapping("/{reservaId}")
    public void eliminarReserva(@PathVariable Long reservaId) {
        reservaService.eliminarReserva(reservaId);
    }
}
