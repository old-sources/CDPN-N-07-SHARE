FROM postgres:9.4
ENV POSTGRES_PASSWORD=docker
ENV POSTGRES_USER=docker
ENV POSTGRES_DB=crowdfunding
COPY docker/schema.sql /docker-entrypoint-initdb.d/dump.sql