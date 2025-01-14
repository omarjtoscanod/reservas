package co.com.backend.reservas.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import co.com.backend.reservas.model.Reserva;
import co.com.backend.reservas.service.ReservaService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.Collections;

@WebMvcTest(ReservaController.class)
class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReservaService reservaService;

    @Test
    void testCrearReserva() throws Exception {
        Reserva mockReserva = new Reserva();

        mockReserva.setId(1L);

        BDDMockito.given(reservaService.crearReserva(1L, 1L)).willReturn(mockReserva);

        mockMvc.perform(post("/api/reservas")
                .param("clienteId", "1")
                .param("horarioId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testObtenerReservasPorDia() throws Exception {
        BDDMockito.given(
                reservaService.obtenerReservasPorFecha(LocalDate.of(2025, 2, 1))).willReturn(Collections.emptyList());

        mockMvc.perform(get("/api/reservas/2025-02-01"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void testActualizarReserva() throws Exception {
        Reserva mockReserva = new Reserva();
        mockReserva.setId(10L);
        mockReserva.setEstado("CANCELADA");

        BDDMockito.given(
                reservaService.actualizarReserva(10L, "CANCELADA")
        ).willReturn(mockReserva);

        mockMvc.perform(put("/api/reservas/10")
                .param("nuevoEstado", "CANCELADA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("CANCELADA"));
    }

    @Test
    void testEliminarReserva() throws Exception {
        // No retornamos nada, solo simulamos la llamada
        Mockito.doNothing().when(reservaService).eliminarReserva(20L);

        mockMvc.perform(delete("/api/reservas/20"))
                .andExpect(status().isOk());

        Mockito.verify(reservaService, Mockito.times(1)).eliminarReserva(20L);
    }
}
