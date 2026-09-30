# Kafka POC - Order to Stock Event Flow

Personal learning project created on 2023-10-29.

This repository is a Kafka + Spring Boot proof of concept that demonstrates event publishing and consumption between two services.

Project status: private, learning/archive project, not under active development.

## Why this exists

I built this to learn event-driven communication using Kafka and Spring Boot.  
I no longer actively work on Kafka, so this repo is kept as a private reference.

## Learning source note

I originally learned this from a YouTube tutorial around the time I built it, but I do not remember the exact video/channel now.

## Overview

This project has 3 modules:

- Base-domains: shared DTO classes used by producer and consumer
- Order service: exposes REST API and publishes OrderEvent to Kafka
- Stock service: consumes OrderEvent from Kafka and logs the event

## Event flow

1. Client sends POST /api/orders to Order service.
2. Order service generates an orderId and wraps the payload in an OrderEvent.
3. Order service publishes the event to Kafka topic orders_topics.
4. Stock service consumes the event using a Kafka listener.

## Tech stack

- Java 17
- Spring Boot 3.1.4
- Spring for Apache Kafka
- Maven
- Apache Kafka (local)

## Project structure

- kafkaApplication/Base-domains
- kafkaApplication/Order
- kafkaApplication/Stock

## Prerequisites

- JDK 17
- Maven (or Maven Wrapper from each module)
- Running Kafka broker at localhost:9092

## Configuration

Current defaults:

- Order producer bootstrap server: localhost:9092
- Stock consumer bootstrap server: localhost:9092
- Topic: orders_topics
- Stock service port: 8082
- Order service port: default Spring Boot port 8080

## Run locally

Build and install shared module first:

~~~bash
cd kafkaApplication/Base-domains
mvnw clean install
~~~

Start Stock service (consumer):

~~~bash
cd kafkaApplication/Stock
mvnw spring-boot:run
~~~

Start Order service (producer API):

~~~bash
cd kafkaApplication/Order
mvnw spring-boot:run
~~~

## Test the flow

Send a sample order request:

~~~bash
curl -X POST http://localhost:8080/api/orders ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Laptop\",\"qty\":1,\"price\":1200.0}"
~~~

Expected behavior:

- API returns: Order placed successfully ...
- Stock service logs: Order event received in stock service => ...

## Notes

- Stock consumer currently logs incoming events only.
- Database persistence is not implemented in this POC.
- This project is private and intended as a personal learning reference.


