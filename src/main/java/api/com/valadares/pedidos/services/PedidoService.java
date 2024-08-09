package api.com.valadares.pedidos.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import api.com.valadares.pedidos.entity.Pedido;
import api.com.valadares.pedidos.exception.ExcecaoDeNegocio;

@Service
public class PedidoService {

    /**
     * Cria pedidos considerando as regras de negócio:
     * 
     *   1# O arquivo pode conter 1 ou mais pedidos, limitado a 10. 
     *   2# Não poderá aceitar um número de controle já cadastrado. 
     *   3# Caso a data de cadastro não seja enviada o sistema deve assumir a data atual.
     *   4# Caso a quantidade não seja enviada considerar 1.
     *   5# Caso a quantidade seja maior que 5 aplicar 5% de desconto no valor total, para quantidades a partir de 
     *      10 aplicar 10% de desconto no valor total.
     *   6# O sistema deve calcular e gravar o valor total do pedido.
     *   7# Assumir que já existe 10 clientes cadastrados, com códigos de 1 a 10. 
     * @param pedidos
     * @return List<Pedido>
     */
    public List<Pedido> criarPedidosClientes(List<Pedido> pedidos) {

        //1# O arquivo pode conter 1 ou mais pedidos, limitado a 10. 
        if (pedidos == null || pedidos.size() == 0 || pedidos.size() > 10) {
            throw new ExcecaoDeNegocio("O arquivo pode conter 1 ou mais pedidos, limitado a 10.");
        }

        //2# Não poderá aceitar um número de controle já cadastrado.
        Boolean existemNumerosDeControlesCadastrados = this.validarSeExistemNumerosDeControlesCadastrados(pedidos);
        if (existemNumerosDeControlesCadastrados) {
            throw new ExcecaoDeNegocio("Para cadastrar os pedidos não pode haver número de controle já cadastrado.");
        }


        //3# Caso a data de cadastro não seja enviada o sistema deve assumir a data atual.
        pedidos.forEach(pedido -> {
            if (pedido.getDataCadastro() == null) {
                pedido.setDataCadastro(LocalDateTime.now());
            }
        });
        

        //4# Caso a quantidade não seja enviada considerar 1.
        pedidos.forEach(pedido -> {
            if (pedido.getQuantidadeProduto() == null) {
                pedido.setQuantidadeProduto(1);
            }
        });

        //5# Caso a quantidade seja maior que 5 aplicar 5% de desconto no valor total, 
        // para quantidades a partir de 10 aplicar 10% de desconto no valor total.
        //6# O sistema deve calcular e gravar o valor total do pedido.
        pedidos.forEach(pedido -> {

            BigDecimal quantidade = BigDecimal.valueOf(pedido.getQuantidadeProduto());

            if (pedido.getQuantidadeProduto() > 5 && pedido.getQuantidadeProduto() < 10) {
                
                BigDecimal DESCONTO = BigDecimal.valueOf(0.95);
                BigDecimal valor = pedido.getValorProduto().multiply(quantidade);
                BigDecimal valorTotal = valor.multiply(DESCONTO);
                pedido.setValorTotal(valorTotal);
            }

            if (pedido.getQuantidadeProduto() >= 10) {

                BigDecimal DESCONTO = BigDecimal.valueOf(0.90);
                BigDecimal valor = pedido.getValorProduto().multiply(quantidade);
                BigDecimal valorTotal = valor.multiply(DESCONTO);
                pedido.setValorTotal(valorTotal);
            }

        });


        List<Pedido> pedidosComNumerosDeControlesCadastrados = this.obterPedidosClientesComNumerosDeControlesCadastrados();
        //Não poderá aceitar um número de controle já cadastrado.
        //List<Pedido> pedidosComNumerosDeControlesNovos = this.obterPedidosClientesComNumerosDeControlesCadastrados();


        return pedidos;
         
    }

    private Boolean validarSeExistemNumerosDeControlesCadastrados(List<Pedido> pedidos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validarSeExistemNumerosDeControlesCadastrados'");
    }

    private List<Pedido> obterPedidosClientesComNumerosDeControlesCadastrados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterPedidosClientesComNumerosDeControlesCadastrados'");
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
