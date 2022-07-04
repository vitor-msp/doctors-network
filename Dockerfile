FROM openjdk:17-alpine as build
WORKDIR /workspace/app
COPY doctorsnetwork/mvnw .
COPY doctorsnetwork/.mvn .mvn
COPY doctorsnetwork/pom.xml .
COPY doctorsnetwork/src src
RUN chmod +x mvnw
RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-alpine
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","br/com/sdconecta/doctorsnetwork/DoctorsNetworkApplication"]
