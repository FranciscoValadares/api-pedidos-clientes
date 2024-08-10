package api.com.valadares.pedidos.controllers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import api.com.valadares.pedidos.entity.Cliente;
import api.com.valadares.pedidos.entity.Pedido;
import api.com.valadares.pedidos.services.PedidoService;

@SpringBootTest
public class RecepcaoPeedidosClientesControllerTest {

    @Autowired
    private RecepcaoPedidosClientesController pedidosClientesController;
    @Autowired
    private PedidoService pedidoService ;


    @Test
    void contextLoads() throws Exception {
      assertThat(pedidosClientesController).isNotNull();
    }

    @BeforeEach
    public void setUp() {
      this.pedidoService = new PedidoService();
      this.pedidosClientesController = new RecepcaoPedidosClientesController(pedidoService);
    }


    @Test
    public void testGetPedidosClientesMokList() {
      List<Pedido> pedidos = this.getMockedClientePedidosList();
      assertThat(pedidos).isNotNull();
    }


    
    @Test
    public void givenInvalidPedidosWhenStoringShouldReturnErrorResponse() {
      // TODO implementar lógica de testes
    }
    
    @Test
    public void givenPedidosValidosWhenStoringShouldReturnSuccessResponse() throws IOException {

      List<Pedido> pedidos = this.getMockedClientePedidosList();
      when(this.pedidosClientesController.criarPedidosClientes(pedidos)).thenReturn(ResponseEntity.ok(pedidos));
      
      ResponseEntity<List<Pedido>> response = this.pedidosClientesController.criarPedidosClientes(pedidos);

      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      assertThat(response.getBody()).isNotNull();
      assertThat(response.getBody()).isEqualTo(pedidos);

    }
  
    public List<Pedido> getMockedClientePedidosList() {

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

