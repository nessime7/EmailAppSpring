# 1. Pobrac obraz linux (jak najmniejszy)
# 2. Pobrac niezbedne narzedzia (java, gradle, +++)
# 3. Pobrac repozytorium EmailSpringApp z GitHub
# 4. Zbudowac plik .jar ze zrodel z 3.
# 5. Odpalic aplikacje na wybranym porcie
# 6. Zweryfikowac dzialanie uzywajac Postman

ARG BUILD_HOME=/EmailAppSpring

FROM gradle:jdk11 as build-image

# Set working Directory
ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME

# config, source code
COPY --chown=gradle:gradle build.gradle settings.gradle $APP_HOME/
COPY --chown=gradle:gradle src $APP_HOME/src/main

# build jar
RUN gradle clean
RUN gradle --no-daemon build -x test fatJar

FROM openjdk:11

# copy the jar file in and name it app.jar.
ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
COPY --from=build-image $APP_HOME/build/libs/*.jar emailapp.jar

# the command to run when the container starts.
EXPOSE 8000
ENTRYPOINT java -jar emailapp.jar