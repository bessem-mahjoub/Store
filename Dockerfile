# Étape 1 : utiliser une image JDK légère
FROM openjdk:17-jdk-slim

# Étape 2 : créer un répertoire pour l'application
WORKDIR /app

# Étape 3 : copier le fichier .jar dans le conteneur
COPY target/*.jar app.jar

# Étape 4 : exposer un port (ex: 8080)
EXPOSE 8080

# Étape 5 : exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
