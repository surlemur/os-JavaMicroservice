#!/bin/bash

# Build app using: mvn clean package dependency:copy-dependencies

SCRIPTDIR=$(dirname "$0")
DIR=$SCRIPTDIR/../target
exec java -cp $DIR/*:$DIR/dependency/* com.zuehlke.doa.MyMain
