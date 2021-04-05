#!/usr/bin/env bash
echo "*** Destroy infrastructure using terraform started ***"
terraform destroy -auto-approve
echo "*** Destroy infrastructure using terraform completed ***"
