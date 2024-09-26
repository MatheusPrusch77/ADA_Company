CREATE DATABASE IF NOT EXISTS ADACompany;

USE ADACompany;

CREATE TABLE Usuario (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NomeCoIDmpleto VARCHAR(100),
    Email VARCHAR(50),
    Senha VARCHAR(50),
    Telefone VARCHAR(11),
    TipoUsuario BIT
);

CREATE TABLE Cliente (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    Telefone VARCHAR(11),
    Endereco VARCHAR(100),
    NomeCliente VARCHAR(100),
    CNPJ VARCHAR(14),
    fk_UsuarioID INT,
    FOREIGN KEY (fk_UsuarioID) REFERENCES Usuario(ID)
);

CREATE TABLE Funcionario (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NomeFuncionario VARCHAR(100),
    Cargo VARCHAR(50),
    fk_UsuarioID INT,
    FOREIGN KEY (fk_UsuarioID) REFERENCES Usuario(ID)
);

CREATE TABLE Orcamento (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    ValidadeOrcamento DATE,
    DataCriacao DATE,
    ValorTotal DECIMAL(10,2),
    TipoServico VARCHAR(20),
    StatusOrc VARCHAR(15),
    Descricao VARCHAR(500),
    EmailVendedor VARCHAR(100),
    fk_IDCliente INT,
    fk_IDCServico INT,
    FOREIGN KEY (fk_IDServico) REFERENCES Servico(ID),
    FOREIGN KEY (fk_IDCliente) REFERENCES Cliente(ID)
);

CREATE TABLE Servico (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    TipoServico VARCHAR(20) NOT NULL,
    Valor DECIMAL(10, 2),
    Nome VARCHAR(20)
);

SELECT * FROM Orcamento;
/* Mudanças 23/05: */

-- syntax do ID para AUTO_INCREMENT (mySQL);
-- mudança tipo de CNPJ e Telefone para VARCHAR;
-- adequação datas para o tipo DATE (Java tipo sql.Date);

/* Mudanças 07/06: */
-- adição de nova coluna na tabela Usuário: TipoUsuario BIT;