# Fetch subnets using vpc id
data "aws_subnet_ids" "subnets" {
  vpc_id = var.vpc_id
}

# ECS Instance Security group
resource "aws_security_group" "ecs_sg" {
  name        = format("%s%s", var.artifact_name, var.ecs_sg)
  description = "Test public access security group"
  vpc_id      = var.vpc_id

  ingress {
    from_port   = "0"
    to_port     = "0"
    protocol    = "-1"
    cidr_blocks = [
      "0.0.0.0/0"]
  }

  /*ingress {
    from_port   = "0"
    to_port     = "32767"
    protocol    = "tcp"
    cidr_blocks = [
      "0.0.0.0/0"]
  }*/

  egress {
    # allow all traffic to private SN
    from_port   = "0"
    to_port     = "0"
    protocol    = "-1"
    cidr_blocks = [
      "0.0.0.0/0"]
  }

  tags = {
    Name = var.ecs_sg
  }
}
