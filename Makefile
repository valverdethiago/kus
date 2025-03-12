PROJECT_DIR := $(shell pwd)

DB_CONTAINER=cassandra

.PHONY: up down logs

# Start the MySQL database container
up:
	podman-compose -f $(PROJECT_DIR)/docker/docker-compose.yml up -d

# Stop and remove the database container
down:
	podman-compose -f $(PROJECT_DIR)/docker/docker-compose.yml down

# Reset the database (stop, remove volume, and restart)
reset: down
	podman volume rm cassandra_data || true
	podman-compose -f $(PROJECT_DIR)/docker/docker-compose.yml up -d

# Reset the database (stop, remove volume, and restart)
restart: down
	podman-compose -f $(PROJECT_DIR)/docker/docker-compose.yml up -d

# Show MySQL logs
logs:
	podman logs -f $(DB_CONTAINER)
