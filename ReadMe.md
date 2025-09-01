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

**Free Tier**
- NAT Gateway + Private VPC subnet support *(coming soon)*

**Enterprise Tier**
- Private Endpoints for ECR, S3, CloudWatch
- Web Application Firewall (WAF)
- Automated Backups
- Single Sign-On (SSO) with ALB IdP protection + Jenkins integration
- Advanced Monitoring

---

## ⚡ Quickstart

1. **Clone this repo**
   ```bash
   git clone https://github.com/CloudForgeCI/cloudforge-sample.git
   cd cloudforge-sample
   ```

2. **Bootstrap CDK (if not already done)**
   ```bash
   cdk bootstrap aws://<account>/<region>
   ```

3. **Configure your deployment**  
   Pass context values at deploy time or via `cdk.json`.

   Example context keys:
   - `domainName` = `jenkins.example.com`
   - `domainZone` = `example.com`
   - `variant` = `ec2` | `ec2-domain` | `fargate` | `fargate-domain`

4. **Deploy**
   ```bash
   mvn clean package
   cdk deploy -c variant=fargate-domain -c domain=example.com -c subdomain=jenkins
   ```

5. **Access Jenkins**
   Once deployed, CDK outputs the Jenkins URL (with SSL).

---

## 🔧 Deployment Context

Control deployments without editing Java code.

### Current usable context keys

| Key                  | Values / Example                                   | Default            | Notes |
|----------------------|----------------------------------------------------|--------------------|-------|
| `variant`            | `ec2`/ `ec2-domain` / `fargate` / `fargate-domain` | `ec2`                | `*-domain` variants expect Route53 + ACM. |
| `env`                | `dev`                                              | `stage` | `prod`                                    | `dev`                | Used for naming + tagging. |
| `domain`             | `example.com`                                      | _none_             | Used with `subdomain` if `fqdn` not set. |
| `subdomain`          | `jenkins`                                          | _none_             | Used to build `fqdn`. |
| `fqdn` / `domainName`| `jenkins.example.com`                              | _none_             | Wins over domain + subdomain. |
| `domainZone`         | `example.com`                                      | _none_             | Must exist in Route53 for `*-domain` variants. |
| `networkMode`        | `public-no-nat`                                    | `private-with-nat`                        | `public-no-nat`      | Future free support for NAT Gateway + private subnets. |
| `wafEnabled`         | `true` / `false`                                   | `false`            | Enterprise only. |
| `cloudfront`         | `true` / `false`                                   | `false`            | Optional CloudFront in front of ALB. |
| `authMode`           | `none` / `alb-oidc`/ `jenkins-oidc`                | `none`               | Enterprise: integrates SSO. |
| `ssoInstanceArn`     | `arn:aws:sso::...`                                 | _none_             | Enterprise only. |
| `ssoGroupId`         | `UUID`                                             | _none_             | Enterprise only. |
| `ssoTargetAccountId` | `123456789012`                                     | _none_             | Enterprise only. |
| `artifactsBucket`    | `my-ci-artifacts`                                  | _auto_             | Custom bucket for build artifacts. |
| `artifactsPrefix`    | `jenkins/job/${JOB_NAME}/${BUILD_NUMBER}`          | default shown      | S3 key prefix. |
| `lbType`             | `alb`                                              | `nlb`                                               | `alb`                | Type of load balancer. |
| `cpu`                | integer (Fargate vCPU, e.g. `1024`)                | `1024`             | Task size for Fargate. |
| `memory`             | integer (MiB, e.g. `2048`)                         | `2048`             | Task size for Fargate. |

---

### CLI examples

**Fargate + custom ci domain (`jenkins.example.com`)**
```bash
cdk deploy   -c variant=fargate-domain   -c domain=example.com   -c subdomain=jenkins
```

**EC2 + explicit FQDN**
```bash
cdk deploy   -c variant=ec2-domain   -c domainName=jenkins.example.com   -c domainZone=example.com
```

**Plain EC2 (no domain records created)**
```bash
cdk deploy -c variant=ec2
```

### `cdk.json` example

```json
{
  "app": "java -cp target/classes:target/dependency/* com.cloudforgeci.samples.app.CloudForgeCommunitySample",
  "context": {
    "variant": "fargate-domain",
    "domain": "example.com",
    "subdomain": "jenkins",
    "domainZone": "example.com"
  }
}
```

---

## 🧩 Planned: `DeploymentContext` Java API

A future release will allow defining deployments with a typed Java class instead of CLI flags.

Example shape:

```java
var ctx = new DeploymentContext.Builder()
    .tier(DeploymentContext.Tier.PUBLIC)
    .variant(DeploymentContext.Variant.FARGATE_DOMAIN)
    .env(DeploymentContext.Env.DEV)
    .domain("example.com")
    .subdomain("jenkins")
    // or .fqdn("jenkins.example.com")
    .networkMode(DeploymentContext.NetworkMode.PUBLIC_NO_NAT)
    .authMode(DeploymentContext.AuthMode.NONE)
    .cpu(1024)
    .memory(2048)
    .build();

new JenkinsStack(app, "JenkinsStack", StackProps.builder().build(), ctx);
```

Until then, use the `-c` context keys shown above.

---

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
