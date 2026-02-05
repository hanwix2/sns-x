#!/bin/bash

BASE_URL="http://localhost:8080"

echo "=== Register User ==="
curl -X POST "${BASE_URL}/api/v1/users" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "johndoe",
    "email": "john@example.com",
    "password": "password123",
    "nickname": "John"
  }'
echo -e "\n"

echo "=== Register Another User ==="
curl -X POST "${BASE_URL}/api/v1/users" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "janedoe",
    "email": "jane@example.com",
    "password": "password456",
    "nickname": "Jane"
  }'
echo -e "\n"

echo "=== Get User by ID ==="
curl -X GET "${BASE_URL}/api/v1/users/1"
echo -e "\n"

echo "=== Get User by Username ==="
curl -X GET "${BASE_URL}/api/v1/users/username/johndoe"
echo -e "\n"

echo "=== Get All Users ==="
curl -X GET "${BASE_URL}/api/v1/users"
echo -e "\n"
