CREATE OR REPLACE PROCEDURE InsertEmpresa(p_nome_empresa IN VARCHAR2) AS
BEGIN
    INSERT INTO EMPRESA (NOME_EMPRESA) VALUES (p_nome_empresa);
    COMMIT;
END InsertEmpresa;
/

CREATE OR REPLACE PROCEDURE InsertSetor(p_descricao IN VARCHAR2, p_empresa_id IN NUMBER) AS
BEGIN
    -- Check if empresa exists
    DECLARE
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_count FROM EMPRESA WHERE ID = p_empresa_id;
        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20001, 'Empresa não existe');
        END IF;
    END;
    
    INSERT INTO SETOR (DESCRICAO, EMPRESA_ID) VALUES (p_descricao, p_empresa_id);
    COMMIT;
END InsertSetor;
/

CREATE OR REPLACE PROCEDURE InsertUsuario(p_nome IN VARCHAR2, p_cargo IN VARCHAR2, p_senha IN VARCHAR2, p_setor_id IN NUMBER) AS
BEGIN
    -- Check if setor exists
    DECLARE
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_count FROM SETOR WHERE ID = p_setor_id;
        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Setor não existe');
        END IF;
    END;

    INSERT INTO USUARIO (NOME, CARGO, SENHA, SETOR_ID) VALUES (p_nome, p_cargo, p_senha, p_setor_id);
    COMMIT;
END InsertUsuario;
/

CREATE OR REPLACE PROCEDURE InsertFuncionarioInfo(
    p_data IN TIMESTAMP,
    p_humor IN NUMBER,
    p_energia IN NUMBER,
    p_carga IN NUMBER,
    p_sono IN NUMBER,
    p_observacao IN VARCHAR2,
    p_historico_medico IN VARCHAR2,
    p_usuario_id IN NUMBER
) AS
BEGIN
    -- Check if usuario exists
    DECLARE
        v_count NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_count FROM USUARIO WHERE ID = p_usuario_id;
        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20003, 'Usuário não existe');
        END IF;
    END;

    INSERT INTO FUNCIONARIO_INFO (
        DATA, HUMOR, ENERGIA, CARGA, SONO, OBSERVACAO, HISTORICO_MEDICO, USUARIO_ID
    ) VALUES (
        p_data, p_humor, p_energia, p_carga, p_sono, p_observacao, p_historico_medico, p_usuario_id
    );
    COMMIT;
END InsertFuncionarioInfo;
/