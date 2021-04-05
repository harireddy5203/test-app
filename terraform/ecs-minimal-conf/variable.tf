#### ECS variables ####
variable "artifact_name" {
  type        = string
  description = "Service artifact name"
  default     = "DigitalExpPlatform"
}

variable "ecs_cluster" {
  type        = string
  description = "ECS cluster name"
  default     = "-Cluster"
}

variable "ecs_task_definition_name" {
  type        = string
  description = "ECS task definition name"
  default     = "-TD"
}

variable "ecs_service_name" {
  type        = string
  description = "ECS service name"
  default     = "-Service"
}

variable "vpc_name" {
  description = "VPC name for environment"
  default = "-VPC"
}

variable "vpc_id" {
  description = "VPC ID"
  default = null
}

variable "ecs_sg" {
  type        = string
  description = "ECS security group name"
  default     = "-SG"
}

variable "protocol" {
  type        = string
  description = "Protocol type"
  default     =  "tcp"
}

variable "ecs_key_pair_name" {
  description = "EC2 instance key pair name"
  default     = null
}

variable "region" {
  description = "AWS region"
  default     = "us-east-2"
}

variable "availability_zone" {
  description = "availability zone used for the demo, based on region"
  default = {
    us-east-2 = "us-east-2"
  }
}

variable "max_instance_size" {
  type        = number
  description = "Maximum number of instances in the cluster"
  default     = 1
}

variable "min_instance_size" {
  type        = number
  description = "Minimum number of instances in the cluster"
  default     = 1
}

variable "desired_capacity" {
  type        = number
  description = "Desired number of instances in the cluster"
  default     = 1
}

#### IAM variables ####
variable "instance_role_name" {
  type        =  string
  description = "ECS instance role name"
  default     = "-ECSInstanceRole"
}

variable "instance_role_path" {
  type        = string
  description = "ECS instance role path"
  default     = "/"
}

variable "instance_profile_name" {
  type        = string
  description = "ECS instance profile name"
  default     = "-ECSInstanceProfile"
}

variable "instance_role_attachment_policy_arn" {
  type        = string
  description = "ECS instance role policy arn"
  default     = "arn:aws:iam::aws:policy/service-role/AmazonEC2ContainerServiceforEC2Role"
}

variable "lc_name" {
  type        = string
  description = "ECS launch configuration"
  default     = "-LC"
}

variable "lc_image_id" {
  type        = string
  description = "ECS launch configuration image id"
  default     = "ami-0159bf92e16d6e3ae"
}

variable "lc_instance_type" {
  type        = string
  description = "ECS launch configuration instance type"
  default     = "t2.micro"
}

variable "associate_public_ip_address" {
  type        = bool
  description = "Associate public ip addrss"
  default     = true
}

variable "autoscaling_group_name" {
  type        = string
  description = "Auto scaling group name"
  default     = "-ASG"
}

variable "autoscaling_health_check_type" {
  type        = string
  description = "Auto scaling health check type"
 // default     = "ELB"
  default = "EC2"
}

variable "auto_scaling_policy_name" {
  type        = string
  description = "Auto scaling policy name"
  default     = "-ASP"
}

variable "auto_scaling_policy_type" {
  type        = string
  description = "Auto scaling policy type"
  default = "TargetTrackingScaling"
}

variable "estimated_instance_warmup" {
  type        = string
  description = "Auto scaling estimated instance warmup"
  default     = "90"
}

variable "adjustment_type" {
  type        = string
  description = "Auto scaling adjustment type"
  default     = "ChangeInCapacity"
}

variable "auto_scaling_target_value" {
  type        = string
  description = "Auto scaling target value"
  default     = "40.0"
}

variable "predefined_metric_type" {
  type        = string
  description = "predefined metric type"
  default     = "ASGAverageCPUUtilization"
}

variable "container_json_file_path" {
  type        = string
  description = "container definition json file location from terraform root directory"
  # Here the file location is given as per the execution directory of terraform.
  # If you are executing terraform from templates directory it should be as "./container/aws/ecs/container.json"
  default     = "./ecs-minimal-conf/container.json"
}

variable "container_name" {
  type        = string
  description = "Name of the container"
  default     = "container"
}

variable "ecr_repo_path" {
  type        = string
  description = "ECR Repository URL"
  default     = "ecr-repo"
}


