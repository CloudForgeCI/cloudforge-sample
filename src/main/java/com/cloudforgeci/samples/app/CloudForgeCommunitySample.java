package com.cloudforgeci.samples.app;

import com.cloudforgeci.api.core.DeploymentContext;
import com.cloudforgeci.api.interfaces.RuntimeType;
import com.cloudforgeci.community.compute.jenkins.Jenkins;
import com.cloudforgeci.community.ec2.JenkinsEc2DomainSslStack;


import com.cloudforgeci.community.ec2.JenkinsEc2Stack;
import com.cloudforgeci.community.fargate.JenkinsFargateEfsEcsDomainSslStack;
import com.cloudforgeci.community.fargate.JenkinsFargateEfsEcsStack;
import com.cloudforgeci.community.fargate.JenkinsServiceNode;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;


public class CloudForgeCommunitySample {

  public static void main(final String[] args) {
    App app = new App();
    DeploymentContext cfc = DeploymentContext.from(app);
    StackProps props = StackProps.builder().env(Environment.builder()
            .account(System.getenv("CDK_DEFAULT_ACCOUNT"))
            .region(System.getenv("CDK_DEFAULT_REGION")).build()).build();

    switch (cfc.runtime().name()) {
      case "FARGATE" -> new JenkinsServiceNode(app, "JenkinsSvc", props);
      case "ec2-domain" -> new JenkinsEc2DomainSslStack(app, "JenkinsEC2s", props);
      case "fargate-legacy" -> new JenkinsFargateEfsEcsStack(app, "JenkinsECS", props);
      case "fargate-domain" -> new JenkinsFargateEfsEcsDomainSslStack(app, "JenkinsECSs", props);
      default -> new JenkinsEc2Stack(app, "JenkinsEc2Legacy", props);
    }

    app.synth();
  }
}
