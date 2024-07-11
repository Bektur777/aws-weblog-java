# Welcome to your CDK Java project!

This is a blank project for CDK development with Java.

The `cdk.json` file tells the CDK Toolkit how to execute your app.

It is a [Maven](https://maven.apache.org/) based project, so you can open this project with any Maven compatible Java IDE to build and run tests.

## Useful commands

 * `mvn package`     compile and run tests
 * `cdk ls`          list all stacks in the app
 * `cdk synth`       emits the synthesized CloudFormation template
 * `cdk deploy`      deploy this stack to your default AWS account/region
 * `cdk diff`        compare deployed stack with current state
 * `cdk docs`        open CDK documentation

Enjoy!

## Swagger
https://app.swaggerhub.com/apis/BekturUlanbekov/aws-weblog-java/1.0.0

## Used technologies

### AWS
* Lambda
* API Gateway
* Cloud Formation
* Cloud Watch (Working with logs)
* S3 (Used to store project dependencies )
* DynamoDB
* SNS (Send email messages for subscribers using filters e.g by title start with "News")

All services were written configured and deployed in java. Github actions were used as ci/cd