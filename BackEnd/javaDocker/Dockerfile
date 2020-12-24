FROM java:8

ARG file
COPY $file.jar /app.jar
COPY input.txt /input.txt
COPY output.txt /output.txt

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]