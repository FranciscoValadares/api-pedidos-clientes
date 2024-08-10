package api.com.valadares.pedidos.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespostaDeErro{
    private String codigoErro;
    private String mensagemDeErro;
    
}


