FROM opensearchproject/logstash-oss-with-opensearch-output-plugin

# Cria o diretório para o driver JDBC
RUN mkdir -p /usr/share/logstash/lib/jars

# Baixar o conector JDBC para MSSQL e mova-o para o diretório correto
RUN curl -L --output "sqljdbc_12.6.2.0_ptb.tar.gz" "https://download.microsoft.com/download/9/e/9/9e97cef4-4c64-484c-bd1b-192147912c47/ptb/sqljdbc_12.6.2.0_ptb.tar.gz" \
    && tar -xf "sqljdbc_12.6.2.0_ptb.tar.gz" \
    && mv "sqljdbc_12.6/ptb/jars/mssql-jdbc-12.6.2.jre11.jar" "/usr/share/logstash/lib/jars/mssql-jdbc-12.6.2.jre11.jar" \
    && rm -f "sqljdbc_12.6.2.0_ptb.tar.gz" \
    && rm -rf "sqljdbc_12.6.2.0_ptb"

# Define permissões corretas para o driver JDBC
RUN chmod 644 /usr/share/logstash/lib/jars/mssql-jdbc-12.6.2.jre11.jar
RUN chown logstash:logstash /usr/share/logstash/lib/jars/mssql-jdbc-12.6.2.jre11.jar

CMD ["sh", "-c", "tail -f /dev/null"]