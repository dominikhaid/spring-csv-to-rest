# Springboot Rest Example

## Description

A Simple REST example build using Gradle and Java Springboot.
There is one Endpoint to upload Data given in CSV file format to the Mysql instance.
The Msql instance is running in docker. Once the Data is inserted to the Database,
you can use one of the Endpoints described below to query it.

## Usage

1. cd docker && docker-compose up -d // start the mysql instance
2. gradle bootRun //start the springboot server from the root directory
3. insert data to the database from with the query bellow

```
curl --location --request POST 'http://localhost:8080/api/v1/betriebsstelle/csv/upload' \
--form 'file=@/ROOT_DIR/src/main/resources/static/DBNetz-Betriebsstellenverzeichnis-Stand2021-07.csv'
```

## Endpoints

- GET /api/v1/betriebsstelle/{id}
- GET /api/v1/betriebsstelle/search/{rlcode}
- POST /api/v1/betriebsstelle
- DELETE /api/v1/betriebsstelle/{id}
- PUT /api/v1/betriebsstelle/{id}

## Dependencies

- Gradle
- Java
- Springboot
- Lombok
- Mysql
- Docker
