# message-processing

This project is a simple message processing pipeline using Spring Boot components and Kafka as the main message broker and a PostgreSQL db for message persistence.  
The major components are:
- message-sender: exposes a REST api to send new messages to the kafka topic
- message-socket: exposes a WS api to get messages from kafka with a simple ui
- message-persistence: listens for messages and persists them into a postgres db
- message-query: exposes a REST api to query the postgres db. It also enriches the messages and use caching.

These components are standalone Spring boot applications built with gradle using Java 11.  
I did not create a parent gradle project because in a real-world system these components would be in separate repositories.  

### Running the stack

`docker-compose up --build --no-recreate`

### Running the tests

In each project:
`./gradlew test`

### Components

([See architectural diagram](diagram.png))

#### 1. Message-sender
Exposes a REST api to send new messages to the kafka topic.  
See http://localhost:8080/swagger-ui.html for api docs.

#### 2. Message-socket 
Exposes a WS api to get messages from kafka realtime.  
Exposes a simple ui as a WS client: http://localhost:8083/  
For convenience, we can send new messages from here too.

#### 3. Message-persistence 
Listens for messages and persists them into a relational database.  
Does not expose any interfaces.

#### 4. Message-query 
Exposes a REST api to query the postgres db using paging.  
The messages are also enriched with the length of longest palindrome found in the content property.  
It uses EHCache for caching palindrome calculation results.  
See http://localhost:8082/swagger-ui.html for api docs.

#### 5. Kafka
Acts as a message broker

#### 6. Message db
Stores messages. Postgres db is used for the sake of simplicity.

#### 7. Config server
Cloud config for each application

### Message model

Incoming message structure:   
` { "content": "some string", "timestamp": "2020-12-30 13:55:55+0000"}`  
- `content` can be at most 255 characters long
- both fields are mandatory

Message sent to the topic:  
` { "content": "some string", "timestamp": 1231323421321}`  
- `timestamp` is converted into a unix format Long value

Message returned by the message-query api:  
` { "content": "some string", "timestamp": "2020-12-30 13:55:55+0000", "longest_palindrome_size": 1}`  
- `timestamp` is converted back to a human readable format
- `longest_palindrome_size` is added to the response calculated based on the `content` field

### Monitoring
kafdrop: http://localhost:9001  
kafka-ui: http://localhost:9002

### Possible improvements
- Adding auth
- Message sanitization for XSS prevention
- Improve error handling
- Create integration tests
- Creating stress/performance tests
- Fine tuning components
- Moving common classes to a separate repository, using it as a dependency
- Message versioning
- Add retry policy to consumers
- Add sql schema migration tool, or switch to nosql db for storing messages
- Add centralized logging
- Using .env file for docker
