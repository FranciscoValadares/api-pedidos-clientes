# Francisco Valadares

## Visão Geral

"Criar uma solução java em formato de API que atenda aos seguintes requisitos para a recepção de pedidos dos clientes:."
<br/>

✅ 

## Instructions to run the project docker-compose

```console
$ docker-compose -f docker-compose.desenvolvimento.yml --build
$ docker-compose -f docker-compose.desenvolvimento.yml up -d
```
ou 

```console
$ docker-compose -f docker-compose.desenvolvimento.yml up --build -d
```

<br/>
✅ 


## Requisitos da API


```
Para realizar testes com o arquivo(RESTClient_TesteController.http) é necessário instalar o plugin REST Client do código do visual studio"
```

A solução é em java em formato de API e atende aos seguintes requisitos para a recepção de pedidos dos clientes:


### <b>Necessidades para criar os pedidos</b>
<li> Criar um serviço que receba pedidos no formato xml e json com 6 campos:<p>
número controle - número aleatório informado pelo cliente.<p>
data cadastro (opcional) <p>
nome - nome do produto<p>
valor - valor monetário unitário produto<p>
quantidade (opcional) - quantidade de produtos.<p>
codigo cliente - identificação numérica do cliente.
  <br/>
  <br/>
  <p>
  <b>Esses requisitos estão presentes em :</b> 

[criarPedidosClientes(@RequestBody List<Pedido> pedidos)](./src/main/java/api/com/valadares/pedidos/controllers/RecepcaoPedidosClientesController.java)

 <br/>


 ### <b>Critérios aceitação e manipulação do arquivo:</b>
<li> O arquivo pode conter 1 ou mais pedidos, limitado a 10.<p>
Não poderá aceitar um número de controle já cadastrado.<p>
Caso a data de cadastro não seja enviada o sistema deve assumir a data atual.<p>
Caso a quantidade não seja enviada considerar 1.<p>
Caso a quantidade seja maior que 5 aplicar 5% de desconto no valor total, para quantidades a partir de 10 aplicar
10% de desconto no valor total. <p>
O sistema deve calcular e gravar o valor total do pedido.
  <br/>
  <br/>
  <p>
  <b>Esses requisitos estão presentes em:</b> 

[public List<Pedido> criarPedidosClientes(List<Pedido> pedidos)](./src/main/java/api/com/valadares/pedidos/services/PedidoService.java#criarPedidosClientes)

 <br/>


<li>Criar um serviço onde possa consultar os pedidos enviados pelos clientes.
Critérios aceitação:<p>
O retorno deve trazer todos os dados do pedido.<p>
  <b>Esses requisitos estão presentes em:</b>
  
[public List<Pedido> obterPedidosEnviadosPeloCliente(Long codigoCliente)](./src/main/java/api/com/valadares/pedidos/services/PedidoService.java#obterPedidosEnviadosPeloCliente) 

<br/>

 

<li>O script de criação das tabelas esta presentes em: 

[Script criacao das tabelas](./src/main/resources/db/migration/V1__Initial_setup.sql)