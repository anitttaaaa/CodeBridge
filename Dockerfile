# Wybór obrazu bazowego
FROM eclipse-temurin:17-jdk-alpine

# Skopiowanie plików statycznych do kontenera, zachowując strukturę folderów
COPY ./src/main/resources/static /static

# Skopiowanie pliku JAR aplikacji
COPY build/libs/myapp.jar /myapp.jar

# Uruchomienie aplikacji
CMD ["java", "-jar", "/myapp.jar"]
