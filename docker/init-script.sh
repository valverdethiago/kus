#!/bin/bash

# Wait for Cassandra to be ready
until cqlsh -e "describe keyspaces;" > /dev/null 2>&1; do
  echo "Waiting for Cassandra to be ready..."
  sleep 2
done

echo "Cassandra is ready! Creating keyspace..."

# Execute the initialization script
cqlsh -f /cassandra-init.cql

echo "Initialization completed!" 