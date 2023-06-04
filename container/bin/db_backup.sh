#!/bin/bash

if [ "$#" -ne 6 ]; then
        echo "Usage: db_backup <hostname> <port> <user> <password> <database> <output file>"
        exit 1
fi

PGPASSWORD="$4" pg_dump -h "$1" -p "$2" -U "$3" "$5" >> "$6"
