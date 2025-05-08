#!/bin/bash

# Define all service/module folders
folders=(
  ".github/workflows"
  "common/src"
  "config-server/src"
  "api-gateway/src"
  "discovery-server/src"
  "auth-service/src"
  "user-service/src"
  "vendor-service/src"
  "order-service/src"
  "delivery-service/src"
  "notification-service/src"
  "analytics-service/src"
  "frontend/app/src"
)

# Create each folder and add .gitkeep
for folder in "${folders[@]}"; do
  mkdir -p "$folder"
  touch "$folder/.gitkeep"
done

echo "✅ Project structure initialized with .gitkeep files."
