#!/bin/sh

echo "Waiting for PostgreSQL to be ready..."
while ! nc -z postgres 5432; do
  sleep 1
done

echo "PostgreSQL ready"
exec java -jar app.jar
