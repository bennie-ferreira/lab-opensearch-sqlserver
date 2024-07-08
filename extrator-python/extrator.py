import os
import pymssql
import PyPDF2
from datetime import datetime


def extract_text_from_pdf(pdf_path):
    with open(pdf_path, "rb") as file:
        reader = PyPDF2.PdfReader(file)
        num_pages = len(reader.pages)
        text = []
        for page_num in range(num_pages):
            page = reader.pages[page_num]
            text.append(page.extract_text())
        return text


# Função para inserir dados no banco de dados SQL Server
def insert_into_database(pages):
    print(" ====> Iniciando ingestão no banco de dados SQL Server")
    # Configurações de conexão com o SQL Server
    server = os.getenv("databaseUri")
    database = os.getenv("databaseName")
    username = os.getenv("databaseUser")
    password = os.getenv("databasePassword")
    print(server, username, password, database)

    # Estabelecer conexão com o SQL Server
    conn = pymssql.connect(
        server=server, user=username, password=password, database=database
    )
    cursor = conn.cursor()

    for i, content in enumerate(pages):
        tags = "Machado de Assis, Literatura Brasileira"
        createdAt = updatedAt = datetime.now()

        # Inserir dados na tabela
        cursor.execute(
            """
            INSERT INTO acervo_machado_de_assis (pagina, conteudo, tags, createdAt, updatedAt)
            VALUES (%s, %s, %s, %s, %s)
            """,
            (i + 1, content, tags, createdAt, updatedAt),
        )
        print(" ===> Conteudo inserido com sucesso! ")

    # Confirmar transação e fechar conexão
    conn.commit()
    conn.close()
    print(" ===> Transação finalizada ")


# Caminho para o arquivo PDF
pdf_path = "assets/Dom_Casmurro-Machado_de_Assis.pdf"

# Extrair texto do PDF e inserir no banco de dados
print("# -- Iniciando script de extração de texto -- #")
pages = extract_text_from_pdf(pdf_path)
insert_into_database(pages)
