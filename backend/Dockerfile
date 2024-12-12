FROM openjdk:21-jdk

ENV HOME=/app

RUN mkdir -p $HOME

WORKDIR $HOME

ADD . $HOME

EXPOSE 8080

CMD chmod a+x /app/./mvnw

ENTRYPOINT /bin/bash /app/./mvnw spring-boot:run