FROM openjdk:17-slim

# 필수 도구 설치
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    time \
    procps \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY run.sh /app/run.sh
RUN chmod +x /app/run.sh

CMD ["./run.sh"]
