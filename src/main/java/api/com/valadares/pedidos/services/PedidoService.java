package api.com.valadares.pedidos.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.com.valadares.pedidos.entity.Cliente;
import api.com.valadares.pedidos.entity.Pedido;
import api.com.valadares.pedidos.exception.ExcecaoDeNegocio;
import api.com.valadares.pedidos.repository.ClienteRepository;
import api.com.valadares.pedidos.repository.PedidoRepository;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PedidoService{

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;


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
            if (pedido.getQuantidadeProduto() == 0) {
                pedido.setQuantidadeProduto(1);
            }
        });

        //5# Caso a quantidade seja maior que 5 aplicar 5% de desconto no valor total, 
        // para quantidades a partir de 10 aplicar 10% de desconto no valor total.
        //6# O sistema deve calcular e gravar o valor total do pedido.
        pedidos.forEach(pedido -> {

            BigDecimal quantidade = BigDecimal.valueOf(pedido.getQuantidadeProduto());

            if(pedido.getQuantidadeProduto() <= 5) {

                BigDecimal valorTotal = pedido.getValorProduto().multiply(quantidade);
                pedido.setValorTotal(valorTotal);

            }else if(pedido.getQuantidadeProduto() > 5 && pedido.getQuantidadeProduto() < 10) {
                
                BigDecimal DESCONTO = BigDecimal.valueOf(0.95);
                BigDecimal valor = pedido.getValorProduto().multiply(quantidade);
                BigDecimal valorTotal = valor.multiply(DESCONTO);
                pedido.setValorTotal(valorTotal);
            }else if (pedido.getQuantidadeProduto() >= 10) {

                BigDecimal DESCONTO = BigDecimal.valueOf(0.90);
                BigDecimal valor = pedido.getValorProduto().multiply(quantidade);
                BigDecimal valorTotal = valor.multiply(DESCONTO);
                pedido.setValorTotal(valorTotal);
            }

        });

        this.vincularClientesDosPedidos(pedidos);
        
        pedidoRepository.saveAll(pedidos);
        return pedidos;         
    }

    private void vincularClientesDosPedidos(List<Pedido> pedidos) {

        for (Pedido pedido : pedidos) {
            Long clienteId = pedido.getCliente().getId();
            Optional<Cliente> clienteOpt = this.clienteRepository.findById(clienteId);
            
            if (clienteOpt.isPresent()) {
                pedido.setCliente(clienteOpt.get());
            } else {
                throw new ExcecaoDeNegocio("Cliente com ID " + clienteId + " não encontrado.");
            }
        }
    }

    private Boolean validarSeExistemNumerosDeControlesCadastrados(List<Pedido> pedidos) {

        List<String> numerosDeControles = pedidos.stream()
                                            .map(Pedido::getNumeroControle)
                                            .collect(Collectors.toList());

        Boolean existe =  pedidoRepository.validarSeExistemNumerosDeControlesCadastrados(numerosDeControles);
        return existe;
    }

    private List<Pedido> obterPedidosClientesComNumerosDeControlesCadastrados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obterPedidosClientesComNumerosDeControlesCadastrados'");
    }


    public List<Pedido> obterPedidosEnviadosPeloCliente(Long codigoCliente) {
        
        List<Pedido> pedidos = this.pedidoRepository.obterPedidosEnviadosPeloCliente(codigoCliente);
        return pedidos;        
    }


    public List<Pedido> findByNumeroControle(String numeroControle){ 
        return this.pedidoRepository.findByNumeroControle(numeroControle);
    } 
    
    public List<Pedido> findByDataCadastro(LocalDateTime dataCadastro){
        return pedidoRepository.findByDataCadastro(dataCadastro);
    }

    public List<Pedido> findByNumeroControleAndDataCadastro(String numeroControle, LocalDateTime dataCadastro){
        return pedidoRepository.findByNumeroControleAndDataCadastro(numeroControle, dataCadastro);
    }

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

}
