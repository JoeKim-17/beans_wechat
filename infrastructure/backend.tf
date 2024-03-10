terraform {
  backend "s3" {
    bucket = "beanswechatbucket"
    key = "beans-wechat/terraform.tfstate"  # Specify the path/key for your state file
    region = "eu-west-1"
  }
}