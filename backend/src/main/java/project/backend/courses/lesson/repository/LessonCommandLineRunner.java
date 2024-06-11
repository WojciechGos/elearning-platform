package project.backend.courses.lesson.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import project.backend.courses.lesson.model.Lesson;

import java.util.List;

@RequiredArgsConstructor
@Component
@Order(10)
public class LessonCommandLineRunner implements CommandLineRunner {

    private final LessonRepository lessonRepository;
    //    private/courses/0/test.mp4
    private static final List<List<Lesson>> lessonsGroups = List.of(

            // 0
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Java introduction")
                            .description("Introduction to Java programming language")
                            .content("Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.")
                            .videoUrl("private/courses/0/test.mp4")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Java variables")
                            .description("Java variables and data types")
                            .content("Variables are containers for storing data values. In Java, there are different types of variables, for example: String, int, float, double, etc.")
                            .videoUrl("private/courses/0/test1.mp4")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Java operators")
                            .description("Java operators and expressions")
                            .content("Operators are special symbols that perform specific operations on one, two, or three operands, and then return a result.")
                            .videoUrl("private/courses/0/test.mp4")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Java control statements")
                            .description("Java control statements")
                            .content("Control statements are used to control the flow of execution in a program. In Java, we have three types of control statements: selection, iteration, and jump.")
                            .videoUrl("private/courses/0/test.mp4")
                            .build()
            ),
            // 1
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Business introduction")
                            .description("Introduction to business management")
                            .content("Business management is the process of organizing, coordinating, and controlling a business's activities to achieve defined objectives.")
                            .videoUrl("private/courses/0/test.mp4")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(2)
                            .title("Business planning")
                            .description("Business planning and strategy")
                            .content("Business planning is the process of setting goals, defining strategies, and outlining tasks and schedules to achieve those goals.")
                            .videoUrl("private/courses/0/test.mp4")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(3)
                            .title("Business operations")
                            .description("Business operations and management")
                            .content("Business operations are the activities that a business engages in to produce a product or service. Operations management is the process of managing these activities.")
                            .videoUrl("private/courses/0/test.mp4")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(4)
                            .title("Business finance")
                            .description("Business finance and accounting")
                            .content("Business finance is the management of money and other assets. Accounting is the process of recording, summarizing, and analyzing financial transactions.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(5)
                            .title("Business marketing")
                            .description("Business marketing and sales")
                            .content("Business marketing is the process of promoting and selling products or services. Sales is the process of closing deals and generating revenue.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build()
            ),
            // 2
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Python introduction")
                            .description("Introduction to Python programming language")
                            .content("Python is a high-level, interpreted, and general-purpose programming language that is known for its simplicity and readability.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(2)
                            .title("Python variables")
                            .description("Python variables and data types")
                            .content("Variables are containers for storing data values. In Python, there are different types of variables, for example: String, int, float, double, etc.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(3)
                            .title("Python operators")
                            .description("Python operators and expressions")
                            .content("Operators are special symbols that perform specific operations on one, two, or three operands, and then return a result.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(4)
                            .title("Python control statements")
                            .description("Python control statements")
                            .content("Control statements are used to control the flow of execution in a program. In Python, we have three types of control statements: selection, iteration, and jump.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(5)
                            .title("Python functions")
                            .description("Python functions and modules")
                            .content("Functions are blocks of code that perform a specific task. Modules are files that contain functions, classes, and variables that can be imported and used in other programs.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build()
            ),
            // 3
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Data Science introduction")
                            .description("Introduction to data science")
                            .content("Data science is an interdisciplinary field that uses scientific methods, processes, algorithms, and systems to extract knowledge and insights from structured and unstructured data.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(2)
                            .title("Data Science data analysis")
                            .description("Data analysis and visualization")
                            .content("Data analysis is the process of inspecting, cleaning, transforming, and modeling data to discover useful information, suggest conclusions, and support decision-making.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(3)
                            .title("Data Science machine learning")
                            .description("Machine learning and predictive modeling")
                            .content("Machine learning is a subset of artificial intelligence that uses statistical techniques to enable computers to learn and make predictions from data without being explicitly programmed.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(4)
                            .title("Data Science big data")
                            .description("Big data and data engineering")
                            .content("Big data refers to large and complex data sets that are difficult to process using traditional data processing applications. Data engineering is the process of designing, building, and managing data pipelines.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(5)
                            .title("Data Science data science tools")
                            .description("Data science tools and technologies")
                            .content("Data science tools are software applications, programming languages, and libraries that data scientists use to analyze, visualize, and interpret data.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build()
            ),
            // 4
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Web Development introduction")
                            .description("Introduction to web development")
                            .content("Web development is the process of building and maintaining websites and web applications. It includes web design, web content development, client-side/server-side scripting, and network security configuration.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(2)
                            .title("Web Development front-end")
                            .description("Front-end development and design")
                            .content("Front-end development is the practice of creating the user interface and user experience of a website or web application. It involves HTML, CSS, and JavaScript.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(3)
                            .title("Web Development back-end")
                            .description("Back-end development and databases")
                            .content("Back-end development is the practice of creating the server-side logic and database of a website or web application. It involves programming languages like PHP, Python, and Ruby.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(4)
                            .title("Web Development web frameworks")
                            .description("Web frameworks and libraries")
                            .content("Web frameworks are software libraries that provide a standard way to build and deploy web applications. They include tools for routing, templating, and database access.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(5)
                            .title("Web Development web security")
                            .description("Web security and performance")
                            .content("Web security is the practice of protecting websites and web applications from cyber threats. Web performance is the practice of optimizing websites and web applications for speed and efficiency.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build()
            ),
            // 5
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Mobile App Development introduction")
                            .description("Introduction to mobile app development")
                            .content("Mobile app development is the process of creating software applications that run on mobile devices like smartphones and tablets. It involves designing, building, and testing mobile apps for iOS and Android platforms.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(2)
                            .title("Mobile App Development app design")
                            .description("Mobile app design and user experience")
                            .content("Mobile app design is the process of creating the user interface and user experience of a mobile app. It involves wireframing, prototyping, and usability testing.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(3)
                            .title("Mobile App Development app development")
                            .description("Mobile app development and programming")
                            .content("Mobile app development is the process of writing code to build the functionality of a mobile app. It involves programming languages like Swift, Kotlin, and React Native.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(4)
                            .title("Mobile App Development app testing")
                            .description("Mobile app testing and deployment")
                            .content("Mobile app testing is the process of evaluating the functionality, usability, and performance of a mobile app. Deployment is the process of releasing the app to the app store.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(5)
                            .title("Mobile App Development app marketing")
                            .description("Mobile app marketing and monetization")
                            .content("Mobile app marketing is the process of promoting and selling a mobile app to users. Monetization is the process of generating revenue from the app through ads, in-app purchases, and subscriptions.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build()
            ),
            // 6
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Artificial Intelligence introduction")
                            .description("Introduction to artificial intelligence")
                            .content("Artificial intelligence is the simulation of human intelligence processes by machines, especially computer systems. It involves learning, reasoning, problem-solving, perception, and language understanding.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(2)
                            .title("Artificial Intelligence machine learning")
                            .description("Machine learning and deep learning")
                            .content("Machine learning is a subset of artificial intelligence that uses statistical techniques to enable machines to learn and make predictions from data. Deep learning is a subset of machine learning that uses neural networks to model and analyze complex patterns.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(3)
                            .title("Artificial Intelligence natural language processing")
                            .description("Natural language processing and chatbots")
                            .content("Natural language processing is the ability of a computer program to understand, interpret, and generate human language. Chatbots are AI-powered programs that can simulate conversations with users.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(4)
                            .title("Artificial Intelligence computer vision")
                            .description("Computer vision and image recognition")
                            .content("Computer vision is the ability of a computer program to interpret and understand visual information from the real world. Image recognition is the process of identifying and classifying objects in images.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(5)
                            .title("Artificial Intelligence AI ethics")
                            .description("AI ethics and responsible AI")
                            .content("AI ethics is the study of the moral and ethical implications of artificial intelligence. Responsible AI is the practice of developing AI systems that are fair, transparent, and accountable.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build()
            ),
            // 7
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Cloud Computing introduction")
                            .description("Introduction to cloud computing")
                            .content("Cloud computing is the delivery of computing services like servers, storage, databases, networking, software, and analytics over the internet. It offers faster innovation, flexible resources, and economies of scale.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(2)
                            .title("Cloud Computing cloud services")
                            .description("Cloud services and deployment models")
                            .content("Cloud services are the different types of services that are delivered over the cloud, like infrastructure as a service (IaaS), platform as a service (PaaS), and software as a service (SaaS). Deployment models are the different ways that cloud services can be deployed, like public cloud, private cloud, and hybrid cloud.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(3)
                            .title("Cloud Computing cloud security")
                            .description("Cloud security and compliance")
                            .content("Cloud security is the practice of protecting cloud-based data, applications, and infrastructure from cyber threats. Compliance is the process of adhering to legal, regulatory, and industry standards.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(4)
                            .title("Cloud Computing cloud migration")
                            .description("Cloud migration and management")
                            .content("Cloud migration is the process of moving data, applications, and workloads to the cloud. Cloud management is the process of monitoring, optimizing, and securing cloud-based resources.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(5)
                            .title("Cloud Computing cloud computing trends")
                            .description("Cloud computing trends and future")
                            .content("Cloud computing trends are the emerging technologies and practices that are shaping the future of cloud computing. The future of cloud computing is expected to include serverless computing, edge computing, and quantum computing.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build()
            ),
            // 8
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Cybersecurity introduction")
                            .description("Introduction to cybersecurity")
                            .content("Cybersecurity is the practice of protecting computer systems, networks, and data from cyber threats like hacking, malware, and data breaches. It involves implementing security measures, monitoring for threats, and responding to security incidents.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(2)
                            .title("Cybersecurity network security")
                            .description("Network security and encryption")
                            .content("Network security is the practice of securing computer networks from unauthorized access, misuse, and modification. Encryption is the process of encoding data to prevent unauthorized access.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(3)
                            .title("Cybersecurity information security")
                            .description("Information security and risk management")
                            .content("Information security is the practice of protecting information from unauthorized access, use, disclosure, disruption, modification, or destruction. Risk management is the process of identifying, assessing, and mitigating risks to information security.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(4)
                            .title("Cybersecurity cybersecurity tools")
                            .description("Cybersecurity tools and technologies")
                            .content("Cybersecurity tools are software applications and hardware devices that help organizations protect their computer systems, networks, and data from cyber threats. They include firewalls, antivirus software, and intrusion detection systems.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(5)
                            .title("Cybersecurity cybersecurity best practices")
                            .description("Cybersecurity best practices and compliance")
                            .content("Cybersecurity best practices are the guidelines and procedures that organizations follow to protect their computer systems, networks, and data from cyber threats. Compliance is the process of adhering to legal, regulatory, and industry standards for cybersecurity.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build()
            ),
            // 9
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("UI/UX Design introduction")
                            .description("Introduction to UI/UX design")
                            .content("UI/UX design is the process of creating user interfaces and user experiences for websites, web applications, and mobile apps. It involves wireframing, prototyping, and usability testing to create intuitive and engaging designs.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(2)
                            .title("UI/UX Design user research")
                            .description("User research and personas")
                            .content("User research is the process of understanding the needs, behaviors, and motivations of users to inform the design of products and services. Personas are fictional characters that represent different user types and help designers empathize with users.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(3)
                            .title("UI/UX Design information architecture")
                            .description("Information architecture and navigation")
                            .content("Information architecture is the organization and structure of content on a website or app. Navigation is the way users move through and interact with a website or app to find information and complete tasks.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(4)
                            .title("UI/UX Design wireframing")
                            .description("Wireframing and prototyping")
                            .content("Wireframing is the process of creating a visual blueprint of a website or app to show the layout and functionality. Prototyping is the process of creating interactive mockups to test and refine the design.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(5)
                            .title("UI/UX Design usability testing")
                            .description("Usability testing and user feedback")
                            .content("Usability testing is the process of evaluating a website or app by testing it with real users to identify usability issues and gather feedback. User feedback is the input and insights that users provide about their experience with a website or app.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build()
            ),
            // 10
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Java Advanced")
                            .description("Advanced Java programming concepts")
                            .content("Advanced Java programming concepts like multithreading, collections, and exception handling")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(2)
                            .title("Java Spring Framework")
                            .description("Introduction to Spring Framework")
                            .content("Introduction to Spring Framework and Spring Boot")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(3)
                            .title("Java Hibernate")
                            .description("Introduction to Hibernate ORM")
                            .content("Introduction to Hibernate ORM and JPA")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(4)
                            .title("Java Microservices")
                            .description("Introduction to Microservices architecture")
                            .content("Introduction to Microservices architecture and design patterns")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(5)
                            .title("Java Testing")
                            .description("Introduction to Java testing frameworks")
                            .content("Introduction to Java testing frameworks like JUnit and Mockito")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build()
            ),
            // 11
            List.of(
                    Lesson.builder()
                            .lessonNumber(1)
                            .title("Business decision making")
                            .description("Business decision making and problem-solving")
                            .content("Business decision making is the process of selecting the best course of action from a set of alternatives. Problem-solving is the process of identifying and solving problems to achieve business objectives.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(2)
                            .title("Business leadership")
                            .description("Business leadership and management")
                            .content("Business leadership is the ability to inspire and motivate employees to achieve business goals. Management is the process of planning, organizing, and controlling business activities to achieve defined objectives.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(3)
                            .title("Business innovation")
                            .description("Business innovation and creativity")
                            .content("Business innovation is the process of introducing new ideas, products, or processes to improve business performance. Creativity is the ability to generate new and original ideas.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(4)
                            .title("Business communication")
                            .description("Business communication and negotiation")
                            .content("Business communication is the exchange of information between people within and outside an organization. Negotiation is the process of reaching agreements through discussion and compromise.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(5)
                            .title("Business entrepreneurship")
                            .description("Business entrepreneurship and startups")
                            .content("Business entrepreneurship is the process of starting and growing a new business venture. Startups are new companies that are founded to develop and market innovative productsor services.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(6)
                            .title("Strategic Management")
                            .description("Developing Business Strategies")
                            .content("Strategic management involves the formulation and implementation of plans and initiatives to achieve long-term goals and objectives. It encompasses environmental scanning, strategy formulation, strategy implementation, and evaluation.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(7)
                            .title("Financial Management")
                            .description("Managing Business Finances")
                            .content("Financial management is the process of planning, organizing, directing, and controlling the financial activities of an organization. It includes budgeting, forecasting, cash flow management, and financial analysis.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(8)
                            .title("Market Analysis")
                            .description("Understanding Market Trends")
                            .content("Market analysis involves studying and interpreting market trends, customer behavior, competitor strategies, and other factors affecting the demand for products or services. It helps businesses make informed decisions regarding marketing strategies and product development.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(9)
                            .title("Human Resource Management")
                            .description("Managing Human Capital")
                            .content("Human resource management focuses on the recruitment, training, development, and retention of employees to achieve organizational goals. It includes activities such as hiring, performance evaluation, compensation management, and employee relations.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build(),
                    Lesson.builder()
                            .lessonNumber(10)
                            .title("Supply Chain Management")
                            .description("Optimizing Supply Chains")
                            .content("Supply chain management involves the planning, sourcing, production, and distribution of goods and services to meet customer demand efficiently and effectively. It aims to minimize costs, maximize efficiency, and enhance customer satisfaction.")
                            .videoUrl("https://www.youtube.com/watch?v=grEKMHGYyns")
                            .build()
            )
    );

    public static List<Lesson> getLessonsPack(int pack) {
        return lessonsGroups.get(pack);
    }

    @Override
    public void run(String... args) throws Exception {
        for (List<Lesson> lessonsGroup : lessonsGroups) {
            lessonRepository.saveAll(lessonsGroup);
        }
    }
}
