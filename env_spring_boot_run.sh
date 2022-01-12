#!/bin/bash
if [ -e .env ]
then
    echo "Found .env file with "$(wc -l .env)" lines"
else
    echo "You must define a .env file !!"
    exit 1
fi

env $(cat .env) mvn spring-boot:run

