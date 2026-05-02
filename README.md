# ⛲ Parque Ubajara API - Catálogo de Turismo

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.0-6DB33F?style=for-the-badge&logo=spring-boot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![AWS](https://img.shields.io/badge/AWS_S3-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=json-web-tokens)

## 📌 Sobre o Projeto

A **Parque Ubajara API** é um sistema de backend desenvolvido para servir como um catálogo de turismo completo para o município de Ubajara-CE e região. O sistema centraliza informações sobre pontos turísticos, rotas e serviços locais, oferecendo uma infraestrutura robusta para aplicações de consulta turística.

O projeto já conta com aproximadamente 80% do backend concluído, cobrindo toda a estrutura de persistência, lógica de negócio e segurança.

## 🚀 Funcionalidades e Progresso

* **Segurança Avançada:** Implementação completa de autenticação e autorização utilizando **JSON Web Tokens (JWT)**.
* **Gestão de Mídia:** Integração planejada com **AWS S3** para o armazenamento escalável de fotos e documentos dos pontos turísticos.
* **Camada de Exceções:** Handler de exceções customizado para retornos de erro padronizados e amigáveis.
* **Cobertura de Domínio:** Models, Repositories, Services e DTOs já implementados para as principais entidades do catálogo.

## 🛠️ Tecnologias e Padrões

O desenvolvimento segue padrões rigorosos de engenharia de software para garantir manutenibilidade e performance:

* **Core:** Java 21 e Spring Boot 3.4.0.
* **Arquitetura:** Padrão MVC com foco em *Fat Services* e *Skinny Controllers*.
* **Persistência:** PostgreSQL.
* **Cloud:** AWS S3 para storage.

## ⚙️ Configuração Local

**Pré-requisitos:**
* Java 21.
* PostgreSQL.
* Credenciais AWS (para funcionalidades de storage).

**Execução:**
1. Clone o repositório.
2. Configure as variáveis de ambiente necessárias no `application.yml` (AWS Keys, Database URL, JWT Secret).
3. Execute o comando `docker-compose up -d` para subir o contêiner do banco de dados.
4. Execute via Maven: `mvn spring-boot:run`.


## 🤝 Metodologia

* **Gestão:** Organizado via **GitHub Projects** com metodologia **Kanban**.
* **Workflow:** Ciclos de desenvolvimento divididos em **Sprints**.
* **Versionamento:** Uso estrito de **Conventional Commits** em inglês.
