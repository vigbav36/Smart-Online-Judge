# Use the official Python image from Docker Hub
FROM python:3.9-slim

ENV PYTHONDONTWRITEBYTECODE=1
ENV PYTHONUNBUFFERED=1

RUN apt-get update && apt-get install -y --no-install-recommends \
    gcc \
    libc-dev \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /usr/src/app

COPY requirements.txt .
COPY test_runner.py .
RUN pip install --no-cache-dir -r requirements.txt

COPY setup.sh .
RUN chmod +x setup.sh

ENTRYPOINT ["./setup.sh"]