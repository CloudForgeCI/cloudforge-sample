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

```bash
# Run the interactive deployer
./deploy-interactive.sh

# Or manually
mvn compile
mvn exec:java -Dexec.mainClass="com.cloudforgeci.samples.app.InteractiveDeployer"
```

### Option 2: Deployment Context File

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

See [Application Catalog](docs/applications/README.md) for detailed documentation on each application.

---

## Deployment Context

Control deployments via JSON configuration without editing Java code.

### Key Configuration Options

| Key | Values / Example | Default | Notes |
|-----|------------------|---------|-------|
| `applicationId` | `jenkins`, `gitlab`, `grafana`, etc. | _required_ | Application to deploy |
| `runtime` | `ec2` / `fargate` | `fargate` | Compute type |
| `securityProfile` | `dev` / `staging` / `production` | `dev` | Security posture |
| `env` | `dev` / `stage` / `prod` | `dev` | Environment name |
| `domain` | `example.com` | _none_ | Route53 domain |
| `subdomain` | `jenkins` | _none_ | Service subdomain |
| `topology` | `service` / `single-node` | `service` | Scaling mode |
| `authMode` | `none` / `alb-oidc` / `application-oidc` | `none` | Authentication |
| `complianceFrameworks` | `SOC2,HIPAA,PCI-DSS` | _none_ | Compliance frameworks |

See [Deployment Context Reference](docs/deployment-contexts/README.md) for the complete configuration guide.

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

### Getting Started
| Guide | Description |
|-------|-------------|
| [Interactive Deployer Guide](docs/guides/INTERACTIVE_DEPLOYER.md) | Step-by-step CLI deployment |
| [Deployment Context Reference](docs/deployment-contexts/README.md) | Configuration options and templates |
| [Deployment Context Examples](docs/deployment-contexts/examples/README.md) | Ready-to-use JSON configurations |

### Applications
| Guide | Description |
|-------|-------------|
| [Application Catalog](docs/applications/README.md) | All supported applications with compliance requirements |
| [Application Guides](docs/guides/applications/README.md) | Per-application deployment guides |
| [Application Compliance](docs/applications/COMPLIANCE.md) | Compliance requirements by application |
| [OIDC Authentication](docs/applications/OIDC.md) | SSO/OIDC setup with Cognito, Identity Center |

### Compliance
| Guide | Description |
|-------|-------------|
| [Compliance Overview](docs/compliance/README.md) | Automated compliance enforcement |
| [Quick Start Guide](docs/compliance/QUICK_START_GUIDE.md) | Fast path to compliance |
| [Deployment Guide](docs/compliance/DEPLOYMENT_GUIDE.md) | Detailed deployment instructions |
| [Multi-Framework Compliance](docs/compliance/MULTI_FRAMEWORK_COMPLIANCE.md) | HIPAA + SOC2 + PCI-DSS together |
| [PCI-DSS Compliance](docs/compliance/PCI_DSS_COMPLIANCE.md) | Payment card industry requirements |
| [PCI-DSS Application Security](docs/compliance/PCI_DSS_APPLICATION_SECURITY.md) | Application-level PCI-DSS controls |
| [Controls Implementation](docs/compliance/CONTROLS_IMPLEMENTATION.md) | Detailed control mapping |

### Plugin System
| Guide | Description |
|-------|-------------|
| [Plugin System Overview](docs/plugins/README.md) | Plugin architecture introduction |
| [Plugin Ecosystem](docs/plugins/PLUGIN-ECOSYSTEM.md) | Built-in and community plugins |
| [Application Plugin Guide](docs/plugins/APPLICATION-PLUGIN-GUIDE.md) | Build custom application plugins |
| [Compliance Plugin Guide](docs/plugins/COMPLIANCE-PLUGIN-GUIDE.md) | Build custom compliance validators |

### Setup & Configuration
| Guide | Description |
|-------|-------------|
| [AWS Identity Center Setup](docs/setup/AWS_IDENTITY_CENTER_SETUP.md) | Enterprise SSO configuration |
| [Cognito MFA Setup](docs/setup/COGNITO_MFA_COMPLIANCE_SETUP.md) | MFA for compliance requirements |
| [IAM Rules](docs/guides/IAM_RULES.md) | IAM policy configuration |
| [Security Rules](docs/guides/SECURITY_RULES_README.md) | Security group configuration |
| [Database Deployment](docs/databases/DATABASE-DEPLOYMENT-GUIDE.md) | PostgreSQL, Redis deployment |

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

### Application Plugins

Define new applications by implementing the `ApplicationSpec` interface:

```java
public class MyAppSpec implements ApplicationSpec {
    @Override
    public String applicationId() { return "myapp"; }

    @Override
    public String defaultContainerImage() { return "myapp/myapp:latest"; }

    @Override
    public int applicationPort() { return 8080; }
    // ... other methods
}
```

Register in `META-INF/services/com.cloudforge.core.interfaces.ApplicationSpec`:
```
com.example.plugins.MyAppSpec
```

See [Application Plugin Guide](docs/plugins/APPLICATION-PLUGIN-GUIDE.md) for details.

### Compliance Plugins

Define custom compliance rules by implementing the `FrameworkRules` interface:

```java
public class MyComplianceRules implements FrameworkRules {
    @Override
    public String frameworkId() { return "MY-FRAMEWORK"; }

    @Override
    public List<ConfigRule> getConfigRules() { /* ... */ }
}
```

Register in `META-INF/services/com.cloudforge.core.interfaces.FrameworkRules`:
```
com.example.plugins.MyComplianceRules
```

See [Compliance Plugin Guide](docs/plugins/COMPLIANCE-PLUGIN-GUIDE.md) for details.

---

## Authentication Options

CloudForge supports multiple authentication modes:

| Mode | Description | Use Case |
|------|-------------|----------|
| `none` | Application-native authentication | Development, simple setups |
| `alb-oidc` | ALB-level OIDC authentication | All requests authenticated at load balancer |
| `application-oidc` | Application-level OIDC | Full group/role mapping, public pages support |

### Supported Providers

- **Amazon Cognito** - Managed user directory with MFA support
- **AWS IAM Identity Center** - Enterprise SSO integration
- **External OIDC** - Okta, Auth0, Azure AD, or any OIDC-compliant provider

See [OIDC Authentication Guide](docs/applications/OIDC.md) for configuration details.

---

## Compliance Frameworks

CloudForge provides automated compliance enforcement:

| Framework | Description | Key Controls |
|-----------|-------------|--------------|
| **SOC2** | Service Organization Control 2 | Access control, encryption, audit logging |
| **PCI-DSS** | Payment Card Industry | Cardholder data protection, network security |
| **HIPAA** | Healthcare data protection | PHI encryption, audit trails, access controls |
| **GDPR** | EU data privacy | Data protection, consent management |

### Automated Controls

- Intelligent S3 lifecycle management (Standard -> Glacier -> Deep Archive)
- IAM password policy enforcement with auto-remediation
- CloudTrail audit logging with immutable storage
- AWS Config continuous compliance monitoring
- Encryption at rest for all storage (EFS, EBS, S3)

See [Compliance Overview](docs/compliance/README.md) for details.

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

- **Documentation**: See the [docs/](docs/) directory
- **Issues**: [GitHub Issues](https://github.com/CloudForgeCI/cloudforge-sample/issues)

---

## License

Apache 2.0 - See [LICENSE](LICENSE) for details.
