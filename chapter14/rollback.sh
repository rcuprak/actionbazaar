#!/bin/bash

liquibase --driver org.postgresql.Driver \
 --classpath=target/chapter14.war \
 --changeLogFile=com/actionbazaar/db/db.changelog-master.xml \
 --url="jdbc:postgresql://localhost/actionbazaar3" \
 --username=actionbazaar \
 --password=password \
 rollback tag
 

