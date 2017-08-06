#!/bin/bash

mvn clean install
rc=$?
if [ $rc -ne 0 ] ; then
  echo 'Maven Execution failure'; exit ${rc}
fi

mvn exec:java
