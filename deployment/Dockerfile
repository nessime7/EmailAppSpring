# 1. Pobrac obraz linux (jak najmniejszy)
# 2. Pobrac niezbedne narzedzia (java, gradle, +++)
# 3. Pobrac repozytorum EmailSpringApp z GitHub
# 4. Zbudowac plik .jar ze zrodel z 3.
# 5. Odpalic aplikacje na wybranym porcie
# 6. Zweryfikowac dzialanie uzywajac Postman

#Linux image
FROM alpine:latest
WORKDIR /root/email-app
COPY EmailAppSpring-0.0.1-SNAPSHOT-all.jar /root/email-app

#JDK
RUN  apk add openjdk11
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk
ENV PATH $PATH:$JAVA_HOME/bin

#RUN
EXPOSE 8000
CMD java -jar EmailAppSpring-0.0.1-SNAPSHOT-all.jar