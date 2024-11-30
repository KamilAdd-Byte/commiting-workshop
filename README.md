
# Gen Commiting Application

## Requirements
- Java 17+
- Docker
- Gradle (You can use Gradle Wrapper)

## Quick Start
1. Clone the repository:
   ```bash
   git clone <repository-URL>
   cd <repository-name>
   ```
2. Inside /gen with git bash write command 
   ```./build.sh``` (sometimes need before used ```chmod +x build.sh```)

## Other Start
1. Clone the repository:
   ```bash
   git clone <repository-URL>
   cd <repository-name>
   ```

2. Build the project using Gradle:
   ```bash
   ./gradlew build
   ```

3. If you want to build the Docker image, use the following command:
   ```bash
   docker build -t gen-commiting .
   ```

4. Run the Docker container:
   ```bash
   docker run -d -p 9000:9000 --name gen-commiting gen-commiting
   ```

5. The application will be available at `http://localhost:8090`.

## Translate Module Integration with DeepL

The `translate` module integrates with DeepL for machine translation. To use this feature, you need to provide your **DeepL API key**.

### Setup API Key

1. Go to the `translate` module’s `application.properties` (or `application.yml`) file.
   
2. Add your DeepL API key in the configuration:
   ```properties
   deepl.api.key=YOUR_DEEPL_API_KEY
   ```

3. Replace `YOUR_DEEPL_API_KEY` with your actual API key obtained from [DeepL](https://www.deepl.com/pro#developer).

## Configuration

You can specify different profiles for the application. For example, to use the `kam` profile:

1. In `application.yml`:
   ```properties
   spring.profiles.active=dev
   ```

2. Alternatively, you can pass the active profile as a Docker environment variable:
   ```bash
   docker run -d -p 9000:9000 -e SPRING_PROFILES_ACTIVE=kam --name gen-commiting gen-commiting
   ```

## Docker Configuration

### Dockerfile

The Docker image uses OpenJDK 17 and builds the application inside a Docker container. You can configure the profile or other settings when running the Docker container.

### Exposed Port

By default, the application will run on port `8090`. To change this, modify the `application.properties`:
```properties
server.port=8090
```

## Running Tests

To run the tests for the application, use Gradle:
```bash
./gradlew test
```

## License

This project is licensed under the MIT License.
