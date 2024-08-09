package api.com.valadares.pedidos.services;

import api.com.valadares.pedidos.entity.Cliente;
import api.com.valadares.pedidos.entity.Pedido;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    
    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :codigoCliente")
    List<Pedido> obterPedidosEnviadosPeloCliente(Long codigoCliente);
    
    
    List<Pedido> findByNumeroControle(String numeroControle);

    List<Pedido> findByDataCadastro(LocalDateTime dataCadastro);

    List<Pedido> findByNumeroControleAndDataCadastro(String numeroControle, LocalDateTime dataCadastro);

    List<Pedido> findAll();
}