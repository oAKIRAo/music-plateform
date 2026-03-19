# Scalable Music Streaming Platform -- Microservices Architecture Guide

## 1. Objective

Build a scalable music streaming platform inspired by Spotify using
microservices architecture. This project demonstrates backend design,
event-driven communication, authentication, realtime systems, and
containerized deployment.

------------------------------------------------------------------------

## 2. Technology Stack

-   Frontend: React, TypeScript, Axios
-   Backend: Spring Boot
-   API Gateway: Spring Cloud Gateway
-   Authentication: Keycloak (OAuth2 / OpenID Connect)
-   Messaging: Apache Kafka
-   Database: PostgreSQL (one database per service)
-   Cache: Redis
-   Infrastructure: Docker
-   Reverse Proxy: Nginx

------------------------------------------------------------------------

## 3. High Level Architecture

    Internet
       |
     Nginx
       |
     React Frontend
       |
     API Gateway
       |
    -------------------------------------------------
    |         |            |            |
    User      Music        Playlist     Realtime
    Service   Service      Service      Service
            \        |        /
              ----- Kafka Event Bus -----
                        |
                    Redis Cache
                        |
                    PostgreSQL
                        |
                     Keycloak

------------------------------------------------------------------------

## 4. Microservices

### User Service

-   manage profiles
-   subscriptions
-   listening history

### Music Service

-   upload songs
-   store metadata
-   streaming

### Playlist Service

-   create playlists
-   manage songs

### Realtime Service

-   listening rooms
-   synchronized playback

------------------------------------------------------------------------

## 5. Event-Driven Communication with Kafka

Services publish events to Kafka topics instead of calling each other
directly. Other services consume those events asynchronously.

### Example Flow

-   Music Service → publish SongPlayedEvent → topic: song-played\
-   User Service → consume → update history\
-   Analytics → consume → statistics

------------------------------------------------------------------------

## 6. Recommended Kafka Topics

-   user-created\
-   song-uploaded\
-   song-played\
-   playlist-created\
-   playlist-updated\
-   user-followed

------------------------------------------------------------------------

## 7. Microservice Folder Structure

    music-service

    src/main/java/com/music/musicservice

    config/
    controller/
    service/
    repository/
    entity/
    dto/
    mapper/
    exception/

    messaging/
       producer/
       consumer/
       event/

    resources/
    application.yml

    Dockerfile
    pom.xml

------------------------------------------------------------------------

## 8. Docker Infrastructure

    frontend
    gateway
    user-service
    music-service
    playlist-service
    realtime-service
    postgres
    redis
    keycloak
    kafka
    zookeeper
    nginx

------------------------------------------------------------------------

## 9. MVP Features

-   User authentication with Keycloak\
-   Upload music\
-   Play music\
-   Create playlists

------------------------------------------------------------------------

## 10. Advanced Features

-   Realtime listening rooms\
-   Collaborative playlists\
-   Music recommendations\
-   Analytics dashboard

------------------------------------------------------------------------

## 11. Portfolio Goals

-   Microservices architecture\
-   Event-driven systems (Kafka)\
-   Secure authentication\
-   Containerized deployment\
-   Scalable backend
