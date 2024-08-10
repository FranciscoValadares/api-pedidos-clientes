package api.com.valadares.pedidos.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.com.valadares.pedidos.entity.Cliente;
import api.com.valadares.pedidos.entity.Pedido;
import api.com.valadares.pedidos.services.PedidoService;

@RestController
@RequestMapping("/api")
public class RecepcaoPedidosClientesController {

    private final PedidoService pedidoService;

    @Autowired
    public RecepcaoPedidosClientesController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    
    
    /**
     * Cria pedidos considerando as regras de negócio:
     * Lista de pedidos pode conter 1 ou mais pedidos, limitado a 10.
     * Cada pedido tem que ter pelo menos um item, limitado a 10.
     * Caso a data de cadastro não seja enviada o sistema deve assumir a data atual.
     * Caso o cliente não seja informado o sistema deve assumir o cliente 1.
     * Caso a quantidade não seja enviada considerar 1.
     * Caso a quantidade seja maior que 5 aplicar 5% de desconto no valor total, para quantidades a partir de
     * 10 aplicar 10% de desconto no valor total.
     * O sistema deve calcular e gravar o valor total do pedido.
     * Assumir que há 10 clientes cadastrados, com códigos de 1 a 10.
     * 
     * A anotação @RequestBody permite entradas em formatos JSON ou XML.
     * @param List<Pedido> pedidos
     * @return
     * @throws IOException
     */
    @PostMapping("/criar-pedidos-clientes")
    public ResponseEntity<?> criarPedidosClientes(@RequestBody List<Pedido> pedidos) throws IOException {

        try {
            List<Pedido> pedidosCriados  = pedidoService.criarPedidosClientes(pedidos);
            return ResponseEntity.ok(pedidosCriados);    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO_AO_CRIAR_PEDIDOS_CLIENTES:" + e.getMessage());
        }
        
    }

    /***
     * Obtem os pedidos enviados pelo cliente a partir do código do cliente.
     * @param codigoCliente
     * @return
     * @throws IOException
     */
    @GetMapping("/pedidos-enviados-pelo-cliente")
    public ResponseEntity<?> getPedidosEnviadosPeloCliente(@RequestBody Long codigoCliente) throws IOException {
        
        try {
            List<Pedido> pedidos = pedidoService.obterPedidosEnviadosPeloCliente(codigoCliente);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO_AO_CONSULTAR_CLIENTES:" + e.getMessage());
        }
        
    }

    /**
     * Metodo auxiliar para fazer Mock dos pedidos.
     * @return
     */
    @GetMapping("/pedidos-clientes")
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
