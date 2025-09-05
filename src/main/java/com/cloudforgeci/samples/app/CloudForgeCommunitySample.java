package com.cloudforgeci.samples.app;

import com.cloudforgeci.community.ec2.JenkinsEc2DomainSslStack;
import com.cloudforgeci.community.ec2.JenkinsEc2Stack;
import com.cloudforgeci.community.fargate.JenkinsFargateEfsEcsDomainSslStack;
import com.cloudforgeci.community.fargate.JenkinsFargateEfsEcsStack;
import software.amazon.awscdk.App;
import software.amazon.awscdk.StackProps;


public class CloudForgeCommunitySample {

  public static void main(final String[] args) {
    App app = new App();

    String tier = (String) app.getNode().tryGetContext("tier");
    if (tier==null) tier="public";
    String variant = (String) app.getNode().tryGetContext("variant");
    if (variant==null) variant="ec2";
    StackProps props = StackProps.builder().build();

      switch (variant) {
        case "ec2-domain":
          new JenkinsEc2DomainSslStack(app, "JenkinsEC2s", props);
          break;
        case "fargate":
          new JenkinsFargateEfsEcsStack(app, "JenkinsECS", props);
          break;
        case "fargate-domain":
          new JenkinsFargateEfsEcsDomainSslStack(app, "JenkinsECSs", props);
          break;
        case "ec2":
        default:
          new JenkinsEc2Stack(app, "JenkinsEc2", props);
          break;
      }

    app.synth();
  }
}
