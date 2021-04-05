# Define aws ecs launch configuration
resource "aws_launch_configuration" "ecs-launch-configuration" {

  depends_on = [
    aws_ecs_cluster.ecs-cluster]
  name_prefix           =  format("%s%s", var.artifact_name, var.lc_name)
  image_id              = var.lc_image_id
  instance_type         = var.lc_instance_type
  iam_instance_profile  = aws_iam_instance_profile.ecs-instance-profile.id

  root_block_device {
    volume_type           = "standard"
    volume_size           = 100
    delete_on_termination = true
  }

  lifecycle {
    create_before_destroy = true
  }
  # Default security groups based on VPC Id.
  #security_groups = [data.aws_security_group.nodes.id]
  security_groups = [aws_security_group.ecs_sg.id]
  associate_public_ip_address = var.associate_public_ip_address
  key_name                    = var.ecs_key_pair_name

  user_data = <<EOF
               #!/bin/bash
               echo ECS_CLUSTER=${aws_ecs_cluster.ecs-cluster.name} >> /etc/ecs/ecs.config;
               echo ECS_CONTAINER_INSTANCE_TAGS={"Name": "ECS-Instance”} >> /etc/ecs/ecs.config;
               EOF
}

# Define ecs cluster
resource "aws_ecs_cluster" "ecs-cluster" {
  name = format("%s%s", var.artifact_name, var.ecs_cluster)
  tags = {
    Name = format("%s%s", var.artifact_name, var.ecs_cluster)
  }
}

# To get aws account Id
data "aws_caller_identity" "current" {}

data "template_file" "container_file" {
  template = file(var.container_json_file_path)
  vars = {
    REPOSITORY_URL = format("%s.%s", data.aws_caller_identity.current.account_id, var.ecr_repo_path)
  }
}

# Define ecs task definition
resource "aws_ecs_task_definition" "task_definition" {
  family = format("%s%s", var.artifact_name, var.ecs_task_definition_name)
  container_definitions = data.template_file.container_file.rendered
  #network_mode             = "awsvpc"
  requires_compatibilities = ["EC2"]
}

# Define ecs service
resource "aws_ecs_service" "test-ecs-service" {
  name = format("%s%s", var.artifact_name, var.ecs_service_name)
  #iam_role = aws_iam_role.ecs-service-role.name

  cluster = aws_ecs_cluster.ecs-cluster.id
  task_definition = format("%s:%s", aws_ecs_task_definition.task_definition.family, aws_ecs_task_definition.task_definition.revision )
  desired_count = 1
  launch_type            = "EC2"

  /*network_configuration {
    security_groups       = [aws_security_group.ecs_sg.id]
    subnets               = tolist(data.aws_subnet_ids.subnets.ids)
    assign_public_ip      = "false"
  }*/
}

# Define aws auto scaling group
resource "aws_autoscaling_group" "ecs-autoscaling-group" {
  name                = format("%s%s", var.artifact_name, var.autoscaling_group_name)

  max_size            = var.max_instance_size
  min_size            = var.min_instance_size
  desired_capacity    = var.desired_capacity
  vpc_zone_identifier = tolist(data.aws_subnet_ids.subnets.ids)
  launch_configuration = aws_launch_configuration.ecs-launch-configuration.name

  health_check_type         = var.autoscaling_health_check_type
  protect_from_scale_in     = true
  health_check_grace_period = 120
  default_cooldown          = 30
  force_delete              = true

}
