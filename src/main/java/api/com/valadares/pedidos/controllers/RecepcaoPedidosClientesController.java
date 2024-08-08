package api.com.valadares.pedidos.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.com.valadares.pedidos.entity.Pedido;
import api.com.valadares.pedidos.services.PedidoService;

@RestController
public class RecepcaoPedidosClientesController {

    private final PedidoService pedidoService;

    @Autowired
    public RecepcaoPedidosClientesController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    

    // A anotação @RequestBody permite entradas em formatos JSON ou XML.
    @RequestMapping(value = "/api/criar-pedidos-clientes", method = RequestMethod.POST)
    public ResponseEntity<List<Pedido>> criarPedidosClientes(@RequestBody List<Pedido> pedidos) throws IOException {
        List<Pedido> pedidosCriados  = pedidoService.criarPedidosClientes(pedidos);
        return ResponseEntity.ok(pedidosCriados);
    }

    // gerar method get 
    @RequestMapping(value = "/api/pedidos-clientes", method = RequestMethod.GET)
    public List<Pedido> getPedidos() {

        return pedidoService.getPedidosClientes();

    }


}
