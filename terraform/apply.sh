#!/usr/bin/env bash
echo "*** Infrastructure creation using terraform started ***"
terraform init
terraform apply -auto-approve
echo "*** Infrastructure creation using terraform completed ***"
