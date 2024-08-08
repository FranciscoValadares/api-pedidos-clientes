package api.com.valadares.pedidos.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import api.com.valadares.pedidos.entity.Pedido;

@Service
public class PedidoService {

    public List<Pedido> criarPedidosClientes(List<Pedido> pedidos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criarPedidosClientes'");
    }

    public List<Pedido> getPedidosClientes() {
        

        List<Pedido> pedidos = new ArrayList<>();
        
        pedidos.add(new Pedido("001", LocalDateTime.now(), "Cerveja", null, 10, 1L));
        pedidos.add(new Pedido("002", LocalDateTime.now(), "Coca Cola", null, 10, 1L));
        pedidos.add(new Pedido("003", LocalDateTime.now(), "Refrigerante", null, 10, 1L));
        pedidos.add(new Pedido("004", LocalDateTime.now(), "Agua", null, 10, 1L));
        pedidos.add(new Pedido("005", LocalDateTime.now(), "Suco", null, 10, 1L));
        pedidos.add(new Pedido("006", LocalDateTime.now(), "Cerveja", null, 10, 1L));
        pedidos.add(new Pedido("007", LocalDateTime.now(), "Coca Cola", null, 10, 1L));
        pedidos.add(new Pedido("008", LocalDateTime.now(), "Refrigerante", null, 10, 1L));
        pedidos.add(new Pedido("009", LocalDateTime.now(), "Agua", null, 10, 1L));
        pedidos.add(new Pedido("010", LocalDateTime.now(), "Suco", null, 10, 1L));
        
        return pedidos;
    }

}
