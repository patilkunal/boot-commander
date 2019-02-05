#!/bin/sh

DATABASE=testcasedb.hsql
DATAFILE=$DATABASE.script
if [ $# -gt 0 ]
then
    DATABASE=$1/testcasedb.hsql
    DATAFILE=$DATABASE.script
    if [ -d $1  -a ! -f $DATAFILE ] 
    then
        cp baseschema.hsql.script $DATAFILE
    else
        echo "Directory $1 does not exist"
    fi
fi

if [ -f $DATAFILE ]
then
    java -cp hsqldb.jar org.hsqldb.Server -database.0 file:$DATABASE -dbname.0 testcasedb 
else
    echo "Unable to find data file $DATAFILE"
fi


