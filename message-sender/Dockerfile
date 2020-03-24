FROM openjdk:11-jdk-slim as build

ENV APP_HOME=/app/
WORKDIR $APP_HOME

COPY build.gradle settings.gradle gradlew $APP_HOME
RUN chmod +x gradlew
COPY gradle $APP_HOME/gradle
COPY src $APP_HOME/src
RUN ./gradlew --no-daemon bootJar

FROM openjdk:11-jre-slim
ENV ARTIFACT_NAME=*.jar
ENV APP_HOME=/app
WORKDIR $APP_HOME
COPY --from=build $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
ENTRYPOINT java -jar $ARTIFACT_NAME
