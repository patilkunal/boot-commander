#!/bin/sh

DATABASE=testcasedb.hsql
DATAFILE=$DATABASE.script

if [ $# -gt 0 ]
then
    if [ ! -d $1 ]
    then
        echo "Directory $1 does not exist"
        exit 1
    fi
    DATABASE=$1/testcasedb.hsql
    DATAFILE=$DATABASE.script
    if [ ! -f $DATAFILE ] 
    then
        echo "No Database setup. Copying base schema to $DATAFILE"
        cp baseschema.hsql.script $DATAFILE
    else
        echo "Database is already setup at $DATAFILE. Skipping copying base schema"
    fi
fi

if [ -f $DATAFILE ]
then
    java -cp hsqldb.jar org.hsqldb.Server -database.0 file:$DATABASE -dbname.0 testcasedb 
else
    echo "Unable to find data file $DATAFILE"
fi


