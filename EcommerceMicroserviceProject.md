# 🛒 Ecommerce Microservice Project — Backend distribuido con Spring Cloud

## 📋 Descripción

Este proyecto es una aplicación backend de comercio electrónico basada en arquitectura de microservicios, diseñada con tecnologías modernas del ecosistema Spring Cloud. Permite la gestión completa de productos, stock, usuarios, carritos y órdenes, con comunicación asíncrona mediante Kafka, alta disponibilidad, y principios de escalabilidad y resiliencia.

El proyecto implementa una arquitectura de microservicios robusta con los siguientes componentes principales:

## 🚀 Servicios

### 🔍 Eureka Server
- **Función**: Registro y descubrimiento de servicios
- **Patrón**: Service Discovery

### ⚙️ Config Server
- **Función**: Configuración centralizada distribuida
- **Patrón**: Configuration Server
- **Características**: Gestión dinámica de configuraciones por ambiente

### 🌐 API Gateway
- **Función**: Punto de entrada unificado
- **Características**: Enrutamiento, balanceo de carga, autenticación

### 📦 Product Service
- **Función**: Gestión del catálogo de productos
- **Base de datos**: MongoDB
- **Cache**: Redis (productos por ID)

### 📊 Inventory Service
- **Función**: Control de stock e inventario
- **Base de datos**: MongoDB
- **Actualización**: Eventos Kafka automáticos

### 👤 User Service
- **Función**: Gestión de usuarios del sistema
- **Base de datos**: PostgreSQL
- **Característica**: Creación automática de carrito al registrar usuario

### 🛒 Cart Service
- **Función**: Gestión de carritos de compras
- **Base de datos**: PostgreSQL

### 📋 Order Service
- **Función**: Procesamiento y gestión de órdenes
- **Base de datos**: PostgreSQL
- **Integración**: Actualización automática de stock una vez generada la orden

## 📁 Módulos Compartidos

### 📋 Config Data
Contiene los archivos `application.yml` centralizados para cada servicio con configuraciones específicas por ambiente.

### 🔄 Common Models
Módulo que centraliza los eventos Kafka y modelos compartidos entre servicios, promoviendo la reutilización de código y consistencia.

## 🛠️ Stack Tecnológico

### Backend Core
- **Java 17** - Lenguaje principal con características modernas
- **Spring Boot 3.x** - Framework principal con configuración automática
- **Maven** - Gestión de dependencias y construcción del proyecto
- **Spring Data JPA** - Abstracción de persistencia
- **Hibernate** - ORM para bases de datos relacionales

### Microservicios & Cloud
- **Spring Cloud** - Ecosistema completo para microservicios
- **Eureka Discovery Client** - Registro y descubrimiento de servicios
- **OpenFeign** - Cliente HTTP para comunicación entre servicios
- **Spring Cloud Gateway** - API Gateway con enrutamiento inteligente

### Resiliencia & Monitoreo
- **Resilience4j** - Circuit Breaker pattern para tolerancia a fallos
- **Spring Boot Actuator** - Endpoints de monitoreo y métricas
- **Fallback Classes** - Manejo de degradación de servicios

### Mensajería & Cache
- **Apache Kafka** - Procesamiento de eventos asíncronos
- **Redis** - Cache distribuido para optimización de consultas

### Calidad & Desarrollo
- **Lombok** - Reducción de código
- **MapStruct** - Mapeo entre DTOs y entidades
- **Bean Validation** - Validaciones declarativas con anotaciones
- **Manejo Global de Excepciones** - Respuestas de error consistentes

## Estado del Proyecto

Este backend está completamente funcional y listo para integrarse con cualquier frontend (web, mobile o APIs externas).

Fue diseñado como base escalable para un e-commerce real, con separación clara de responsabilidades, patrones modernos, tolerancia a fallos, y una arquitectura robusta lista para producción.