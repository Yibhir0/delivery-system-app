# Delivery-WebApp

## Project Overview

The scope of our project is focused on designing a robust delivery service application that addresses both current and future needs of users. We will ensure that our system is scalable and flexible, capable of handling various delivery scenarios and accommodating future enhancements. By adhering to design principles that emphasize usability, efficiency, and reliability, we aim to deliver a solution that not only meets the immediate requirements but also provides a foundation for ongoing improvements and integration with other services.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation--usage)
- [Team & Roles](#team--roles)
 
## Features

The core features of the Delivery service include:

1. Request for delivery service
2. Proposal of a quotation for the service
3. Communication about the service
4. Tracking the order
5. Payment
6. Help assistance by using Chatbot


## Technologies

The following are the major frameworks and tools we will be using to develop the project:

* [![React][React.js]][React-url]
* [![Spring Boot][SpringBoot]][SpringBoot-url]
* [![PostgreSQL][PostgreSQL]][PostgreSQL-url]

- **Frontend**: [React](https://reactjs.org/)
- **Backend**: [Spring Boot](https://spring.io/projects/spring-boot)
- **Database**: [PostgreSQL](https://www.postgresql.org/)

[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[SpringBoot]: https://img.shields.io/badge/Spring%20Boot-%236DB33F.svg?style=for-the-badge&logo=spring-boot&logoColor=white
[SpringBoot-url]: https://spring.io/projects/spring-boot
[PostgreSQL]: https://img.shields.io/badge/PostgreSQL-%23336791.svg?style=for-the-badge&logo=postgresql&logoColor=white
[PostgreSQL-url]: https://www.postgresql.org/


## Installation & Usage

### Prerequisites
- **Docker**: Ensure Docker is installed to run this project in containers. You can install it via:
    - **Docker Desktop**:
        - [macOS](https://docs.docker.com/desktop/install/mac-install/)
        - [Windows](https://docs.docker.com/desktop/install/windows-install/) (requires **WSL2**)
        - [Linux](https://docs.docker.com/desktop/install/linux-install/)
    - **Linux or Linux VM**: If you're on a Linux environment, Docker can be installed directly on the system. Refer to Docker's [Linux installation guide](https://docs.docker.com/engine/install/) for detailed instructions.

### Steps
1. Clone the repository:
    ```bash
    git clone https://github.com/Mohadgb/Delivery-WebApp.git
    ```
2. Navigate to the project directory:
    ```bash
    cd Delivery-WebApp
    ```
3. Set up the environment variables:
    - Create a `.env` file in the root directory of the project and add the following environment variables:
        ```bash
          VITE_BACKEND_URL
          DB_URL
          POSTGRES_DB
          POSTGRES_USER
          POSTGRES_PASSWORD
		  GEMINI_URL
		  GEMINI_API_KEY
   
4. Run the project (make sure that you have all the environment variables are set up correctly):
    ```bash
    docker compose up -d
    ```

5. Access the application at `http://localhost:3000` in your browser. The backend API is available at `http://localhost:8080`.


6. To stop the project, run:
    ```bash
    docker compose down
    ```

## Team & Roles


* Abdelmalek Mouhamou (*github username here*) - <strong>Backend DEV</strong>
* Ali Zidan (*github username here*) - <strong>Front-end DEV</strong>
* Miskat Mahmud (codedsami) - <strong>Backend DEV</strong>
* Mohamed Saidi (Mohadgb) - <strong>Front-end DEV</strong>
* Yassine Ibhir (YIbhir0) - <strong>Front-end DEV</strong>
* Zeiad Badawy (iKozay) - <strong>Scrum master/Backend DEV</strong>
