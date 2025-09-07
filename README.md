# JPMC Backend Engineering Project – Midas Core 🏦  
![Java](https://img.shields.io/badge/Java-17+-red?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.2.5-brightgreen?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-Build-blue?logo=apachemaven)
![Kafka](https://img.shields.io/badge/Kafka-3.1.4-black?logo=apachekafka)

This repository contains **my solution** for the **J.P. Morgan Chase Software Engineering Virtual Experience** (Forage).  
I completed all tasks successfully, integrating Kafka, H2 Database, REST APIs, and exposing user balance endpoints.

---

## 📌 Overview
Midas Core is a simplified backend system that:
- Listens for incoming **transactions** from a Kafka topic.
- Validates and records transactions to an **H2 database**.
- Integrates with an **Incentive API** to fetch and apply rewards.
- Exposes a **REST API** for querying user balances.

---

## 🛠️ Tech Stack
- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA**
- **Apache Kafka**
- **H2 In-Memory Database**
- **JUnit 5** + Embedded Kafka Test

---

## 🚀 Features
✅ **Kafka Consumer** – Listens to transactions asynchronously  
✅ **Transaction Validation** – Ensures sender/recipient IDs exist and balance is sufficient  
✅ **Database Persistence** – Stores transactions & updates balances  
✅ **Incentive API Integration** – Fetches incentives for each transaction  
✅ **REST Endpoint** – Exposes `/balance` for user balance queries  

---

## 📜 Certificate
I successfully completed the program and earned a [Certificate of Completion](https://drive.google.com/file/d/1DLTZWAzoxH_I86wh38CXjkBoi4kmIoge/view?usp=drive_link).

---

## 💡 What I Learned
- Setting up a Spring Boot project from scratch
- Using **Spring Kafka** to consume messages asynchronously
- Persisting data with **Spring Data JPA** and H2
- Calling REST APIs using **RestTemplate**
- Writing clean, testable code and debugging with IntelliJ

---
