#!/bin/bash
echo "Creating DynamoDb 'ItemInfo' table..."
awslocal dynamodb create-table --cli-input-json '{
  "TableName": "ItemInfo",
  "KeySchema": [{"AttributeName": "id", "KeyType": "HASH"}],
  "AttributeDefinitions": [{"AttributeName": "id", "AttributeType": "S"}],
  "BillingMode": "PAY_PER_REQUEST"
}'

awslocal dynamodb create-table --cli-input-json '{
  "TableName": "Payment",
  "KeySchema": [
    { "AttributeName": "PK", "KeyType": "HASH" },
    { "AttributeName": "SK", "KeyType": "RANGE" }
  ],
  "AttributeDefinitions": [
    { "AttributeName": "PK", "AttributeType": "S" },
    { "AttributeName": "SK", "AttributeType": "S" }
  ],
  "BillingMode": "PAY_PER_REQUEST"
}'



echo "Listing tables..."
awslocal dynamodb list-tables
