# HospitalSystem

Sistema de gerenciamento de estoque hospitalar desenvolvido em Java, com integração ao MySQL e Power BI.

## Tecnologias
- Java 21
- MySQL 8.0
- JDBC
- Java Swing
- Power BI

## Funcionalidades
- Cadastro e remoção de caixas de medicamentos.
- Busca de medicamentos por ID.
- Controle de estoque por setor.
- Validação de cadastros duplicados.
- Alertas de medicamentos vencidos ou próximos do vencimento.
- Registro automático de movimentações no banco de dados.
- Dashboard com indicadores de estoque.

## Estruturas de Dados
- Pilha (LIFO) para gerenciamento das caixas.
- Vetores para organização dos setores.
- Algoritmo de reempilhamento para remoção de elementos específicos.

## Banco de Dados e BI
O sistema utiliza MySQL para registrar as movimentações de medicamentos e Power BI para visualizar indicadores e acompanhar informações do estoque.

## Melhorias Futuras
- Persistência do estoque atual.
- Implementação da lógica FEFO.
- Sistema de autenticação.
- Cadastro dinâmico de setores.

## Objetivo
Aplicar conceitos de programação, estruturas de dados, banco de dados e análise de informações em uma solução voltada à gestão hospitalar.
