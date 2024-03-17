INSERT INTO tb_clientes(nome)VALUES('Marcos');
INSERT INTO tb_clientes(nome)VALUES('Tiago');
INSERT INTO tb_clientes(nome)VALUES('Andre');

INSERT INTO tb_contas(cliente_id,agencia,conta,saldo,limite,ativo)VALUES(1,1,1,5100,12000,true);
INSERT INTO tb_contas(cliente_id,agencia,conta,saldo,limite,ativo)VALUES(2,1,2,200,100,true);
INSERT INTO tb_contas(cliente_id,agencia,conta,saldo,limite,ativo)VALUES(3,2,1,6000,5000,false);
INSERT INTO tb_contas(cliente_id,agencia,conta,saldo,limite,ativo)VALUES(3,2,2,3000,2000,true);

INSERT INTO tb_transacoes(agencia_origem,conta_origem,agencia_destino,conta_destino,valor,data)VALUES(2,2,1,1,700,now());
