# :dart: <center> PrognostiCare </center>

Bem-vindo ao repositório do Projeto de Trabalho de Conclusão de Semestre (TCS) da 5º fase do curso de Análise e Desenvolvimento de Sistemas da Faculdade SENAC. Neste projeto, estamos desenvolvendo o aplicativo PrognostiCare, uma ferramenta inovadora que visa auxiliar os usuários a monitorar e prever sua saúde de maneira eficiente e conveniente.

# :memo: Descrição do Projeto

O aplicativo Prognóstico de Saúde tem como objetivo proporcionar aos usuários uma maneira inteligente e personalizada de acompanhar seu bem-estar e prever possíveis condições de saúde. Combinando tecnologias de ponta, como aprendizado de máquina e análise de dados, nosso aplicativo busca oferecer insights valiosos para promover um estilo de vida saudável e prevenir problemas de saúde.

# 🛠 Ferramentas e Tecnologias utilizadas
<p align="left">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,spring,vscode,postgres,docker,nginx,flutter" />
  </a>
</p>
 
# :exclamation: Pré-requisitos

Para poder iniciar o projeto verifique se sua máquina tem os seguintes programas instalados:

- [X] JDK 17 ou JRE
- [X] Editor de Texto VSCode ou Eclipse IDE
- [X] PostgreSQL
- [ ] Docker (Opcional)

##  :zap: Executando o Projeto

```bash
# 1º Clone o repositório
$ git clone https://github.com/AlissonFerreiraEvangelista/PrognostiCare.git
# 2º Acesse a pasta do projeto
$ cd /PrognostiCare
# Acesse o package default e inicie o arquivo Application.java

Aplicação esta rodando na porta padrão 8080, acesse localhost:8080
```
## :no_entry_sign: Lembrete
Configure o arquivo application.properties com base nas informações abaixo.<br> 
<a href="https://support.google.com/accounts/answer/185833?hl=pt-BR">Criar uma senha app Google</a> <br>
<a href="https://support.google.com/a/answer/176600?hl=pt#:~:text=filtrar%20mensagens%20suspeitas.-,O%20nome%20de%20dom%C3%ADnio%20totalmente%20qualificado%20do%20servi%C3%A7o%20SMTP%20%C3%A9,Protocolos%20SSL%20e%20TLS"> SMTP Google</a> <br>
<a href="https://cloud.google.com/?hl=pt-br">Credencial Google OAUTH2</a>
```bash

#Spring Boot server configuration
server.port=8080
server.error.include-stacktrace=never

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults = false
spring.jpa.show-sql=true

# DataSource
spring.datasource.url= 'INFORME O CAMINHO DO SEU BANCO DE DADOS'
spring.datasource.username= 'INFORME O USER DO SEU BANCO DE DADOS'
spring.datasource.password= 'INFORME A SENHA DO SEU BANCO DE DADOS'

#FlyWay
# spring.flyway.enabled=true
# spring.flyway.baseline-on-migrate=true

TOKEN_SECRET= 'CRIE UM TOKEN'

#Google OATH2
spring.security.oauth2.client.registration.google.client-id='CLIENT ID'
spring.security.oauth2.client.registration.google.client-secret='CLIENT_SECRET'
spring.security.oauth2.resourceserver.jwt.issuer-uri='CLIENT_URI'

#Mail
spring.mail.host='endereço de e-mail onde irá fazer o envio de e-mail'
spring.mail.port='porta do SMTP para a comunica de envio de e-mails'
spring.mail.username='EMAIL CADASTRADO'
spring.mail.password='SENHA APP'
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

```

# Endpoints
### Swagger
http://localhost:8080/swagger-ui.html


# 🍻Contato

Se você tiver alguma dúvida, sugestão ou feedback, sinta-se à vontade para entrar em contato com nossa equipe:

- [Contato do Alisson](https://github.com/usuario-alisson) 😊
- [Contato do Pedro Prazeres](https://github.com/PedroPrazz) 🚀
- [Contato do Pedro Paulo](https://github.com/usuario-pedropaulo) 🎉
- [Contato do Mauricio](https://github.com/MauricioDevJS) 🌟
- [Contato da Lauren](https://github.com/lauured) 📧

Agradecemos por seu interesse em nosso projeto!
