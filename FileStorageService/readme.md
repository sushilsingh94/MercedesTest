#FileStorageService
###FileStorageService is backend service and a Kafka consumer service which will read message from kafka and process 
## Getting Started

### Local Setup and Installation
* First setup kafka and zookeeper
  * Install docker and docker-compose in the host machine follow for reference [Docker & Docker-Compose Installation](https://docs.docker.com/engine/install/)
  * check whether docker and docker-compose is installed correctly.
* Run docker-compose.yml from project file.
  * > docker-compose up -d
* run below commands on terminal to check if docker container is running with kafka and zookeeper.
* This command will return all running containers and id
* >docker ps
* Copy container id eg. e999dcc71641  from previous command result
* Next Create kafka topic to send message named "mercedes_user"
* >docker exec -it {containerId} kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic mercedes_user
* List topics from kafka will return "mercedes_user"
* >docker exec -it {containerId} kafka-topics.sh --list --zookeeper zookeeper:2181
* Show/List messages in topic
* >kafka-console-consumer.sh --topic mercedes_user --from-beginning --bootstrap-server localhost:9092

* If kafka is installed on seperate host then update application.properties file with the host address and port for key "spring.kafka.consumer.bootstrap-servers"
* Create docker image for file-storage-service
* >docker build -t sushil/file-storage-server .
* run  docker images to see latest image file created
* run below command to start application in docker
* >docker run -p 8080:8080 sushil/file-storage-server

* Or If dont want to run in docker then directly run in Machine using command below
* >nohup java -jar /build/libs/FileStorageService-0.0.1-SNAPSHOT.jar > /User/Documents/logs/beapplog.log 2>&1 &
* Access application using http://localhost:8080/file-service/v1/users