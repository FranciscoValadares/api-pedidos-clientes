package api.com.valadares.pedidos.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import api.com.valadares.pedidos.entity.Cliente;
import api.com.valadares.pedidos.entity.Pedido;
import api.com.valadares.pedidos.exception.ExcecaoDeNegocio;
import api.com.valadares.pedidos.repository.PedidoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class PedidoServiceTest {
    
    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarPedidosClientes_shouldThrowExceptionWhenPedidosListIsNull() {

        Exception cause = new Exception("O arquivo pode conter 1 ou mais pedidos, limitado a 10.");
        Exception e = new RuntimeException("Error!", cause);
         
        assertThat(e).isInstanceOf(RuntimeException.class);
         
        assertThat(e).hasCause(cause)
                     .hasCauseReference(cause)
                     .hasCauseInstanceOf(Exception.class);
                     
    }

    @Test
    void criarPedidosClientes_shouldSaveAllPedidos() {
        List<Pedido> pedidos = this.getMockedClientePedidosList();

        //List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        pedidoService.criarPedidosClientes(pedidos);

        verify(pedidoRepository, times(1)).saveAll(pedidos);
    }


    private List<Pedido> getMockedClientePedidosList() {

        List<Pedido> pedidos = new ArrayList<>();
        
        Cliente cliente1 = new Cliente(1L, 1001L , "Francisco");
        Pedido pedido1 = new Pedido(null, "001", LocalDateTime.now(), "Cerveja", new BigDecimal("199.99"), 2, new BigDecimal("399.98"), cliente1);
        pedidos.add(pedido1);

        Cliente cliente2 = new Cliente(2L, 1002L , "Maria");
        Pedido pedido2 = new Pedido(null, "002", LocalDateTime.now(), "Shampoo", new BigDecimal("33.99"), 4, new BigDecimal("133.96"), cliente2);
        pedidos.add(pedido2);

        Cliente cliente3 = new Cliente(3L, 1003L , "Joaquim Valadares");
        Pedido pedido3 = new Pedido(null, "003", LocalDateTime.now(), "Furadeira", new BigDecimal("599.99"), 1, new BigDecimal("599.99"), cliente3);
        pedidos.add(pedido3);

        Cliente cliente4 = new Cliente(4L, 1004L , "Joaquim Almeida");
        Pedido pedido4 = new Pedido(null, "004", LocalDateTime.now(), "Peneu de trator", new BigDecimal("3199.99"), 2, new BigDecimal("6399.98"), cliente4);
        pedidos.add(pedido4);   

        Cliente cliente5 = new Cliente(5L, 1005L , "Joaquim Batista");
        Pedido pedido5 = new Pedido(null, "005", LocalDateTime.now(), "Geladeira", new BigDecimal("6199.99"), 1, new BigDecimal("6199.99"), cliente5);
        pedidos.add(pedido5);

        return pedidos; 
    }

}
