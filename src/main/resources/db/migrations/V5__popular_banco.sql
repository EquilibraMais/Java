-- Inserir empresas
BEGIN
  InsertEmpresa('Google');
  InsertEmpresa('Facebook');
  InsertEmpresa('Bayer');
  InsertEmpresa('Eurofarma');
  InsertEmpresa('Nestle');
  InsertEmpresa('Viveo');
  InsertEmpresa('Italac');
  InsertEmpresa('Gilette');
  InsertEmpresa('Motorolla');
  InsertEmpresa('Samsung');
END;
/

-- Inserir setores
BEGIN
  InsertSetor('Recursos Humanos', 1);
  InsertSetor('Tecnologia', 1);
  InsertSetor('Financeiro', 2);
  InsertSetor('Marketing', 3);
  InsertSetor('Vendas', 2);
  InsertSetor('Pesquisa e Desenvolvimento', 4);
  InsertSetor('Suporte Técnico', 5);
  InsertSetor('Data Science', 6);
  InsertSetor('Consultoria', 8);
  InsertSetor('Operações', 9);
END;
/

-- Inserir usuários
BEGIN
  InsertUsuario('João Silva', 'Analista', '$2a$10$cWmYOtcwKUpOu2xNwMdX.ODgeUNd4bCBrNeMLDJYdTlXWeOoO.AS2', 1);
  InsertUsuario('Maria Oliveira', 'Desenvolvedora', 'maria123', 2);
  InsertUsuario('Carlos Santos', 'Contador', 'carlos123', 3);
  InsertUsuario('Ana Lima', 'Especialista em Marketing', 'ana123', 4);
  InsertUsuario('Fernanda Costa', 'Vendedora', 'fernanda123', 5);
  InsertUsuario('Roberto Souza', 'Pesquisador', 'roberto123', 6);
  InsertUsuario('Patricia Martins', 'Técnico de Suporte', 'patricia123', 7);
  InsertUsuario('Lucas Almeida', 'Cientista de Dados', 'lucas123', 8);
  InsertUsuario('Camila Pereira', 'Consultora', 'camila123', 9);
  InsertUsuario('Eduardo Ferreira', 'Gerente de Operações', 'eduardo123', 10);
END;
/

-- Inserir informações de funcionários 
BEGIN
  InsertFuncionarioInfo(TIMESTAMP '2025-11-01 08:00:00', 3, 4, 5, 3, 'Rotina normal', 'Nenhum histórico', 1);
  InsertFuncionarioInfo(TIMESTAMP '2025-11-02 09:15:00', 4, 3, 4, 4, 'Projeto importante em andamento', 'Nenhum histórico', 2);
  InsertFuncionarioInfo(TIMESTAMP '2025-11-03 10:30:00', 2, 2, 3, 2, 'Cansaço físico', 'Asma leve', 3);
  InsertFuncionarioInfo(TIMESTAMP '2025-11-04 11:45:00', 5, 5, 5, 5, 'Excelente desempenho recente', 'Nenhum histórico', 4);
  InsertFuncionarioInfo(TIMESTAMP '2025-11-05 08:20:00', 3, 4, 3, 3, 'Horário flexível', 'Diabetes tipo 2', 5);
  InsertFuncionarioInfo(TIMESTAMP '2025-11-06 09:10:00', 4, 4, 4, 4, 'Entusiasmo com pesquisa nova', 'Nenhum histórico', 6);
  InsertFuncionarioInfo(TIMESTAMP '2025-11-07 08:50:00', 3, 3, 3, 3, 'Suporte técnico intenso', 'Hipertensão controlada', 7);
  InsertFuncionarioInfo(TIMESTAMP '2025-11-08 10:00:00', 5, 5, 4, 5, 'Alta carga de trabalho', 'Nenhum histórico', 8);
  InsertFuncionarioInfo(TIMESTAMP '2025-11-09 11:30:00', 4, 3, 5, 4, 'Ajustes em consultoria', 'Nenhum histórico', 9);
  InsertFuncionarioInfo(TIMESTAMP '2025-11-10 09:45:00', 3, 4, 4, 3, 'Rotina de operações estável', 'Nenhum histórico', 10);
END;
/