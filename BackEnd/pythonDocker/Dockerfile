FROM python:latest

ARG file
COPY $file.py /app.py
COPY input.txt /input.txt
COPY output.txt /output.txt

EXPOSE 8080

ENTRYPOINT ["python","/app.py"]