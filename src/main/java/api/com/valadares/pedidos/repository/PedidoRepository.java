package api.com.valadares.pedidos.repository;

import api.com.valadares.pedidos.entity.Cliente;
import api.com.valadares.pedidos.entity.Pedido;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    
    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :codigoCliente")
    List<Pedido> obterPedidosEnviadosPeloCliente(Long codigoCliente);
    
    @Query("SELECT COUNT(p) > 0 FROM Pedido p WHERE p.numeroControle IN :numerosDeControles")
    Boolean validarSeExistemNumerosDeControlesCadastrados(@Param("numerosDeControles") List<String> lista);
    
    
    List<Pedido> findByNumeroControle(String numeroControle);

    List<Pedido> findByDataCadastro(LocalDateTime dataCadastro);

    List<Pedido> findByNumeroControleAndDataCadastro(String numeroControle, LocalDateTime dataCadastro);

    List<Pedido> findAll();

}