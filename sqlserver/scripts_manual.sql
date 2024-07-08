-- Criar tabela
CREATE TABLE acervo_machado_de_assis (
    id INT IDENTITY(1, 1) PRIMARY KEY,
    pagina INT,
    conteudo TEXT NOT NULL,
    tags VARCHAR(255),
    createdAt DATETIME NOT NULL DEFAULT GETDATE(),
    updatedAt DATETIME NOT NULL DEFAULT GETDATE()
);

-- Trigger para atualizar a coluna updatedAt em cada atualização da linha
CREATE TRIGGER trgUpdateUpdatedAt
ON acervo_machado_de_assis
AFTER UPDATE
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE acervo_machado_de_assis
    SET updatedAt = GETDATE()
    FROM acervo_machado_de_assis AS a
    INNER JOIN inserted AS i ON a.id = i.id;
END;