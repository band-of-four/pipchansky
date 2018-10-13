#!/bin/bash

scp build/libs/lab2-0.1.war asgard:~/apps-to-deploy/

ssh -t asgard '~/glassfish4/bin/asadmin --user admin redeploy --name lab2-0.1 ~/apps-to-deploy/lab2-0.1.war'

