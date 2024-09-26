-- Funcionários
INSERT INTO Usuario (NomeCompleto, Email, Senha, Telefone, TipoUsuario) VALUES
('Matheus Prüsch', 'matheus.prusch@example.com', 'senhaMatheus', '11987654321', 1),
('Samuel', 'samuel@example.com', 'senhaSamuel', '11987654322', 1),
('Rafael', 'rafael@example.com', 'senhaRafael', '11987654323', 1),
('Carlos', 'carlos@example.com', 'senhaCarlos', '11987654324', 1);

INSERT INTO Funcionario (NomeFuncionario, Cargo, fk_UsuarioID) VALUES
('Matheus Prüsch', 'Administrador', 1),
('Samuel', 'Administrador', 2),
('Rafael', 'Pedreiro', 3),
('Carlos', 'Administrador', 4);

-- Clientes
INSERT INTO Usuario (NomeCompleto, Email, Senha, Telefone, TipoUsuario) VALUES
('Softech Solutions', 'contact@softechsolutions.com', 'senhaSoftech', '11987654325', 0),
('Refrigerante Rossi', 'info@refrigeranterossi.com', 'senhaRossi', '11987654326', 0),
('Alpha Networks', 'sales@alphanetworks.com', 'senhaAlpha', '11987654327', 0),
('Beta Technologies', 'support@betatech.com', 'senhaBeta', '11987654328', 0),
('Gamma Enterprises', 'contact@gammaenterprises.com', 'senhaGamma', '11987654329', 0),
('Delta Innovations', 'info@deltainnovations.com', 'senhaDelta', '11987654330', 0),
('Epsilon Motors', 'sales@epsilonmotors.com', 'senhaEpsilon', '11987654331', 0),
('Zeta Pharmaceuticals', 'support@zetapharma.com', 'senhaZeta', '11987654332', 0),
('Eta Communications', 'contact@etacommunications.com', 'senhaEta', '11987654333', 0),
('Theta Logistics', 'info@thetalogistics.com', 'senhaTheta', '11987654334', 0);

INSERT INTO Cliente (Telefone, Endereco, NomeCliente, CNPJ, fk_UsuarioID) VALUES
('11987654325', 'Rua A, 123', 'Softech Solutions', '11111111000111', 5),
('11987654326', 'Rua B, 456', 'Refrigerante Rossi', '22222222000122', 6),
('11987654327', 'Rua C, 789', 'Alpha Networks', '33333333000133', 7),
('11987654328', 'Rua D, 101', 'Beta Technologies', '44444444000144', 8),
('11987654329', 'Rua E, 202', 'Gamma Enterprises', '55555555000155', 9),
('11987654330', 'Rua F, 303', 'Delta Innovations', '66666666000166', 10),
('11987654331', 'Rua G, 404', 'Epsilon Motors', '77777777000177', 11),
('11987654332', 'Rua H, 505', 'Zeta Pharmaceuticals', '88888888000188', 12),
('11987654333', 'Rua I, 606', 'Eta Communications', '99999999000199', 13),
('11987654334', 'Rua J, 707', 'Theta Logistics', '00000000000100', 14);

-- Servico
INSERT INTO Servico (TipoServico, Nome, Valor) VALUES
('ADAPTACAO', 'Adaptacao', 5000),
('DESENVOLVIMENTO', 'Desenvolvimento', 7000),
('CONSULTORIA', 'Consultoria', 4000);

-- Orcamento
INSERT INTO Orcamento (ValidadeOrcamento, DataCriacao, ValorTotal, TipoServico, StatusOrc, Descricao, EmailVendedor, fk_IDCliente) VALUES
('2024-10-11', '2024-02-11', 5000.00, 'ADAPTACAO', 'ANALISE', 'Solicitação de adaptação de layout para tornar o site mais acessível ao público idoso.', '', 5),
('2024-11-15', '2024-03-15', 7000.00, 'DESENVOLVIMENTO', 'ANALISE', 'Solicitação de desenvolvimento de site do zero, com foco em acessibilidade para o público infantil.', '', 6),
('2024-12-20', '2024-04-20', 4000.00, 'CONSULTORIA', 'ANALISE', 'Solicitação de consultoria na área de desenvolvimento de site voltado para o público idoso.', '', 7),
('2024-11-15', '2024-03-15', 2250.00, 'ADAPTACAO', 'DESENVOLVIMENTO', 'Adaptação de layout para tornar o site mais acessível ao público idoso.', 'matheus.prusch@example.com', 8),
('2024-06-11', '2024-06-11', 7700.00, 'DESENVOLVIMENTO', 'CONCLUIDO', 'Desenvolvimento de aplicativo educacional com foco em acessibilidade para crianças com deficiência visual.', 'samuel@example.com', 9),
('2024-09-30', '2024-01-30', 3500.00, 'DESENVOLVIMENTO', 'CANCELADO', 'Adaptação de infraestrutura para tornar o site mais acessível ao público idoso.', 'matheus.prusch@example.com', 10),
('2024-07-05', '2024-03-05', 4400.00, 'CONSULTORIA', 'DESENVOLVIMENTO', 'Consultoria em marketing digital com foco em promover a acessibilidade em websites corporativos.', 'matheus.prusch@example.com', 11),
('2024-06-11', '2024-06-11', 6000.00, 'CONSULTORIA', 'CONCLUIDO', 'Desenvolvimento de site para empresa de entretenimento infantil, com ênfase em tornar o conteúdo acessível para crianças com deficiência.', 'samuel@example.com', 12),
('2024-06-02', '2024-03-02', 3850.00, 'CONSULTORIA', 'CANCELADO', 'Solicitação de consultoria técnica em desenvolvimento de sistemas para promover a acessibilidade em plataformas de comércio eletrônico.', 'matheus.prusch@example.com', 13);



-- Select do valor total de orçamentos concluídos por cliente.
SELECT c.NomeCliente, SUM(o.ValorTotal) AS ValorTotalConcluido
FROM Cliente c
INNER JOIN Orcamento o ON c.ID = o.fk_IDCliente
WHERE o.StatusOrc = 'CONCLUIDO'
GROUP BY c.NomeCliente;

-- Select do valor total de orçamentos pendentes por cliente (que podem ser concluídos).
SELECT c.NomeCliente, SUM(o.ValorTotal) AS ValorTotalAnaliseDesenvolvimento
FROM Cliente c
INNER JOIN Orcamento o ON c.ID = o.fk_IDCliente
WHERE o.StatusOrc IN ('ANALISE', 'DESENVOLVIMENTO')
GROUP BY c.NomeCliente;

--orcamentos concludos para cada Tipo de Serviço. Verifica rentabilidade.
SELECT s.TipoServico,
    SUM(CASE WHEN o.StatusOrc = 'CONCLUIDO' THEN o.ValorTotal ELSE 0 END) AS ValorTotalConcluido
FROM  Servico s
LEFT JOIN Orcamento o ON s.TipoServico = o.TipoServico
GROUP BY s.TipoServico;

--Select que mostra os tipos de serviços mais solicitados pelos clintes.
SELECT 
    o.TipoServico,
    COUNT(*) AS Quantidade
FROM 
    Orcamento o
GROUP BY 
    o.TipoServico;


-- Atualização de +10% do preço dos serviços.
UPDATE Servico
SET Valor = Valor * 1.10;

-- Alteração do status dos orçamentos em analise.
UPDATE Orcamento
SET StatusOrc = 'CANCELADO'
WHERE ID = 1;

UPDATE Orcamento
SET StatusOrc = 'CANCELADO'
WHERE ID = 2;


