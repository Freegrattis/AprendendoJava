# Weather App

## Descrição

O **Weather App** é uma aplicação que permite aos usuários consultar as condições climáticas em qualquer lugar do mundo, utilizando coordenadas de latitude e longitude. O aplicativo se destaca por sua interface amigável e pela capacidade de armazenar o histórico de consultas para referência futura.

## Funcionalidades

- **Consulta de Clima**: Insira a latitude e longitude para obter informações meteorológicas atualizadas.
- **Histórico de Consultas**: Todas as consultas de clima são registradas e podem ser acessadas na seção de histórico, com um histórico individual e outro geral para visualização única.
- **Interface Intuitiva**: Desenvolvido com Thymeleaf e Tailwind CSS para uma experiência de usuário agradável e responsiva.
- **Feedback de Erros**: Mensagens de erro claras e concisas são exibidas quando a entrada do usuário é inválida ou quando ocorrem problemas ao consultar a API.

## Tecnologias Utilizadas

- **Java JDK 23**: A linguagem de programação principal utilizada para o backend.
- **Spring Boot**: Para o desenvolvimento do servidor e gerenciamento das rotas da aplicação.
- **Thymeleaf**: Para a renderização dos templates HTML.
- **Tailwind CSS**: Para o design responsivo e estilização da interface do usuário.
- **API de Clima**: Integração com uma API de clima OpenMeteo para buscar dados meteorológicos.

## Integração com a API

O Weather App utiliza uma API externa para obter dados meteorológicos. Os usuários inserem coordenadas de latitude e longitude, que são enviadas para a API. As respostas da API incluem informações como temperatura e horário, que são então exibidas ao usuário.

## Geração do Histórico de Consultas
As consultas de clima são armazenadas no backend para permitir que os usuários visualizem seu histórico. Cada vez que uma consulta é realizada, os detalhes da consulta, incluindo as coordenadas e a data/hora, são salvos em um banco de dados. Essa funcionalidade é implementada usando JPA (Java Persistence API) para gerenciar a persistência dos dados. 

## Como Rodar o Projeto Localmente
**Para iniciar o Weather App localmente, siga os passos abaixo:**

### Pré-requisitos
* Java JDK 23 instalado.
* IntelliJ IDEA ou qualquer outro IDE Java.
* Dependências do Maven.

### Passos

**Clone o Repositório**

```bash
git clone https://github.com/analiceleite/weather-app.git
cd weather-app
```

### Abra o Projeto no IntelliJ

Abra o IntelliJ IDEA e selecione "Open" para abrir o diretório do projeto.

### Configuração do Banco de Dados

Configure o banco de dados em application.properties ou application.yml com as credenciais corretas. Por exemplo:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/weather_app
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Instale as Dependências

Se estiver usando Maven, execute:

```bash
mvn install
```

Ou, se estiver usando Gradle:
```bash
./gradlew build
```

### Execute a Aplicação

No IntelliJ, localize a classe principal que contém o método main e execute-a.

### Acesse a Aplicação

Abra um navegador e vá até http://localhost:8080/weather para começar a usar o aplicativo.

### Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

### Licença
Este projeto é licenciado sob a MIT License - consulte o arquivo LICENSE para mais detalhes.

### Contato
Para mais informações, entre em contato comigo pelo e-mail analice.leite12@gmail.com.