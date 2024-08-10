package api.com.valadares.pedidos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;




@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "pedido") 
public class Pedido{
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_controle", nullable = false)
    private String numeroControle;
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;
    @Column(name = "nome_produto", nullable = false)
    private String nomeProduto;
    @Column(name = "valor_produto", nullable = false)
    private BigDecimal valorProduto;
    @Column(name = "quantidade_produto", nullable = false)
    private Integer quantidadeProduto;
    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;
    // private Long codigoCliente; 

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

}
