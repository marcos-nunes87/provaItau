# Projeto Prova Itaú
Este projeto foi desenvolvido como parte do processo de entrevista para programador no Itaú.

## Descrição do Projeto
O projeto consiste em dois endpoints:

/contas/transferencia (POST): Permite a transferência de fundos entre contas.
/contas/{agencia}/{conta}/saldo (GET): Retorna o saldo de uma conta específica, identificada pela agência e número da conta.
## Execução do Projeto
Após a execução do projeto, os endpoints estarão disponíveis no Swagger, acessível em: http://localhost:8080/swagger-ui.html

## Payloads para Validação do Serviço de Transferência
Para validar o serviço de transferência, podem ser utilizados os seguintes payloads:

### Cenário 1 - Conta Origem Inválida
```json
{
    "agenciaOrigem": 1,
    "contaOrigem": 7,
    "agenciaDestino": 2,
    "contaDestino": 1,
    "valor": 100
}
```
### Cenário 2 - Conta Destino Inválida
```json
{
    "agenciaOrigem": 1,
    "contaOrigem": 1,
    "agenciaDestino": 2,
    "contaDestino": 8,
    "valor": 100
}
```
### Cenário 3 - Valor Acima do Limite Disponível (Saldo + Limite)
```json
{
    "agenciaOrigem": 1,
    "contaOrigem": 2,
    "agenciaDestino": 1,
    "contaDestino": 1,
    "valor": 400
}
```
### Cenário 4 - Limite Diário de Transferência Excedido (Limite de R$1000)
```json
{
    "agenciaOrigem": 2,
    "contaOrigem": 2,
    "agenciaDestino": 1,
    "contaDestino": 1,
    "valor": 400
}
```
### Cenário 5 - Transferência Bem-sucedida
```json
{
    "agenciaOrigem": 1,
    "contaOrigem": 1,
    "agenciaDestino": 1,
    "contaDestino": 2,
    "valor": 200
}
```
## Arquitetura Proposta
A arquitetura proposta para a aplicação é ilustrada na imagem abaixo:
![arquitetura](https://github.com/marcos-nunes87/provaItau/assets/163775196/164a54ce-38d3-4ca6-9977-20b3ef628bad)

A arquitetura proposta para esta aplicação segue uma abordagem moderna e escalável, utilizando serviços gerenciados da AWS para garantir desempenho, escalabilidade e disponibilidade.

### Descrição da Arquitetura:

1. Cloudfare: Serve como o ponto de entrada da aplicação, fornecendo recursos de CDN (Content Delivery Network) e segurança de borda. Ele roteia as solicitações para o próximo componente da arquitetura.

2. API Gateway: Atua como o gateway de entrada para todas as solicitações HTTP, gerenciando o roteamento de solicitações para os serviços internos da aplicação.

3. Application Load Balancer (ALB): Distribui o tráfego de entrada entre as instâncias EC2 que executam a API da aplicação. Ele fornece balanceamento de carga de camada 7, permitindo roteamento com base em regras de roteamento avançadas.

4. Auto Scaling Group: Gerencia o dimensionamento automático das instâncias EC2 que hospedam a aplicação. Com base na carga de tráfego, o Auto Scaling Group adiciona ou remove instâncias EC2 conforme necessário para garantir que a aplicação tenha capacidade suficiente para lidar com a carga de trabalho.

5. EC2 Instances: São as instâncias de máquinas virtuais que executam a API da aplicação. Elas hospedam a lógica de negócios da aplicação e se conectam ao banco de dados Aurora para persistência de dados.

6. Banco de Dados Aurora: É um banco de dados relacional altamente escalável e totalmente gerenciado pela AWS. Ele oferece desempenho e disponibilidade excepcionais, garantindo que a aplicação possa escalar sem problemas à medida que cresce.

7. Solução de Monitoramento (APM, CloudWatch, etc.): Está conectada à API na instância EC2 para monitorar o desempenho e a integridade da aplicação em tempo real. Isso permite a detecção proativa de problemas e a análise de métricas para otimização contínua.

Essa arquitetura proporciona uma base sólida para uma aplicação altamente disponível, escalável e resiliente, permitindo que ela cresça conforme necessário e mantenha um alto nível de desempenho e confiabilidade.

Nota: Infelizmente, neste momento não foram incluídos o relatório Jacoco, um circuit breaker para a chamada da API do Banco Central ou um producer RabbitMQ para tentativa de retry para chamada do API do Banco Central.

Marcos Bezerra Nunes
