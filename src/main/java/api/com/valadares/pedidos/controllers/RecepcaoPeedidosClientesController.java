package api.com.valadares.pedidos.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.com.valadares.pedidos.entity.Pedido;
import api.com.valadares.pedidos.services.PedidoService;

@RestController
@RequestMapping("/")
public class RecepcaoPeedidosClientesController {
    

    // A anotação @RequestBody permite que o  Spring Boot deserializa entradas em formatos JSON ou XML.
    @RequestMapping(value = "/api/criar-pedidos-clientes", method = RequestMethod.POST)
    public ResponseEntity<List<Pedido>> criarPedidosClientes(@RequestBody List<Pedido> pedidos) throws IOException {

        PedidoService pedidoService = new PedidoService(
                // this.originalPostsRepository, 
                // this.repostRepository, 
                // this.quotePostingRepository, 
                // this.usersRepository
        );

        List<Pedido> pedidosCriados  = pedidoService.criarPedidosClientes(pedidos);
        return ResponseEntity.ok(pedidosCriados);
    }
}
