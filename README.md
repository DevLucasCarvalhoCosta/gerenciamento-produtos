# Sistema de Gerenciamento de Usuários e Produtos

## Descrição do Projeto
Este projeto é um sistema desenvolvido para gerenciar usuários e produtos, com funcionalidades completas para cadastro, atualização, remoção e listagem. Ele implementa regras de validação para garantir a consistência dos dados e atender aos requisitos de negócio.

## Funcionalidades

### Gerenciamento de Usuários
- **Cadastro de Usuários:** 
  - Login e senha devem ser strings.
  - Regras de validação para a senha:
    - Pelo menos 4 caracteres.
    - Não ser igual ao login.
    - Não conter espaços em branco no início ou no final.
  - Caso as regras não sejam atendidas, uma exceção `IllegalArgumentException` será lançada.
- **Remoção de Usuários:** Remoção com base no login.
- **Atualização de Senha:** Validação das regras ao atualizar a senha.
- **Evitar Duplicidade:** Controle de unicidade para o login dos usuários.
- **Listagem de Usuários:** Retorno de todos os usuários cadastrados.

### Gerenciamento de Produtos
- **Cadastro de Produtos:** 
  - Validações:
    - Código único.
    - Nome não pode ser nulo ou vazio.
    - Descrição com no máximo 200 caracteres.
    - Preço deve ser maior que zero.
  - Exceções do tipo `IllegalArgumentException` são lançadas em casos de falha nas validações.
- **Remoção de Produtos:** Com base no código.
- **Atualização de Produtos:** Validações aplicadas durante a atualização (nome, descrição e preço).
- **Listagem de Produtos:** Retorno de todos os produtos cadastrados.

## Diagramas

### Diagrama de Caso de Uso
![Diagrama de Caso de Uso](./Diagramas/diagrama_casos_de_uso.png)

### Diagrama de Classes
![Diagrama de Classes](./Diagramas/Diagrama_de_Classes.png)

## Casos de Teste
O sistema foi extensivamente testado com o framework JUnit 5. Os testes incluem:
- **Usuários:**
  - Adicionar usuários válidos.
  - Validar senhas.
  - Verificar duplicidade de login.
  - Atualizar senhas.
  - Remover usuários.
  - Listar todos os usuários.
- **Produtos:**
  - Adicionar produtos com dados válidos.
  - Garantir unicidade de código.
  - Atualizar informações de produtos.
  - Remover produtos.
  - Listar todos os produtos.

## Tecnologias Utilizadas
- **Linguagem:** Java
- **Framework:** Spring Boot
- **Testes:** JUnit 5
- **IDE:** Visual Studio Code

## Como Executar o Projeto
1. Clone o repositório:
   ```bash
   git clone https://github.com/DevLucasCarvalhoCosta/gerenciamento-produtos.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd gerenciamento-produtos
   ```
3. Execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```
