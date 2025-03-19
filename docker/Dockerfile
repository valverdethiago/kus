FROM cassandra:4.1

COPY cassandra-init.cql /cassandra-init.cql

# Create an initialization script
COPY docker-entrypoint-initdb.sh /docker-entrypoint-initdb.sh
RUN chmod +x /docker-entrypoint-initdb.sh

CMD ["/docker-entrypoint-initdb.sh"] 