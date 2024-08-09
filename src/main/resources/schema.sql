SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";



 

CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo BIGINT,
    nome VARCHAR(255) NOT NULL
);


CREATE TABLE Pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_controle VARCHAR(255) NOT NULL,
    data_cadastro DATETIME NOT NULL,
    nome_produto VARCHAR(255) NOT NULL,
    valor_produto DECIMAL(19, 4) NOT NULL,
    quantidade_produto INT NOT NULL,
    valor_total DECIMAL(19, 4) NOT NULL,
    codigo_cliente BIGINT NOT NULL,
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (codigo_cliente) REFERENCES Cliente(codigo)
);


 