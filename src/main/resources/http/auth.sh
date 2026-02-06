#!/bin/bash

BASE_URL="http://localhost:8080"
COOKIE_FILE="/tmp/auth_cookies.txt"

# Clean up previous cookies
rm -f "$COOKIE_FILE"

echo "=== 1. Register User ==="
curl -s -X POST "${BASE_URL}/api/v1/users" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "nickname": "Test User"
  }'
echo -e "\n"

echo "=== 2. Login (Invalid Password) ==="
curl -s -X POST "${BASE_URL}/api/v1/login" \
  -d "username=testuser" \
  -d "password=wrongpassword" \
  -w "\nHTTP Status: %{http_code}"
echo -e "\n"

echo "=== 3. Login (Valid Credentials) ==="
curl -s -X POST "${BASE_URL}/api/v1/login" \
  -d "username=testuser" \
  -d "password=password123" \
  -c "$COOKIE_FILE" \
  -w "\nHTTP Status: %{http_code}"
echo -e "\n"

echo "=== 4. Access Protected API (With Session) ==="
curl -s -X GET "${BASE_URL}/api/v1/users/1" \
  -b "$COOKIE_FILE" \
  -w "\nHTTP Status: %{http_code}"
echo -e "\n"

echo "=== 5. Access Protected API (Without Session) ==="
curl -s -X GET "${BASE_URL}/api/v1/users/1" \
  -w "\nHTTP Status: %{http_code}"
echo -e "\n"

echo "=== 6. Logout ==="
curl -s -X POST "${BASE_URL}/api/v1/logout" \
  -b "$COOKIE_FILE" \
  -c "$COOKIE_FILE" \
  -w "\nHTTP Status: %{http_code}"
echo -e "\n"

echo "=== 7. Access Protected API (After Logout) ==="
curl -s -X GET "${BASE_URL}/api/v1/users/1" \
  -b "$COOKIE_FILE" \
  -w "\nHTTP Status: %{http_code}"
echo -e "\n"

# Clean up
rm -f "$COOKIE_FILE"

echo "=== Test Complete ==="
