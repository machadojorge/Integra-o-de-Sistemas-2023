FROM openjdk:11-jdk

RUN apt update -y \
    && apt update -y

COPY app/target/*-with-dependencies.jar ./

EXPOSE 5000

CMD java -jar *-with-dependencies.jar