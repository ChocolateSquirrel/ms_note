FROM openjdk:8
EXPOSE 8082
COPY target/ms_note-0.0.1-SNAPSHOT.jar note.jar
ENTRYPOINT ["java", "-jar", "/note.jar"]