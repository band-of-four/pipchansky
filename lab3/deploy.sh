#!/bin/bash

scp build/libs/lab3-1.0.war asgard:~/apps-to-deploy/

ssh -t asgard '~/glassfish4/bin/asadmin --user admin redeploy --name lab3-1.0 ~/apps-to-deploy/lab3-1.0.war'
