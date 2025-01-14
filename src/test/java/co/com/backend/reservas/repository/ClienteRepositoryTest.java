package co.com.backend.reservas.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.com.backend.reservas.model.Cliente;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ClienteRepositoryTest {
    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    @DisplayName("Guardar y recuperar un Cliente")
    void testSaveAndFindCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Test Cliente");
        cliente.setEmail("test@mail.com");
        cliente.setTelefono("3156254879");

        Cliente saved = clienteRepository.save(cliente);
        assertThat(saved.getId()).isNotNull();

        Cliente found = clienteRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getNombre()).isEqualTo("Test Cliente");
    }
}
