# Dynamic Numbering Project

A flexible system for generating dynamic numbers based on configurable criteria and rules.

## Features

- Dynamic number generation based on multiple criteria types
- Configurable criteria ordering and formatting
- Support for multiple identifiers
- Prefix and suffix customization
- Automatic counter increment
- Name-based number generation with configurable length
- Birth date integration
- Real-time number preview
- Criteria management interface
- RESTful API endpoints

## Technical Requirements

### Backend
- Java 17 or higher
- Spring Boot 3.x
- Maven 3.x
- Spring Web
- JUnit 5 for testing

### Frontend
- Node.js 16.x or higher
- Vue.js 2.x
- Vue Router
- Axios for API communication
- npm or yarn package manager

## Backend (Spring Boot)

### Architecture

#### Controller Layer (NumberingController)
- REST API Endpoints:
    1. Configure Criteria
        - POST `/api/numbering/configure/{identifier}`
        - Accepts: List<CriteriaDTO> in request body
        - Purpose: Configures numbering criteria for a specific identifier
        - Returns: Success confirmation message

    2. Generate Number
        - POST `/api/numbering/generate/{identifier}`
        - Accepts: RegistrantDTO (firstName, lastName, birthDate)
        - Purpose: Generates a unique number based on configured criteria for given identifier
        - Returns: Generated number string

    3. Get Identifiers
        - GET `/api/numbering/identifiers`
        - Purpose: Retrieves list of all available identifiers
        - Returns: List of identifier strings

#### Service Layer
- Contains business logic
- Implements number generation rules
- Manages criteria and registrant data processing
- Main service: NumberingServiceImpl
    - Generates dynamic numbers based on defined criteria
    - Processes names, dates and counters

#### Tests
- Unit tests for criteria validation
- Integration tests for number generation verification
- TestDataProvider: provides test data
    - Two test data sets (Test1 and Test2)
    - Predefined criteria with different orders and formats
    - Registrant data for testing

## Frontend (Vue.js)

### Architecture

#### Router (router/index.js)
- Application routing management
- Main routes:
    - Home (/)
    - Numbering (/numbering)
    - Criteria (/criteria) - Criteria management interface
    - Generate (/generate) - Number generation form

#### Configuration
- Vue.config.js: Proxy configuration for backend communication
- Babel.config.js: Transpilation configuration

#### Components
- App.vue: Root component
- Views:
    - HomeView: Landing page
    - NumberingView: Number generation interface
    - CriteriaForm.vue: Component for creating and editing numbering criteria
        - Manages criteria types (FIRST_NAME, LAST_NAME, BIRTH_DATE, COUNTER)
        - Handles criteria ordering and formatting options
    - GenerateForm.vue: Component for number generation
        - Input form for registrant data
        - Displays generated number results
        - Handles API communication with backend

## Build and Run Instructions

### Backend
1. Build the project:
mvn clean install

2. Run the application:
mvn spring-boot:run

### Frontend
1. Install dependencies:
npm install

2. Run the development server:
npm run serve

3. Build for production:
npm run build

Technical Configuration
* Backend port: 8080
* Frontend development port: 8081
* Configured proxy for API calls
* Build configured to generate into Spring Boot static resources

Accessing the Application
* Backend API: http://localhost:8080
* Production deployment: Access through backend port after building frontend