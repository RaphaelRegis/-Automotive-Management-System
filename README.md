<h1 align="center" id="title">Automotive Management System</h1>

<p align="center"><img src="https://www.getac.com/content/dam/uploads/2023/03/autorepairworkshop_cover.png" alt="project-image"></p>

<p id="description">Project that performs CRUD operations in an automotive store management system.</p>

  
  
<h2>💻 Built with</h2>

*   Java
*   Spring Boot
*   Maven
*   H2 memory database

  <h2>MODULES</h2>

*   Funcionário
*   Equipe
*   Produto
*   Serviço
*   Cliente
*   Veículo
*   Orçamento
  
    <h2>BUSINESS RULES</h2>

FUNCIONARIO E EQUIPE:
- Funcionario só é excluido se não for líder de nenhuma equipe;
- Funcionario só é excluido se não fizer parte de nenhuma equipe;
- Equipe só é excluida caso não for responsavel por nenhum Servico_Orcamento;

PRODUTO E SERVICO:
- Excluiu Produto, excluiu seus Produtos_Orcamentos;
- Excluiu Servico, excluiu seus Servicos_Orcamentos;

CLIENTE E VEICULO:
- Excluiu Cliente, excluiu seus veículos;
- Veículo só poderá ser excluido caso não tenha nenhum Orcamento;

ORCAMENTO:
- Excluiu Orcamento, excluiu seus Produto_Orcamento e Servico_Orcamento;

SEQUENCIA DE PASSOS:
- Adicionar Funcionario ("/api/funcionarios");
- Adicionar Equipe ("/api/equipes");
- Adicionar Produto ("/api/produtos");
- Adicionar Servico ("/api/servicos");
- Adicionar Cliente ("/api/clientes");
- Adicionar Veiculo ("/api/veiculos");
- Adicionar Orcamento ("/api/orcamentos");
- Orcar Servico ("/api/orcarServico");
- Orcar Produto ("/api/orcarProduto");
- Verificar preço do orçamento("/totalOrcamento/{idOrcamento}");
