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

    private static final List<List<Lesson>> lessonsGroups = List.of(

            // 0
            List.of(
                    new Lesson("Java introduction", "Introduction to Java programming language", "Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible.", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Java variables", "Java variables and data types", "Variables are containers for storing data values. In Java, there are different types of variables, for example: String, int, float, double, etc.", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Java operators", "Java operators and expressions", "Operators are special symbols that perform specific operations on one, two, or three operands, and then return a result.", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Java control statements", "Java control statements", "Control statements are used to control the flow of execution in a program. In Java, we have three types of control statements: selection, iteration, and jump.", 4, "https://www.youtube.com/watch?v=grEKMHGYyns")
            ),
            // 1
            List.of(
                    new Lesson("Business introduction", "Introduction to business management", "Business management is the process of organizing, coordinating, and controlling a business's activities to achieve defined objectives.", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Business planning", "Business planning and strategy", "Business planning is the process of setting goals, defining strategies, and outlining tasks and schedules to achieve those goals.", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Business operations", "Business operations and management", "Business operations are the activities that a business engages in to produce a product or service. Operations management is the process of managing these activities.", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Business finance", "Business finance and accounting", "Business finance is the management of money and other assets. Accounting is the process of recording, summarizing, and analyzing financial transactions.", 4, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Business marketing", "Business marketing and sales", "Business marketing is the process of promoting and selling products or services. Sales is the process of closing deals and generating revenue.", 5, "https://www.youtube.com/watch?v=grEKMHGYyns")
            ),
            // 2
            List.of(
                    new Lesson("Python introduction", "Introduction to Python programming language", "Python is a high-level, interpreted, and general-purpose programming language that is known for its simplicity and readability.", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Python variables", "Python variables and data types", "Variables are containers for storing data values. In Python, there are different types of variables, for example: String, int, float, double, etc.", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Python operators", "Python operators and expressions", "Operators are special symbols that perform specific operations on one, two, or three operands, and then return a result.", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Python control statements", "Python control statements", "Control statements are used to control the flow of execution in a program. In Python, we have three types of control statements: selection, iteration, and jump.", 4, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Python functions", "Python functions and modules", "Functions are blocks of code that perform a specific task. Modules are files that contain functions, classes, and variables that can be imported and used in other programs.", 5, "https://www.youtube.com/watch?v=grEKMHGYyns")
            ),
            // 3
            List.of(
                    new Lesson("Data Science introduction", "Introduction to data science", "Data science is an interdisciplinary field that uses scientific methods, processes, algorithms, and systems to extract knowledge and insights from structured and unstructured data.", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Data Science data analysis", "Data analysis and visualization", "Data analysis is the process of inspecting, cleaning, transforming, and modeling data to discover useful information, suggest conclusions, and support decision-making.", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Data Science machine learning", "Machine learning and predictive modeling", "Machine learning is a subset of artificial intelligence that uses statistical techniques to enable computers to learn and make predictions from data without being explicitly programmed.", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Data Science big data", "Big data and data engineering", "Big data refers to large and complex data sets that are difficult to process using traditional data processing applications. Data engineering is the process of designing, building, and managing data pipelines.", 4, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Data Science data science tools", "Data science tools and technologies", "Data science tools are software applications, programming languages, and libraries that data scientists use to analyze, visualize, and interpret data.", 5, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Data Science data science tools", "Data science tools and technologies", "Data science tools are software applications, programming languages, and libraries that data scientists use to analyze, visualize, and interpret data.", 5, "https://www.youtube.com/watch?v=grEKMHGYyns")
            ),
            // 4
            List.of(
                    new Lesson("Web Development introduction", "Introduction to web development", "Web development is the process of building and maintaining websites and web applications. It includes web design, web content development, client-side/server-side scripting, and network security configuration.", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Web Development front-end", "Front-end development and design", "Front-end development is the practice of creating the user interface and user experience of a website or web application. It involves HTML, CSS, and JavaScript.", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Web Development back-end", "Back-end development and databases", "Back-end development is the practice of creating the server-side logic and database of a website or web application. It involves programming languages like PHP, Python, and Ruby.", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Web Development web frameworks", "Web frameworks and libraries", "Web frameworks are software libraries that provide a standard way to build and deploy web applications. They include tools for routing, templating, and database access.", 4, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Web Development web security", "Web security and performance", "Web security is the practice of protecting websites and web applications from cyber threats. Web performance is the practice of optimizing websites and web applications for speed and efficiency.", 5, "https://www.youtube.com/watch?v=grEKMHGYyns")
            ),
            // 5
            List.of(
                    new Lesson("Mobile App Development introduction", "Introduction to mobile app development", "Mobile app development is the process of creating software applications that run on mobile devices like smartphones and tablets. It involves designing, building, and testing mobile apps for iOS and Android platforms.", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Mobile App Development app design", "Mobile app design and user experience", "Mobile app design is the process of creating the user interface and user experience of a mobile app. It involves wireframing, prototyping, and usability testing.", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Mobile App Development app development", "Mobile app development and programming", "Mobile app development is the process of writing code to build the functionality of a mobile app. It involves programming languages like Swift, Kotlin, and React Native.", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Mobile App Development app testing", "Mobile app testing and deployment", "Mobile app testing is the process of evaluating the functionality, usability, and performance of a mobile app. Deployment is the process of releasing the app to the app store.", 4, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Mobile App Development app marketing", "Mobile app marketing and monetization", "Mobile app marketing is the process of promoting and selling a mobile app to users. Monetization is the process of generating revenue from the app through ads, in-app purchases, and subscriptions.", 5, "https://www.youtube.com/watch?v=grEKMHGYyns")
            ),
            // 6
            List.of(
                    new Lesson("Artificial Intelligence introduction", "Introduction to artificial intelligence", "Artificial intelligence is the simulation of human intelligence processes by machines, especially computer systems. It involves learning, reasoning, problem-solving, perception, and language understanding.", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Artificial Intelligence machine learning", "Machine learning and deep learning", "Machine learning is a subset of artificial intelligence that uses statistical techniques to enable machines to learn and make predictions from data. Deep learning is a subset of machine learning that uses neural networks to model and analyze complex patterns.", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Artificial Intelligence natural language processing", "Natural language processing and chatbots", "Natural language processing is the ability of a computer program to understand, interpret, and generate human language. Chatbots are AI-powered programs that can simulate conversations with users.", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Artificial Intelligence computer vision", "Computer vision and image recognition", "Computer vision is the ability of a computer program to interpret and understand visual information from the real world. Image recognition is the process of identifying and classifying objects in images.", 4, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Artificial Intelligence AI ethics", "AI ethics and responsible AI", "AI ethics is the study of the moral and ethical implications of artificial intelligence. Responsible AI is the practice of developing AI systems that are fair, transparent, and accountable.", 5, "https://www.youtube.com/watch?v=grEKMHGYyns")

            ),
            // 7
            List.of(
                    new Lesson("Cloud Computing introduction", "Introduction to cloud computing", "Cloud computing is the delivery of computing services like servers, storage, databases, networking, software, and analytics over the internet. It offers faster innovation, flexible resources, and economies of scale.", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Cloud Computing cloud services", "Cloud services and deployment models", "Cloud services are the different types of services that are delivered over the cloud, like infrastructure as a service (IaaS), platform as a service (PaaS), and software as a service (SaaS). Deployment models are the different ways that cloud services can be deployed, like public cloud, private cloud, and hybrid cloud.", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Cloud Computing cloud security", "Cloud security and compliance", "Cloud security is the practice of protecting cloud-based data, applications, and infrastructure from cyber threats. Compliance is the process of adhering to legal, regulatory, and industry standards.", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Cloud Computing cloud migration", "Cloud migration and management", "Cloud migration is the process of moving data, applications, and workloads to the cloud. Cloud management is the process of monitoring, optimizing, and securing cloud-based resources.", 4, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Cloud Computing cloud computing trends", "Cloud computing trends and future", "Cloud computing trends are the emerging technologies and practices that are shaping the future of cloud computing. The future of cloud computing is expected to include serverless computing, edge computing, and quantum computing.", 5, "https://www.youtube.com/watch?v=grEKMHGYyns")
            ),
            // 8
            List.of(
                    new Lesson("Cybersecurity introduction", "Introduction to cybersecurity", "Cybersecurity is the practice of protecting computer systems, networks, and data from cyber threats like hacking, malware, and data breaches. It involves implementing security measures, monitoring for threats, and responding to security incidents.", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Cybersecurity network security", "Network security and encryption", "Network security is the practice of securing computer networks from unauthorized access, misuse, and modification. Encryption is the process of encoding data to prevent unauthorized access.", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Cybersecurity information security", "Information security and risk management", "Information security is the practice of protecting information from unauthorized access, use, disclosure, disruption, modification, or destruction. Risk management is the process of identifying, assessing, and mitigating risks to information security.", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Cybersecurity cybersecurity tools", "Cybersecurity tools and technologies", "Cybersecurity tools are software applications and hardware devices that help organizations protect their computer systems, networks, and data from cyber threats. They include firewalls, antivirus software, and intrusion detection systems.", 4, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Cybersecurity cybersecurity best practices", "Cybersecurity best practices and compliance", "Cybersecurity best practices are the guidelines and procedures that organizations follow to protect their computer systems, networks, and data from cyber threats. Compliance is the process of adhering to legal, regulatory, and industry standards for cybersecurity.", 5, "https://www.youtube.com/watch?v=grEKMHGYyns")
            ),
            // 9
            List.of(
                    new Lesson("UI/UX Design introduction", "Introduction to UI/UX design", "UI/UX design is the process of creating user interfaces and user experiences for websites, web applications, and mobile apps. It involves wireframing, prototyping, and usability testing to create intuitive and engaging designs.", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("UI/UX Design user research", "User research and personas", "User research is the process of understanding the needs, behaviors, and motivations of users to inform the design of products and services. Personas are fictional characters that represent different user types and help designers empathize with users.", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("UI/UX Design information architecture", "Information architecture and navigation", "Information architecture is the organization and structure of content on a website or app. Navigation is the way users move through and interact with a website or app to find information and complete tasks.", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("UI/UX Design wireframing", "Wireframing and prototyping", "Wireframing is the process of creating a visual blueprint of a website or app to show the layout and functionality. Prototyping is the process of creating interactive mockups to test and refine the design.", 4, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("UI/UX Design usability testing", "Usability testing and user feedback", "Usability testing is the process of evaluating a website or app by testing it with real users to identify usability issues and gather feedback. User feedback is the input and insights that users provide about their experience with a website or app.", 5, "https://www.youtube.com/watch?v=grEKMHGYyns")
            ),
            // 10
            List.of(
                    new Lesson("Java Advanced", "Advanced Java programming concepts", "Advanced Java programming concepts like multithreading, collections, and exception handling", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Java Spring Framework", "Introduction to Spring Framework", "Introduction to Spring Framework and Spring Boot", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Java Hibernate", "Introduction to Hibernate ORM", "Introduction to Hibernate ORM and JPA", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Java Microservices", "Introduction to Microservices architecture", "Introduction to Microservices architecture and design patterns", 4, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Java Testing", "Introduction to Java testing frameworks", "Introduction to Java testing frameworks like JUnit and Mockito", 5, "https://www.youtube.com/watch?v=grEKMHGYyns")
            ),
            // 11
            List.of(
                    new Lesson("Business decision making", "Business decision making and problem-solving", "Business decision making is the process of selecting the best course of action from a set of alternatives. Problem-solving is the process of identifying and solving problems to achieve business objectives.", 1, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Business leadership", "Business leadership and management", "Business leadership is the ability to inspire and motivate employees to achieve business goals. Management is the process of planning, organizing, and controlling business activities to achieve defined objectives.", 2, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Business innovation", "Business innovation and creativity", "Business innovation is the process of introducing new ideas, products, or processes to improve business performance. Creativity is the ability to generate new and original ideas.", 3, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Business communication", "Business communication and negotiation", "Business communication is the exchange of information between people within and outside an organization. Negotiation is the process of reaching agreements through discussion and compromise.", 4, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Business entrepreneurship", "Business entrepreneurship and startups", "Business entrepreneurship is the process of starting and growing a new business venture. Startups are new companies that are founded to develop and market innovative products or services.", 5, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Strategic Management", "Developing Business Strategies", "Strategic management involves the formulation and implementation of plans and initiatives to achieve long-term goals and objectives. It encompasses environmental scanning, strategy formulation, strategy implementation, and evaluation.", 6, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Financial Management", "Managing Business Finances", "Financial management is the process of planning, organizing, directing, and controlling the financial activities of an organization. It includes budgeting, forecasting, cash flow management, and financial analysis.", 7, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Market Analysis", "Understanding Market Trends", "Market analysis involves studying and interpreting market trends, customer behavior, competitor strategies, and other factors affecting the demand for products or services. It helps businesses make informed decisions regarding marketing strategies and product development.", 8, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Human Resource Management", "Managing Human Capital", "Human resource management focuses on the recruitment, training, development, and retention of employees to achieve organizational goals. It includes activities such as hiring, performance evaluation, compensation management, and employee relations.", 9, "https://www.youtube.com/watch?v=grEKMHGYyns"),
                    new Lesson("Supply Chain Management", "Optimizing Supply Chains", "Supply chain management involves the planning, sourcing, production, and distribution of goods and services to meet customer demand efficiently and effectively. It aims to minimize costs, maximize efficiency, and enhance customer satisfaction.", 10, "https://www.youtube.com/watch?v=grEKMHGYyns")
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
