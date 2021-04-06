output "publicIp" {
  value = aws_launch_configuration.ecs-launch-configuration.associate_public_ip_address
}

output "configuration" {
  value = aws_launch_configuration.ecs-launch-configuration
}

output "service" {
  value = aws_ecs_service.test-ecs-service
}
