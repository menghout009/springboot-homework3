# 🔥 Base image with known CVEs
FROM openjdk:8-jdk

# 🔥 No USER instruction — runs as root
# 🔥 No HEALTHCHECK defined
# 🔥 No vulnerability mitigation or cleanup

# Copy JAR file (you can change the name if needed)
COPY target/demo.jar /app/demo.jar

# Run the app (as root)
CMD ["java", "-jar", "/app/demo.jar"]
