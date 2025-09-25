# Jenkins on AWS CDK (Java Edition) 🚀

Spin up a production-ready Jenkins CI/CD deployment in **minutes** with [AWS CDK for Java](https://docs.aws.amazon.com/cdk/latest/guide/work-with-cdk-java.html).  
This repo is the quickstart demo — opinionated defaults, multiple deployment flavors, and a roadmap toward enterprise features.

---

## ✨ Features

- **EC2 or Fargate** – choose your compute type at deploy time
- **Application Load Balancer (ALB)** – scalable, secure traffic routing
- **Domain + Subdomain** – bring your own domain for a polished setup
- **SSL/TLS** – encrypted by default with ACM certificates
- **Multi–Availability Zones** – fault tolerance at no extra effort

---

## 🛣 Roadmap

**Open-Source**
- NAT Gateway + Private VPC subnet support *(coming soon)*

**Enterprise**
- Private Endpoints for ECR, S3, CloudWatch
- Web Application Firewall (WAF)
- Automated Backups
- Single Sign-On (SSO) with ALB IdP protection + Jenkins integration
- Advanced Monitoring

---

### Quick Start

```bash
# Run the interactive deployer
./deploy-interactive.sh

# Or manually
mvn compile
mvn exec:java -Dexec.mainClass="com.cloudforgeci.samples.app.InteractiveDeployer"
```

### Features

- **Modular Architecture**: Uses SystemContext orchestration layer for expandable deployment types
- **Strategy Pattern**: Easily extensible deployment strategies
- **Multiple Deployment Types**:
  - Jenkins (Fargate/EC2) - ✅ Complete
  - S3 + CloudFront (Static Website) - 🚧 Coming Soon
  - S3 + CloudFront + SES + Lambda (Website + Mailer) - 🚧 Coming Soon
- **Interactive Configuration**: Prompts for all necessary parameters with sensible defaults
- **CDK Integration**: Generates proper CDK context and synthesizes stacks

### Prerequisites

1. **AWS CDK CLI**: `npm install -g aws-cdk`
2. **AWS Credentials**: `aws configure`
3. **Java 21+**: Required for compilation
4. **Maven**: For building the project


### Usage Examples

#### With Custom Stack Name
```bash
java -cp "target/classes:target/dependency/*" com.cloudforgeci.samples.app.InteractiveDeployer my-jenkins-ec2
```

#### Interactive Mode
```bash
java -cp "target/classes:target/dependency/*" com.cloudforgeci.samples.app.InteractiveDeployer
```

## 🔧 Deployment Context

Control deployments without editing Java code.

### Current usable context keys

| Key                    | Values / Example                          | Default                                   | Notes                                          |
|------------------------|-------------------------------------------|-------------------------------------------|------------------------------------------------|
| `runtime`              | `ec2` / `fargate`                         | `*-domain` variants expect Route53 + ACM. |
| `env`                  | `dev`                                     | `stage`                                   | `prod`                                         | `dev`                | Used for naming + tagging. |
| `domain`               | `example.com`                             | _none_                                    | Used with `subdomain` if `fqdn` not set.       |
| `subdomain`            | `jenkins`                                 | _none_                                    | Used to build `fqdn`.                          |
| `fqdn`                 | `jenkins.example.com`                     | _none_                                    | Wins over domain + subdomain.                  |
| `domain`               | `example.com`                             | _none_                                    | Must exist in Route53 for `*-domain` variants. |
| `topology`             | `service` / `single-node`                 | `service`                                 | Future free support for S3, SES, Lambda        |
| `enableSsl`            | `true` / `false`                          | `false`                                   | Enterprise only.                               |
| `enableFlowlogs`       | `true` / `false`                          | `false`                                   | Optional CloudFront in front of ALB.           |
| `authMode`             | `none` / `alb-oidc`/ `jenkins-oidc`       | `none`                                    | Enterprise: integrates SSO.                    |
| `ssoInstanceArn`       | `arn:aws:sso::...`                        | _none_                                    | Enterprise only.                               |
| `ssoGroupId`           | `UUID`                                    | _none_                                    | Enterprise only.                               |
| `ssoTargetAccountId`   | `123456789012`                            | _none_                                    | Enterprise only.                               |
| `artifactsBucket`      | `my-ci-artifacts`                         | _auto_                                    | Custom bucket for build artifacts.             |
| `artifactsPrefix`      | `jenkins/job/${JOB_NAME}/${BUILD_NUMBER}` | default shown                             | S3 key prefix.                                 |
| `lbType`               | `alb`                                     | `alb`                                     | Type of load balancer.                         |
| `cpu`                  | integer (Fargate vCPU, e.g. `1024`)       | `1024`                                    | Task size for Fargate.                         |
| `memory`               | integer (MiB, e.g. `2048`)                | `2048`                                    | Task size for Fargate.                         |
| `minInstanceCapacity`  | integer (Minimum Instances e.g. `2`       | `0`                                       | Minimum Instance Capacity                      |
| `maxInstanceCapacity`  | integer (Minimum Instances e.g. `10`      | `0`                                       | Maximum Instance Capacity                      |
| `cpuTargetUtilization` | integer (Minimum Instances e.g. `75`      | `60`                                      | CPU Target Utilization                         |


 ---

**Topology**

*Service (scalable / highly-available)*

Runs Jenkins as a managed service (ECS/Fargate service or an EC2 Auto Scaling Group) behind an ALB. Auto Scaling policies add/remove tasks or instances based on load (CPU/memory, request rate, queue depth). You get rolling updates, self-healing, and minimal downtime. Requires shared storage (e.g., EFS) for the Jenkins home so new tasks come up warm. Best for teams, bursty CI, and uptime expectations.

*Single Node (simple / cost-lean)*

One Jenkins controller (a single Fargate task or EC2 instance) with no horizontal scaling. Fewer moving parts, lower cost, and straightforward ops—but restarts mean brief downtime and throughput is capped at that one node. Use EBS (EC2) or EFS (Fargate) if you want persistence. Great for dev, POCs, solo use, or steady low-volume pipelines.


### `cdk.json` example

```json
{
  "app": "java -cp target/classes:target/dependency/* com.cloudforgeci.samples.app.InteractiveDeployer",
  "context": {}
}
```


## 🆓 Free vs Enterprise

CloudForgeCI comes in two editions:

- **Free Edition**
  - Fully open, with no restrictions.
  - Use in personal, enterprise, or commercial projects at no cost.
  - Includes core features: EC2/Fargate deploys, ALB, Domain/Subdomain, SSL, Multi-AZ.

- **Enterprise Edition** *(commercial)*
  - Adds advanced features for production workloads:
    - Web Application Firewall (WAF)
    - Private Endpoints (ECR, S3, CloudWatch)
    - Single Sign-On (SSO with ALB IdP + Jenkins integration)
    - Automated Backups
    - Advanced Monitoring
  - Commercial support & feature roadmap.

- **Veteran-Owned Businesses** ❤️
  - Eligible to receive **Enterprise Edition features free of charge**.
  - Our way of honoring and supporting those who’ve served.

**Bottom line:** start free, scale into enterprise features when your needs demand it — or claim full Enterprise benefits free if you’re a veteran-owned business.

