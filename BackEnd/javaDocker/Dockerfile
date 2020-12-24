FROM java:8

ARG file
COPY $file.jar /app.jar
COPY input.txt /input.txt
COPY output.txt /output.txt

CMD ["--server.port=8080"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar","< /input.txt","> /output.txt"]