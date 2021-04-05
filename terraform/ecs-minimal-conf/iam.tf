# Define aws iam role for ecs instance
resource "aws_iam_role" "ecs-instance-role" {
  name        = format("%s%s", var.artifact_name, var.instance_role_name)
  path        = var.instance_role_path
  description = "Allows EC2 instances to call AWS services on your behalf."
  assume_role_policy    = data.aws_iam_policy_document.ecs-instance-policy.json
  force_detach_policies = true
}

# Declare iam policy document for ecs instance
data "aws_iam_policy_document" "ecs-instance-policy" {
  statement {
    actions = [
      "sts:AssumeRole"]
    principals {
      type = "Service"
      identifiers = [
        "ec2.amazonaws.com"]
    }
  }
}

# Define iam role policy attachment for ecs instance
resource "aws_iam_role_policy_attachment" "ecs-instance-role-attachment" {
  role       = aws_iam_role.ecs-instance-role.name
  policy_arn = var.instance_role_attachment_policy_arn
}

# Define iam instance profile for ecs instance
resource "aws_iam_instance_profile" "ecs-instance-profile" {
  role = aws_iam_role.ecs-instance-role.id
  name = format("%s%s", var.artifact_name, var.instance_profile_name)
  path = var.instance_role_path
  provisioner "local-exec" {
    command = "sleep 10"
  }
}
