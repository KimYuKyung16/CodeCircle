FROM node:20-slim

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    time \
    procps \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY run.sh /app/run.sh
RUN chmod +x /app/run.sh

CMD ["./run.sh"]
