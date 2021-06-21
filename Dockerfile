FROM openjdk:11

ENV GRADLE_OPTS="-Dkotlin.compiler.execution.strategy=in-process -Dkotlin.incremental=false"
WORKDIR /app
COPY . /app/
RUN ./gradlew dependencies
