CREATE TABLE IF NOT EXISTS SIMULACOES (
  id           INT PRIMARY KEY AUTO_INCREMENT,
  simulacao_id VARCHAR(36) AUTO_INCREMENT,
  cpf          VARCHAR(14) NOT NULL,
  convenio     VARCHAR(255),
  data_simulacao DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  valor_solicitado DOUBLE NOT NULL,
  taxa_aplicada DOUBLE,
  quantidade_parcelas INTEGER,
  valor_parcela DOUBLE,
  valor_total_pagamento DOUBLE
);