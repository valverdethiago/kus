PROJECT_DIR := $(shell pwd)

DB_CONTAINER=cassandra

.PHONY: up down logs

# Start the MySQL database container
up:
	docker-compose -f $(PROJECT_DIR)/docker/docker-compose.yml up -d

# Stop and remove the database container
down:
	docker-compose -f $(PROJECT_DIR)/docker/docker-compose.yml down

# Reset the database (stop, remove volume, and restart)
reset: down
	docker volume rm cassandra_data || true
	docker-compose -f $(PROJECT_DIR)/docker/docker-compose.yml up -d

# Reset the database (stop, remove volume, and restart)
restart: down
	docker-compose -f $(PROJECT_DIR)/docker/docker-compose.yml up -d

# Show MySQL logs
logs:
	docker logs -f $(DB_CONTAINER)
