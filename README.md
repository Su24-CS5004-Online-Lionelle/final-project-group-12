# Final Project for CS 5004 - (APPLICATION NAME/Update)

(remove this and add your sections/elements)
This readme should contain the following information: 

* The group member's names and link to their personal githubs
  Wing Yee Li https://github.com/liwingy
  
* The application name and a brief description of the application
  The application name is G12 Zoo Management System. This is a comprehensive application designed to facilitate efficient zoo management using a Model-View-Controller (MVC) architecture with Java Swing for a rich and interactive user interface. Key features include Animal Management for adding, editing, and deleting animal records, managing species, feeding times, and health records; Enclosure Management for monitoring and maintaining enclosure conditions; Employee Management for handling employee roles, schedules, and records; Visitor Information for tracking visitor details and feedback; and Help and Support sections for FAQs and assistance. This system is designed to streamline zoo operations, improve data accuracy, and enhance overall management efficiency, making it a valuable tool for zoo administrators, keepers, and support staff.
  
* Links to design documents
https://docs.google.com/document/d/1O_sO9AKvfFi_5G2hLLhuA3nowhppxMk9Kv_AlIFhAiw/edit?usp=sharing

* Instructions on how to run the application
* 
Please use the goole doc link below to see user manual

* https://docs.google.com/document/d/1gYCBzU1Terovg1x306U4hsmRh4pdsWT0ZeHF3ABdbUE/edit?usp=sharing

Pre design UML
```mermaid
classDiagram

    %% Model
    class IAnimals {
        +String getName()
        +int getAge()
        +String getSpecies()
    }
    
    class Animals {
        -String name
        -int age
        -String species
        +String getName()
        +int getAge()
        +String getSpecies()
        +void setName(String name)
        +void setAge(int age)
        +void setSpecies(String species)
    }
    
    IAnimals <|-- Animals

    class IEnclosures {
        +String getName()
        +String getType()
        +int getCapacity()
    }
    
    class Enclosures {
        -String name
        -String type
        -int capacity
        +String getName()
        +String getType()
        +int getCapacity()
        +void setName(String name)
        +void setType(String type)
        +void setCapacity(int capacity)
    }
    
    IEnclosures <|-- Enclosures

    class IEmployees {
        +String getEmployeeID()
        +String getName()
        +String getPosition()
    }
    
    class Employees {
        -String employeeID
        -String name
        -String position
        +String getEmployeeID()
        +String getName()
        +String getPosition()
        +void setEmployeeID(String employeeID)
        +void setName(String name)
        +void setPosition(String position)
    }
    
    IEmployees <|-- Employees

    class VisitorsInformation {
        -String visitorID
        -String name
        -String contactInfo
        +String getVisitorID()
        +String getName()
        +String getContactInfo()
        +void setVisitorID(String visitorID)
        +void setName(String name)
        +void setContactInfo(String contactInfo)
    }

    %% View
    class Windows {
        +JFrame mainWindow
        +JPanel animalPanel
        +JPanel enclosurePanel
        +JPanel employeePanel
        +JPanel visitorPanel
    }

    class Buttons {
        +JButton addButton
        +JButton updateButton
        +JButton deleteButton
    }

    class ListWindows {
        +JList animalList
        +JList enclosureList
        +JList employeeList
        +JList visitorList
    }

    %% Controller
    class Controller {
        +IAnimals animals
        +IEnclosures enclosures
        +IEmployees employees
        +VisitorsInformation visitors
        +Windows windows
        +void initialize()
        +void addAnimal()
        +void updateAnimal()
        +void deleteAnimal()
        +void addEnclosure()
        +void updateEnclosure()
        +void deleteEnclosure()
        +void addEmployee()
        +void updateEmployee()
        +void deleteEmployee()
        +void addVisitor()
        +void updateVisitor()
        +void deleteVisitor()
    }

    %% App
    class App {
        +Controller controller
        +void main(String[] args)
    }

    %% Maintenance
    class Maintenance {
        +void scheduleMaintenance()
        +void performMaintenance()
    }

    Relationships
    Windows o-- Buttons
    Windows o-- ListWindows
    Controller *-- IAnimals
    Controller *-- IEnclosures
    Controller *-- IEmployees
    Controller *-- VisitorsInformation
    Controller *-- Windows
    App *-- Controller
    Maintenance <-- App
```

Post design UML
```mermaid
classDiagram

    %% Model
    class IAnimalNew {
        +String getName()
        +AnimalCategory getCategory()
        +FoodType getFoodType()
        +Sex getSex()
    }
    
    class AnimalNew {
        -String name
        -AnimalCategory category
        -FoodType foodType
        -Sex sex
        +String getName()
        +AnimalCategory getCategory()
        +FoodType getFoodType()
        +Sex getSex()
        +void setName(String name)
        +void setCategory(AnimalCategory category)
        +void setFoodType(FoodType foodType)
        +void setSex(Sex sex)
    }
    
    IAnimalNew <|-- AnimalNew

    class IEnclosure {
        +void addAnimal(AnimalNew animal)
        +void removeAnimal(AnimalNew animal)
    }
    
    class EnclosureImpl {
        -List~AnimalNew~ animals
        +void addAnimal(AnimalNew animal)
        +void removeAnimal(AnimalNew animal)
        +List~AnimalNew~ getAnimals()
    }
    
    IEnclosure <|-- EnclosureImpl

    class IEmployee {
        +String getName()
        +String getRole()
        +String getShift()
        +List~String~ getResponsibilities()
    }
    
    class EmployeeImpl {
        -String name
        -String role
        -String shift
        -List~String~ responsibilities
        +String getName()
        +String getRole()
        +String getShift()
        +void setName(String name)
        +void setRole(String role)
        +void setShift(String shift)
        +void addResponsibility(String responsibility)
    }
    
    IEmployee <|-- EmployeeImpl

    class EmployeeManagement {
        -Map~String, EmployeeImpl~ employees
        +void addEmployee(EmployeeImpl employee)
        +void removeEmployee(String name)
        +EmployeeImpl getEmployee(String name)
        +void updateEmployee(String name, EmployeeImpl updatedEmployee)
        +void scheduleShift(String name, String shift)
        +void assignResponsibility(String name, String responsibility)
        +void printEmployeeDetails(String name)
        +Set~String~ getEmployeeNames()
    }

    class Visitor {
        -List~Visit~ visits
        -Map~String, Double~ pricing
        +void addVisit(String date, int entryTime, String category, int duration, Integer animalFeedback, Integer cleanlinessFeedback, Integer pricingFeedback)
        +List~Visit~ getVisits()
        +Visit getVisit(int index)
        +int getTotalVisits()
        +int getVisitsCountByDate(String date)
        +int getPeakHourByDate(String date)
        +double getAverageAnimalFeedbackByDate(String date)
        +double getAverageCleanlinessFeedbackByDate(String date)
        +double getAveragePricingFeedbackByDate(String date)
        +void setPricing(Map~String, Double~ pricing)
        +double getRevenueByDate(String date)
    }

    class Visit {
        -String date
        -int entryTime
        -String category
        -int duration
        -Integer animalFeedback
        -Integer cleanlinessFeedback
        -Integer overallFeedback
        +Visit(String date, int entryTime, String category, int duration, Integer animalFeedback, Integer cleanlinessFeedback, Integer overallFeedback)
        +String getDate()
        +int getEntryTime()
        +String getCategory()
        +int getDuration()
        +Integer getAnimalFeedback()
        +Integer getCleanlinessFeedback()
        +Integer getOverallFeedback()
        +String toString()
    }

    class FeedingTime {
        -int timeInMinutes
        +FeedingTime(int hour, int minute)
        +int getTimeInMinutes()
        +void setTimeInMinutes(int timeInMinutes)
        +String toString()
    }

    class EnclosureType {
        <<enumeration>>
        TEMPERATE
        TROPICAL
        DESERT
        ARCTIC
    }

    %% View
    class AnimalView {
        +void displayAnimalDetails(AnimalNew animal)
    }

    class EnclosureManagementView {
        +void displayEnclosureDetails(EnclosureImpl enclosure)
    }

    class EmployeeManagementView {
        +void displayEmployeeDetails(EmployeeImpl employee)
    }

    class VisitorView {
        +void displayVisitorDetails(Visitor visitor)
    }

    class View {
        +void display()
        +void close()
    }

    %% Controller
    class Controller {
        -EmployeeManagement employeeManagement
        -EnclosureImpl enclosureManagement
        -Visitor visitorManagement
        +Controller(EmployeeManagement employeeManagement, EnclosureImpl enclosureManagement, Visitor visitorManagement)
        +void addAnimal(AnimalNew animal)
        +void removeAnimal(int id)
        +void editAnimal(AnimalNew updatedAnimal)
        +void addEmployee(EmployeeImpl employee)
        +void removeEmployee(String name)
        +void updateEmployee(String name, EmployeeImpl updatedEmployee)
        +void addVisit(String date, int entryTime, String category, int duration, int animalFeedback, int cleanlinessFeedback, int pricingFeedback)
        +List~Visit~ getVisits()
    }

    %% App
    class ZooApp {
        -Controller appController
        +static void main(String[] args)
    }

    Relationships
    AnimalNew --> IAnimalNew : implements
    EnclosureImpl --> IEnclosure : implements
    EmployeeImpl --> IEmployee : implements
    EmployeeManagement --> EmployeeImpl : manages
    Visitor --> Visit : contains
    EnclosureImpl --> AnimalNew : contains
    EnclosureImpl --> EnclosureType : uses
    Visit --> String
    Visit --> int
    Visit --> Integer
    FeedingTime --> int

    Controller --> EmployeeManagement : controls
    Controller --> EnclosureImpl : controls
    Controller --> Visitor : controls
    AnimalView --> View : inherits
    EnclosureManagementView --> View : inherits
    EmployeeManagementView --> View : inherits
    VisitorView --> View : inherits
    ZooApp --> Controller : uses
```



Ask yourself, if you started here in the readme, would you have what you need to work on this project and/or use the application?

If we started with the README that includes the UML diagram we provided, we'd likely have some questions about whether it gives us everything we need to work on this project or use the application effectively. The UML diagram is helpful for getting an overview of the structure—showing how classes like `Controller`, `AnimalView`, and `EmployeeManagement` are connected. However, while it provides a good starting point, it doesn’t explain the reasoning behind the setup or how these components are meant to interact in practice.

For someone looking to contribute to the project, the diagram lists the classes, methods, and attributes, which is useful, but it lacks context. We’d need more information on what each method is supposed to do and how all these parts work together during runtime. The diagram offers a roadmap, but it doesn’t really tell us how to navigate it. Additionally, to get started with coding, we’d need clear setup instructions—how to set up the environment, what dependencies are required, and how to run the application. The diagram doesn’t cover this, so we’d definitely need more guidance in the README. If we wanted to contribute, we’d also need to know the project’s coding standards and practices. The UML shows us the structure, but we’d need guidelines on how to add or change things without breaking the project.

From a user’s perspective, the UML diagram doesn’t provide much insight into how the app can be used. We’d need a straightforward explanation of the app’s features—what it can do, like managing zoo animals, tracking visitors, or scheduling employees. Even if we know what the app is capable of, we’d still need instructions on how to actually use it. Step-by-step instructions, screenshots, or a user manual would be essential for understanding how to interact with the app’s interface. Moreover, we’d need clear instructions on how to install the app, set up any necessary software, and get it running. Without this, knowing the structure alone isn’t enough to make the app functional.

Overall, while the UML diagram is a good starting point, it doesn’t provide everything we’d need to effectively work on or use the application. We’d need more detailed documentation in the README, including the purpose of the app, examples of how to use it, and troubleshooting tips in case we run into problems. The diagram gives us an outline, but we’d need more comprehensive guidance and context to fully dive in and be productive with this project.
