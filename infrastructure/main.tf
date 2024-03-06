# Create a VPC
resource "aws_vpc" "beans_wechat_vpc" {
  cidr_block = "10.0.0.0/16"
}

# Create Subnet 1
resource "aws_subnet" "subnet1" {
  vpc_id     = aws_vpc.beans_wechat_vpc.id
  cidr_block = "10.0.0.0/17"
}

# Create Subnet 2
resource "aws_subnet" "subnet2" {
  vpc_id     = aws_vpc.beans_wechat_vpc.id
  cidr_block = "10.0.128.0/17"
}

# Create a security group
resource "aws_security_group" "database_sg" {
  name        = "database-sg"
  description = "SQL server security group"

  ingress {
    from_port   = 1433
    to_port     = 1433
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 65535
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  vpc_id = aws_vpc.beans_wechat_vpc.id
}


resource "aws_db_instance" "default" {
  allocated_storage = 20
  engine = "sqlserver-ex"
  instance_class = "db.t3.micro"
  username = jsondecode(data.aws_secretsmanager_secret_version.creds.secret_string)["username"]
  password = jsondecode(data.aws_secretsmanager_secret_version.creds.secret_string)["password"]
  skip_final_snapshot = true // required to destroy
  publicly_accessible= true
  identifier = "beans-wechat"
}

resource "aws_elastic_beanstalk_application" "beanstalk_application" {
  name        = "beanstalk-application"
  description = "beanstalk-application for beans wechat"
}

resource "aws_elastic_beanstalk_environment" "elasticbeanstalk" {
  name                = "beans-elastic-beanstalk"
  application         = aws_elastic_beanstalk_application.tftest.name
  solution_stack_name = "64bit Amazon Linux 2015.03 v2.0.3 running Go 1.4"

  setting {
    namespace = "aws:ec2:vpc"
    name      = "VPCId"
    value     = aws_vpc.beans_wechat_vpc.id
  }

  setting {
    namespace = "aws:ec2:vpc"
    name      = "Subnets"
    value     = aws_subnet.subnet1
  }
}