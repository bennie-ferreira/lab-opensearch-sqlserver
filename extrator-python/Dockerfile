FROM python:3.12-alpine

RUN apk update && apk add gcc \
    build-base \
    curl \
    musl-dev \
    unixodbc-dev

WORKDIR /app

COPY requirements.txt .

RUN pip install --upgrade pip && \
    pip install --no-cache-dir -r requirements.txt

CMD ["tail", "-f", "/dev/null"]