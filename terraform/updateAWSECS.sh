#!/usr/bin/env bash
echo "*** Updating AWS ECS service with recent image ***"
terraform init
# Taint is used to mark the resource, These tainted resources will re-create in the next apply.
terraform taint module.ecs-minimal-conf.aws_ecs_task_definition.task_definition
terraform taint module.ecs-minimal-conf.aws_autoscaling_group.ecs-autoscaling-group
terraform apply -auto-approve
echo "*** AWS ECS update completed ***"



