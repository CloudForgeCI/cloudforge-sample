# CloudForge Sample Project

Deploy production-ready applications on AWS in minutes using [AWS CDK for Java](https://docs.aws.amazon.com/cdk/latest/guide/work-with-cdk-java.html).

This repository demonstrates the CloudForge platform with opinionated defaults, multiple deployment options, and enterprise-grade compliance features.

---

## Features

- **15+ Supported Applications** - Jenkins, GitLab, Grafana, PostgreSQL, Redis, Vault, SonarQube, and more
- **EC2 or Fargate** - Choose your compute type at deploy time
- **Plugin Architecture** - Extensible application and compliance plugins via Java SPI
- **Multi-Framework Compliance** - SOC2, PCI-DSS, HIPAA, GDPR out of the box
- **OIDC Authentication** - Cognito, AWS Identity Center, or external providers
- **Application Load Balancer** - Scalable, secure traffic routing with SSL/TLS
- **Multi-Availability Zone** - Fault tolerance at no extra effort

---

## Quick Start

### Option 1: Interactive Deployer (Recommended)

The interactive deployer guides you through configuration choices and generates a deployment context file.

```bash
# Synthesize with interactive prompts (creates deployment-context.json)
cdk synth

# Review the generated CloudFormation template, then deploy
cdk deploy

# Or preview changes without executing
cdk deploy --no-execute
```

### Option 2: Deployment Context File

Use a pre-configured template for faster deployments.

```bash
# Copy a deployment context template
cp docs/deployment-contexts/examples/jenkins-dev.json deployment-context.json

# Edit with your settings
vim deployment-context.json

# Deploy
cdk deploy -c cfc=@deployment-context.json
```

### Prerequisites

1. **AWS CDK CLI**: `npm install -g aws-cdk`
2. **AWS Credentials**: `aws configure`
3. **Java 21+**: Required for compilation
4. **Maven**: For building the project

---

## Supported Applications

| Category | Applications |
|----------|-------------|
| **CI/CD** | Jenkins, GitLab, Drone |
| **Version Control** | Gitea |
| **Monitoring** | Grafana, Prometheus |
| **Databases** | PostgreSQL, Redis |
| **Secrets Management** | HashiCorp Vault |
| **Artifact Registry** | Nexus, Harbor |
| **Collaboration** | Mattermost |
| **Analytics** | Metabase, Apache Superset |
| **Code Quality** | SonarQube |

---

## Documentation

📚 **[Complete Documentation](https://cloudforgeci.github.io/cfc-core/documentation/)**

For comprehensive guides, API references, and detailed configuration options, visit the hosted documentation:
- Application catalog and deployment guides
- Deployment context configuration reference
- Compliance framework implementation
- Plugin development guides
- Authentication and security setup

---

## Ready-to-Use Templates

### By Application

| Application | Development | Production |
|-------------|-------------|------------|
| Jenkins | [jenkins-dev.json](docs/deployment-contexts/examples/jenkins-dev.json) | [jenkins-production.json](docs/deployment-contexts/examples/jenkins-production.json) |
| Mattermost | [mattermost-dev.json](docs/deployment-contexts/examples/mattermost-dev.json) | [mattermost-production.json](docs/deployment-contexts/examples/mattermost-production.json) |
| Metabase | [metabase-dev.json](docs/deployment-contexts/examples/metabase-dev.json) | [metabase-production.json](docs/deployment-contexts/examples/metabase-production.json) |
| GitLab | - | [gitlab-production.json](docs/deployment-contexts/examples/gitlab-production.json) |
| Grafana | - | [grafana-production.json](docs/deployment-contexts/examples/grafana-production.json) |
| Harbor | - | [harbor-production.json](docs/deployment-contexts/examples/harbor-production.json) |
| SonarQube | - | [sonarqube-production.json](docs/deployment-contexts/examples/sonarqube-production.json) |

### By Compliance Framework

| Framework | Quick Start | Staging | Production |
|-----------|-------------|---------|------------|
| SOC2 | [compliance-soc2-quick.json](docs/deployment-contexts/examples/compliance-soc2-quick.json) | [compliance-soc2-staging.json](docs/deployment-contexts/examples/compliance-soc2-staging.json) | [compliance-soc2-production.json](docs/deployment-contexts/examples/compliance-soc2-production.json) |
| HIPAA | [compliance-hipaa-quick.json](docs/deployment-contexts/examples/compliance-hipaa-quick.json) | - | [compliance-hipaa-production.json](docs/deployment-contexts/examples/compliance-hipaa-production.json) |
| PCI-DSS | - | - | [compliance-pci-dss-production.json](docs/deployment-contexts/examples/compliance-pci-dss-production.json) |

### By Environment & Cost

| Environment | Template | Cost Estimate |
|-------------|----------|---------------|
| Dev Minimal | [dev-minimal.json](docs/deployment-contexts/dev-minimal.json) | ~$35/month |
| Dev Standard | [dev-standard.json](docs/deployment-contexts/dev-standard.json) | ~$95/month |
| Staging SOC2 | [staging-soc2.json](docs/deployment-contexts/staging-soc2.json) | ~$220/month |
| Production SOC2 | [production-soc2.json](docs/deployment-contexts/production-soc2.json) | ~$400/month |
| Production HIPAA | [production-hipaa.json](docs/deployment-contexts/production-hipaa.json) | ~$550/month |
| Production PCI-DSS | [production-pci-dss.json](docs/deployment-contexts/production-pci-dss.json) | ~$710/month |

---

## Documentation

📚 **[Complete Documentation](https://cloudforgeci.github.io/cfc-core/documentation/)**

Visit the hosted documentation for comprehensive guides and API references.

### Quick Links

- **[Getting Started](https://cloudforgeci.github.io/cfc-core/documentation/guides/interactive-deployer)** - Interactive deployment guide
- **[Application Catalog](https://cloudforgeci.github.io/cfc-core/documentation/applications/)** - All supported applications
- **[Deployment Context Reference](https://cloudforgeci.github.io/cfc-core/documentation/deployment-contexts/)** - Configuration options
- **[Compliance Frameworks](https://cloudforgeci.github.io/cfc-core/documentation/compliance/)** - SOC2, HIPAA, PCI-DSS
- **[Plugin Development](https://cloudforgeci.github.io/cfc-core/documentation/plugins/)** - Build custom plugins
- **[Authentication Setup](https://cloudforgeci.github.io/cfc-core/documentation/applications/oidc)** - SSO and OIDC configuration

> **Note:** The `/docs` folder in this repository serves as the source for the hosted documentation

---

## Project Structure

```
cloudforge-sample/
├── src/main/java/com/cloudforgeci/samples/
│   ├── app/
│   │   ├── CloudForgeCommunitySample.java    # Main CDK app entry point
│   │   └── InteractiveDeployer.java          # Interactive CLI deployer
│   ├── launchers/
│   │   ├── ApplicationEc2Stack.java          # Universal EC2 deployment stack
│   │   └── ApplicationFargateStack.java      # Universal Fargate deployment stack
│   └── plugins/
│       ├── application/
│       │   └── SonarQubeApplicationSpec.java # Example application plugin
│       └── compliance/
│           └── CustomSecurityPolicyRules.java # Example compliance plugin
├── docs/
│   ├── applications/      # Application catalog and specs
│   ├── compliance/        # Compliance framework documentation
│   ├── databases/         # Database deployment guides
│   ├── deployment-contexts/ # Ready-to-use JSON templates
│   ├── guides/            # Implementation guides
│   ├── plugins/           # Plugin development documentation
│   └── setup/             # Initial setup guides
└── src/main/resources/META-INF/services/
    ├── com.cloudforge.core.interfaces.ApplicationSpec
    └── com.cloudforge.core.interfaces.FrameworkRules
```

---

## Plugin System

CloudForge uses Java's ServiceLoader for plugin discovery, enabling extensibility without modifying core code.

- **Application Plugins** - Define custom applications by implementing `ApplicationSpec`
- **Compliance Plugins** - Add custom compliance rules via `FrameworkRules`

Example plugins are included in [src/main/java/com/cloudforgeci/samples/plugins/](src/main/java/com/cloudforgeci/samples/plugins/).

📖 **[Plugin Development Guide](https://cloudforgeci.github.io/cfc-core/documentation/plugins/)**

---

## Authentication Options

CloudForge supports multiple authentication modes:

- **`none`** - Application-native authentication (development)
- **`alb-oidc`** - ALB-level OIDC authentication
- **`application-oidc`** - Application-level OIDC with group/role mapping

Supports Amazon Cognito, AWS IAM Identity Center, and external OIDC providers (Okta, Auth0, Azure AD).

📖 **[Authentication Setup Guide](https://cloudforgeci.github.io/cfc-core/documentation/applications/oidc)**

---

## Compliance Frameworks

CloudForge provides automated compliance enforcement for:

- **SOC2** - Access control, encryption, audit logging
- **PCI-DSS** - Cardholder data protection, network security
- **HIPAA** - PHI encryption, audit trails, access controls
- **GDPR** - Data protection, consent management

Automated controls include S3 lifecycle management, IAM policy enforcement, CloudTrail audit logging, AWS Config monitoring, and encryption at rest.

📖 **[Compliance Framework Guide](https://cloudforgeci.github.io/cfc-core/documentation/compliance/)**

---

## Free vs Enterprise

CloudForge comes in two editions:

### Free Edition
- Fully open, with no restrictions
- Use in personal, enterprise, or commercial projects at no cost
- Includes core features: EC2/Fargate deploys, ALB, Domain/Subdomain, SSL, Multi-AZ

### Enterprise Edition
Adds advanced features for production workloads:
- Web Application Firewall (WAF)
- Private Endpoints (ECR, S3, CloudWatch)
- Single Sign-On (SSO with ALB IdP + application integration)
- Automated Backups
- Advanced Monitoring
- Commercial support & feature roadmap

### Veteran-Owned Businesses
Eligible to receive **Enterprise Edition features free of charge**. Our way of honoring and supporting those who've served.

---

## Support

- **Documentation**: [https://cloudforgeci.github.io/cfc-core/documentation/](https://cloudforgeci.github.io/cfc-core/documentation/)
- **Issues**: [GitHub Issues](https://github.com/CloudForgeCI/cloudforge-sample/issues)

---

## License

Apache 2.0 - See [LICENSE](LICENSE) for details.
