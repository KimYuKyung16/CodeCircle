FROM gcc:13-bookworm

RUN apt-get update && apt-get install -y \
    time \
    procps \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY run.sh /app/run.sh
RUN chmod +x /app/run.sh

CMD ["./run.sh"]
