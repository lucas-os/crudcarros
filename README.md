🚗 Sistema de Cadastro de Proprietários e Carros

Um sistema Full Stack completo desenvolvido com Java Spring Boot, React.js e MySQL, permitindo o cadastro, edição, listagem e associação entre proprietários e seus veículos.
O projeto foi desenvolvido como parte dos meus estudos em desenvolvimento back-end com Java e Spring Boot, e front-end com React, unindo todos os conhecimentos em uma aplicação integrada, responsiva e hospedada na nuvem.

🧠 Visão Geral

Este sistema simula o gerenciamento de uma oficina, onde é possível:

• Cadastrar proprietários e seus respectivos carros

• Consultar, editar e remover registros

• Filtrar carros dinamicamente por placa, CPF do Proprietário, marca, modelo, cor e ano, inclusive em combinações múltiplas de filtros

• Validar automaticamente dados como CPF, placa, email, idade e telefone

• Exibir mensagens de erro personalizadas no frontend com base nas respostas do backend

A aplicação segue o modelo RESTful, separando as responsabilidades entre cliente (React) e servidor (Spring Boot).

⚙️ Stack Utilizada
🖥️ Backend

Java 17

Spring Boot 3 (Spring Web, Spring Data JPA, Validation)

Hibernate & JPA

MySQL

Exception Handler personalizado

API RESTful

💻 Frontend

React.js (com Hooks e React Router)

Axios para integração com a API

Tailwind CSS para design moderno e responsivo

React-IMask para formatação de campos como CPF

☁️ Infra e Deploy

Backend: Railway

Frontend: Vercel

Controle de versão: Git + GitHub

🧩 Funcionalidades Principais
👤 Proprietários

Cadastro, edição, listagem e exclusão

Busca direta por CPF

Validações automáticas (nome, idade, CPF, telefone, email)

Tratamento de erros no backend com retorno ao frontend

🚘 Carros

Cadastro, edição, listagem e exclusão

Associação direta com o CPF do proprietário

Filtros avançados por placa, CPF do Proprietário, marca, modelo, cor e ano, podendo ser combinados

Validação de placa única

Exibição de mensagens personalizadas em casos de erro, como:

“Já existe um carro com essa placa”

“Não tem nenhum proprietário com esse CPF”

🚀 Deploy Online

🌍 Frontend (Vercel): https://crudcarros.vercel.app/
☁️ Backend (Railway)

🏁 Conclusão

Esse projeto representa uma grande conquista no meu aprendizado com Spring Boot e React, consolidando minha experiência prática com APIs REST, integração frontend-backend e deploy em nuvem.

🚀 Estou em constante evolução, aprimorando minhas habilidades técnicas e teóricas para ingressar como desenvolvedor back-end Java.
