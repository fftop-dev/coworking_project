# Coworking Project

## About
This repository contains the source code for the Coworking Project, a collaborative workspace management application built as a REST API using Java and Spring Boot. The project allows users to log in and provides different roles for users, including admin, member, and guest.
With the Coworking Project, users can effectively manage and organize a shared workspace environment. The application offers various features and functionalities tailored to different user roles:
1. Admin: Administrators have extensive control over the workspace management system. They can perform tasks such as creating and managing user accounts and monitoring overall system operations.
2. Member: Members are users who have registered and gained access to the coworking space. They can utilize the features provided by the application, such as managing reservations.
3. Guest: Guests are users who have limited access to the workspace management system. They may have temporary access, allowing them to explore available workspaces, check availability, and make inquiries.

The Coworking Project aims to enhance collaboration, productivity, and convenience within a shared workspace environment. By leveraging the power of Java and Spring Boot, the project delivers a robust and scalable REST API that can be integrated with various front-end applications or used as a standalone backend solution.
By following the instructions provided in the repository, you can clone the code, run the Coworking Project, and start exploring its features and functionalities.
Feel free to customize the Coworking Project according to your specific  -workspace management needs and requirements.

## Cloning the Repository
To clone the Coworking_Project repository and initialize its submodules, follow these steps:
1. Open your terminal or command prompt.
2. Change to the directory where you want to clone the repository.
    1. Execute the following command:
       `git clone https://github.com/fftop-dev/coworking_project.git`

## Run Code
To run the Coworking_Project and start the REST API, you need to have Java and Spring Boot installed. Follow these steps:
1. Open your terminal or command prompt.
2. Navigate to the root directory of the cloned repository.
3. Execute the following command:
   `./mvnw spring-boot:run`

## Test Data
The Coworking_Project repository already contains test data in the code. 
You can use this data for testing and exploration.
If you want to change the test data, you can simply go to file `src/main/java/ch/zli/m223/ksh20/coworking_project/init/ServerInit.java` and change it.
