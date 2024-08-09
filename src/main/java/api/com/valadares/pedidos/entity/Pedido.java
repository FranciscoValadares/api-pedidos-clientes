package api.com.valadares.pedidos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String numeroControle;
    private LocalDateTime dataCadastro;
    private String nomeProduto;
    private BigDecimal valorProduto;
    private Integer quantidadeProduto;
    private BigDecimal valorTotal;
    // private Long codigoCliente; 

    @ManyToOne
    @JoinColumn(name = "codigo_cliente", nullable = false)
    private Cliente cliente;

}
