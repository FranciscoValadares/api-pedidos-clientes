
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo BIGINT,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numeroControle VARCHAR(255) NOT NULL,
    dataCadastro DATETIME NOT NULL,
    nomeProduto VARCHAR(255) NOT NULL,
    valorProduto DECIMAL(10, 2) NOT NULL,
    quantidadeProduto INT NOT NULL,
    valorTotal DECIMAL(10, 2) NOT NULL,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);


