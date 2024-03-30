# Utiliser une image de base Maven avec Java 8
FROM maven:3.8.4-openjdk-8-slim AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier pom.xml et le répertoire src dans le conteneur
COPY pom.xml .
COPY src ./src

# Télécharger toutes les dépendances et construire le projet
RUN mvn clean package -DskipTests

# Utiliser une image de base Java 8 pour l'exécution
FROM openjdk:8-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier JAR construit dans le conteneur
# Utilisez un wildcard pour copier le JAR généré par Maven
COPY --from=build /app/target/*.jar /app/app.jar

# Exposer le port sur lequel l'application sera accessible
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java","-jar","/app/app.jar"]
