package api.com.valadares.pedidos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido{
    
    private String numeroControle;
    private LocalDateTime dataCadastro;
    private String nomeProduto;
    private BigDecimal valorProduto;
    private Integer quantidadeProduto;
    private Long codigoCliente; 
}
