# Weather Information REST API  

A Spring Boot application that provides weather information for a given pincode and date using OpenWeatherMap APIs.  

---

## **Technologies Used**  
- **Java**: 17  
- **Spring Boot**: 3.1.0  
- **Spring Data JPA**: For database operations.  
- **H2 Database**: 2.1.214 (In-memory database for development).  
- **Lombok**: 1.18.28 (Reduces boilerplate code).  
- **RestTemplate**: For consuming OpenWeatherMap APIs.  
- **Hibernate Validator**: 8.0.1.Final (Bean Validation).  
- **OpenWeatherMap APIs**:  
  - Geocoding API (v1.0): Converts pincode to latitude/longitude.  
  - Current Weather API (v2.5): Fetches weather data.  
- **Testing**:  
  - JUnit 5  
  - Mockito  

---

## **Prerequisites**  
- Java 17 JDK installed.  
- Maven for dependency management.  
- OpenWeatherMap API key (sign up [here](https://openweathermap.org/api)).  
- Postman/Swagger for testing endpoints.  

---

## **Getting Started**  

### **1. Clone the Repository**  
```bash  
git clone https://github.com/nageshram/weatherInfoForPincode.git  
cd weatherInfoForPincode
```  

### **2. Configure API Key**  
Update `src/main/resources/application.properties`:  
```properties  
openweather.apiKey=your_api_key_here  
openweather.geocoding.country=IN  
```  

### **3. Build and Run**  
```bash  
mvn clean install  
mvn spring-boot:run  
```  

### **4. Access Endpoints**  
- **API Endpoint**:  
  ```  
  GET http://localhost:8080/api/weather?pincode=411014&forDate=2023-10-15  
  ```  
- **H2 Database Console**:  
  - URL: `http://localhost:8080/h2-console`  
  - JDBC URL: `jdbc:h2:mem:weatherdb`  
  - Credentials: `sa` (username), no password.  

---

## **Approach Followed**  
1. **REST API Design**:  
   - Single endpoint to fetch weather data by pincode and date.  
   - Optimized to avoid redundant API calls by caching location and weather data in H2.  

2. **Layered Architecture**:  
   - **Controller**: Handles HTTP requests/responses.  
   - **Service**: Business logic for fetching/saving data.  
   - **Repository**: JPA interfaces for database operations.  
   - **DTOs**: Maps OpenWeatherMap API responses.  
   - **Config**: Manages RestTemplate and API properties.  

3. **Integration with OpenWeatherMap**:  
   - **Geocoding API**: Converts pincode to latitude/longitude.  
   - **Current Weather API**: Fetches temperature and humidity.  

4. **Testing**:  
   - Unit tests for service layer using Mockito.  
   - Integration tests for controller using MockMvc.  

---

## **Input/Output Examples**  

### **Sample Input**  
```  
GET /api/weather?pincode=411014&forDate=2023-10-15  
```  

### **Sample Output**  
```json  
{  
  "id": {  
    "pincode": "411014",  
    "forDate": "2023-10-15"  
  },  
  "temperature": 28.71,  
  "humidity": 24.0  
}  
```  

### **Explanation**  
- **First Call**:  
  - Fetches latitude/longitude for the pincode using OpenWeatherMap Geocoding API.  
  - Fetches weather data using latitude/longitude.  
  - Saves both location and weather data to H2 database.  

- **Subsequent Calls**:  
  - Returns cached data from the database to minimize API calls.  

---

## **Support**  
For issues, contact [nagesha2rl@gmail.com] or raise a GitHub issue.
