# EDA Microservices solution template

##  How to build

```
cd microservices-template
mvn clean install -DskipTests
```

## How to Run
```
cd docker-compose
docker-compose -f  common.yml -f kafka_cluster.yml -f services.yml up
```