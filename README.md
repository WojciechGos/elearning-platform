
# eLearning Platform

## Project description

This project is an eLearning platform developed using Spring Boot, Angular, PostgreSQL, AWS S3, and Stripe. The platform provides a comprehensive online learning experience with various functionalities, including course creation, payment processing, and user authentication.

## Technologies Used

- **Backend:** Spring Boot
- **Frontend:** Angular
- **Database:** PostgreSQL
- **Storage:** AWS S3
- **Payment Gateway:** Stripe
- **Authorization:** Oauth2

## Functionalities

### Integrations
- **AWS S3 Integration:** Integrated with AWS S3 for storing video courses and images using presigned urls.
- **Payment System Integration:** Integrated with Stripe - an electronic payment system.
- **OAuth2 Google Integration:** Integrated with Google OAuth2 for user authentication.  

### Raw functionalities
- **Reactive Course Creator Form:** Created a reactive form for course creation.
- **Access Guards:** Implemented guards to prevent unauthorized access to certain pages.
- **Course Search:** Developed a search functionality for finding courses.
- **Admin Panel:** Created an admin panel to manage course visibility and user carts.
- **Payment Scenarios Handling:** Handled various payment scenarios and offline payments.
- **Cart Functionality:** Implemented cart functionality for users.
- **Discussion Forum:** Created a discussion forum under courses.
- **JWT Authentication and Authorization:** Implemented authentication and authorization using JWT tokens.
- **Token Configuration:** Configured access and refresh tokens.
- **Course Completion Functionality:** Implemented functionality to mark courses as completed.

## Testing

The project includes comprehensive testing to ensure the functionality and reliability of the platform. We used the following types of tests:

- **Unit Tests:** Implemented unit tests with coverage to test individual components and services in isolation.
- **Integration Tests:** Created integration tests to verify the interaction between different components and services.
- **End-to-End (E2E) Tests:** Developed E2E tests to simulate real user scenarios and verify the overall functionality of the platform.


## SOLID principles

Throughout the development of this project, we adhered to the SOLID principles to ensure a robust and maintainable codebase:

- **Single Responsibility Principle (SRP):** Each class and function has a single responsibility, making the code more understandable and easier to maintain. 
- **Open/Closed Principle (OCP):** Our system is designed to be open for extension but closed for modification, allowing new features to be added with minimal changes to existing code.
- **Liskov Substitution Principle (LSP):** Subclasses and derived classes are used interchangeably without affecting the correctness of the program, ensuring that derived classes extend base classes without changing their behavior.
- **Interface Segregation Principle (ISP):** We created specific and fine-grained interfaces, ensuring that clients only need to know about the methods that are relevant to them.
- **Dependency Inversion Principle (DIP):** High-level modules do not depend on low-level modules; both depend on abstractions. This principle is particularly reflected in our use of dependency injection and interface-based programming.

## Improvements

To further enhance the platform, we plan to adopt advanced architectural patterns and methodologies:

- **Domain-Driven Design (DDD):** We aim to model the software to match the domain and its logic closely, improving the alignment between business needs and software implementation.
- **Migations instead of CommandLineRunner:** By using migrations, we can manage database schema changes more effectively, ensuring that our database evolves in a controlled and predictable manner. This approach facilitates version control of the database schema, enables easier rollbacks in case of errors, and enhances collaboration among team members by providing a clear history of changes.

## Git 
### git branch name convenion

feat/{feature_name} - it is for new feature  
chore/{feature_name} - it is for editing feature  
fix/{feature_name} - it is for small bug fix  

#### branch example
feat/coureses   
chore/security  

### git commit description convenion
add:{what did you add}  
change:{what did you change}  

#### commit description example
add: security  
change: lesson structure   

## Screenshots

### Reactive form of course creator
![image](https://github.com/WojciechGos/elearning-platform/assets/36795978/3992e691-a73d-4072-a8d9-5e990e613fc1)  

### Video upload via S3
![image](https://github.com/WojciechGos/elearning-platform/assets/36795978/54adcf69-0e20-48f1-bd2b-d4fdb410aa7d)

### Stripe integration 
![image](https://github.com/WojciechGos/elearning-platform/assets/36795978/6b276f47-3339-406d-8d14-e00cb6e622a4)

![image](https://github.com/WojciechGos/elearning-platform/assets/36795978/21c7e240-c877-4d86-b21c-70bcf9a5475a)


### Oauth

![image](https://github.com/WojciechGos/elearning-platform/assets/36795978/7c885a75-6b64-46a1-83b6-6871f470ebd2)  

![image](https://github.com/WojciechGos/elearning-platform/assets/36795978/7678dd5e-475a-42a3-9824-176b288f1848)






