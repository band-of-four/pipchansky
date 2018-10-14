#!/bin/bash

scp build/libs/lab2-0.1.war helios:~/apps-to-deploy/

ssh -t helios '~/glassfish4/bin/asadmin deploy --force true --user admin --port 27573 --name lab2-0.1 ~/apps-to-deploy/lab2-0.1.war'

