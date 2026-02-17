# Getting Started

### ⚙️ Technologies Used

* Java 21 (or compatible version)
* Spring Boot
* Maven
* Jackson (JSON parsing)


### How to Run

###  Clone the repository:
git clone https://github.com/devnisaev/geospat-travel.git

cd geospat-travel


### Build the project:
mvn clean install

### Run the application:
mvn spring-boot:run

The application will start on:

 http://localhost:8080

### API Usage
Endpoint
GET /routing/{origin}/{destination}

✅ Example 
### GET http://localhost:8080/routing/CZE/ITA


### Response:

{
"route": ["CZE", "AUT", "ITA"]
}

### ❌ If No Route Exists

Example:

GET /routing/ISL/ITA


# Response:

HTTP 400 Bad Request