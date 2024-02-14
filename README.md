 Spring Social media blog API

## Background 

Full-stack applications are typically concerned with both a front end, that displays information to the user and takes in input, and a backend, that manages persisted information.

This project will be a backend for a hypothetical social media app, where we must manage our users’ accounts as well as any messages that they submit to the application. However, the functionality for this project will leverage a popular web application framework for Java known as Spring. The Spring framework allows for automatic injection and configuration of many features, including data persitence, endpoints and conventional data manipulation logic (CRUD operations).

In our hypothetical micro-blogging or messaging app, any user should be able to see all of the messages posted to the site, or they can see the messages posted by a particular user. In either case, we require a backend which is able to deliver the data needed to display this information as well as process actions like logins, registrations, message creations, message updates, and message deletions.

## Database Tables 

The following tables will be initialized in your project's built-in database upon startup using the configuration details in the application.properties file and the provided SQL script.

### Account
```
account_id integer primary key auto_increment,
username varchar(255) not null unique,
password varchar(255)
```

### Message
```
message_id integer primary key auto_increment,
posted_by integer,
message_text varchar(255),
time_posted_epoch long,
foreign key (posted_by) references Account(account_id)
```

# Spring Technical Requirement

## Project must leverage the Spring Boot Framework

# User Stories

## 1: Our API should be able to process new User registrations.

## 2: Our API should be able to process User logins.

## 3: Our API should be able to process the creation of new messages.

## 4: Our API should be able to retrieve all messages.

## 5: Our API should be able to retrieve a message by its ID.

## 6: Our API should be able to delete a message identified by a message ID.

## 7: Our API should be able to update a message text identified by a message ID.

## 8: Our API should be able to retrieve all messages written by a particular user.

## 9: The Project utilizes the Spring Framework.

- The project was created leveraging the spring framework, including dependency injection, autowire functionality and/or Spring annotations.


Author: Joseph Kim @justjohykim
