#!/bin/bash
set -e

# Start the default Cassandra entrypoint in the background
docker-entrypoint.sh cassandra -f &

# Store the Cassandra PID
CASSANDRA_PID=$!

# Wait for Cassandra to become ready
until cqlsh -e "DESC KEYSPACES" >/dev/null 2>&1; do
  echo "Waiting for Cassandra to start..."
  sleep 5
done

echo "Cassandra is ready! Creating keyspace..."

# Create the keyspace
cqlsh -f /cassandra-init.cql

echo "Initialization completed!"

# Wait for Cassandra process to finish
wait $CASSANDRA_PID 