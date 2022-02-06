## AWS Security
- Security and Compliance is a shared responsibility between AWS and the customer. 
    - AWS is responsible for protecting the infrastructure which runs all of the services offered and 
        - this responsibility is known as the **Security of the Cloud**. 
        - AWS customers benefit from a data center and network architecture built to satisfy the requirements of the most security-sensitive organizations. 
    - Customers responsibility, known as the **Security in the Cloud**, is determined by which services the customer chooses to use. 
- The security pillar describes how to take advantage of cloud technologies to protect data, systems, and assets in a way that can improve your security posture. 

## Design Principles
- In the cloud, there are a number of principles that can help you strengthen your workload security:
### Implement a strong identity foundation: 
- Implement the principle of least privilege and enforce separation of duties with appropriate authorization for each interaction with your AWS resources. 
- Centralize identity management, and aim to eliminate reliance on long-term static credentials.
### Enable traceability: 
- Monitor, alert, and audit actions and changes to your environment in real time. 
- Integrate log and metric collection with systems to automatically investigate and take action.
### Apply security at all layers: 
- Apply a defense in depth approach with multiple security controls. 
- Apply to all layers (for example, edge of network, VPC, load balancing, every instance and compute service, operating system, application, and code).
### Automate security best practices: 
- Automated software-based security mechanisms improve your ability to securely scale more rapidly and cost effectively. 
- Create secure architectures, including the implementation of controls that are defined and managed as code in version-controlled templates.
### Protect data in transit and at rest: 
- Classify your data into sensitivity levels and use mechanisms, such as encryption, tokenization, and access control where appropriate.
### Keep people away from data: 
- Use mechanisms and tools to reduce or eliminate the need for direct access or manual processing of data. 
- This reduces the risk of mishandling or modification and human error when handling sensitive data.
### Prepare for security events: 
- Prepare for an incident by having incident management and investigation policy and processes that align to your organizational requirements. 
- Run incident response simulations and use tools with automation to increase your speed for detection, investigation, and recovery

## Definition
- Security in the cloud is composed of five areas:
    - Identity and access management
    - Detection
    - Infrastructure protection
    - Data protection
    - Incident response
- Security and Compliance is a shared responsibility between AWS and you, the customer. 
- This shared model can help reduce your operational burden. 
- You should carefully examine the services you choose as your responsibilities vary depending on the services used, 
    - the integration of those services into your IT environment, and applicable laws and regulations. 
- The nature of this shared responsibility also provides the flexibility and control that permits the deployment.

## Operating Your Workload Securely
- To operate your workload securely, you must apply overarching best practices to every area of security. 
- Take requirements and processes that you have defined in operational excellence at an organizational and workload level, and apply them to all areas. 
- Staying up to date with AWS and industry recommendations and threat intelligence helps you evolve your threat model and control objectives. 
- Automating security processes, testing, and validation allow you to scale your security operations.
- Identify and prioritize risks using a threat model: 
    - Use a threat model to identify and maintain an up-to-date register of potential threats. 
    - Prioritize your threats and adapt your security controls to prevent, detect, and respond. 
    - Revisit and maintain this in the context of the evolving security landscape.
- Identify and validate control objectives: 
    - Based on your compliance requirements and risks identified from your threat model, 
    - derive and validate the control objectives and controls that you need to apply to your workload. 
- Ongoing validation of control objectives and controls help you measure the effectiveness of risk mitigation.
- Keep up to date with security threats: 
    - Recognize attack vectors by staying up to date with the latest security threats to help you define and implement appropriate controls. 
- Keep up to date with security recommendations: 
    - Stay up to date with both AWS and industry security recommendations to evolve the security posture of your workload.
- Evaluate and implement new security services and features regularly: 
    - Evaluate and implement security services and features from AWS and APN Partners that allow you to evolve the security posture of your workload.
- Automate testing and validation of security controls in pipelines: 
    - Establish secure baselines and templates for security mechanisms that are tested and validated as part of your build, pipelines, and processes. 
    - Use tools and automation to test and validate all security controls continuously. 
    - For example, scan items such as machine images and infrastructure as code templates for security vulnerabilities, irregularities, and drift from an established baseline at each stage.
- Reducing the number of security misconfigurations introduced into a production environment is critical—the more quality control and reduction of defects you can perform in the build process, the better. 
    - Design continuous integration and continuous deployment (CI/CD) pipelines to test for security issues whenever possible. 
    - CI/CD pipelines offer the opportunity to enhance security at each stage of build and delivery.
    - CI/CD security tooling must also be kept updated to mitigate evolving threats.
    
## AWS Account Management and Separation
- We recommend that you organize workloads in separate accounts and group accounts based on function, compliance requirements, or a common set of controls rather than mirroring your organization’s reporting structure. 
- In AWS, accounts are a hard boundary, zero trust container for your resources. 
- For example, account-level separation is strongly recommended for isolating production workloads from development and test workloads.
- Separate workloads using accounts: Start with security and infrastructure in mind to enable your organization to set common guardrails as your workloads grow. 
- This approach provides boundaries and controls between workloads. 
- Account-level separation is strongly recommended for isolating production environments from development and test environments, or providing a strong logical boundary between workloads that process data of different sensitivity levels, as defined by external compliance requirements (such as PCI-DSS or HIPAA), and workloads that don’t.
- Secure AWS accounts: There are a number of aspects to securing your AWS accounts, including the securing of, and not using the root user, and keeping the contact information up to date. 
- You can use AWS Organizations to centrally manage and govern your accounts as you grow and scale your workloads. 
- AWS Organizations helps you manage accounts, set controls, and configure services across your accounts.
- Manage accounts centrally: AWS Organizations automates AWS account creation and management, and control of those accounts after they are created. 
- When you create an account through AWS Organizations, it is important to consider the email address you use, as this will be the root user that allows the password to be reset.
- Organizations allows you to group accounts into organizational units (OUs), which can represent different environments based on the workload’s requirements and purpose.
- Set controls centrally: Control what your AWS accounts can do by only allowing specific services, Regions, and service actions at the appropriate level. 
- AWS Organizations allows you to use service control policies (SCPs) to apply permission guardrails at the organization, organizational unit, or account level, which apply to all AWS Identity and Access Management (IAM) users and roles. 
    - For example, you can apply an SCP that restricts users from launching resources in Regions that you have not explicitly allowed. AWS Control Tower offers a simplified way to set up and govern multiple accounts. 
    - It automates the setup of accounts in your AWS Organization, automates provisioning, applies guardrails (which include prevention and detection), and provides you with a dashboard for visibility.
- Configure services and resources centrally: 
    - AWS Organizations helps you configure AWS services that apply to all of your accounts. 
    - For example, you can configure central logging of all actions performed across your organization using AWS CloudTrail, and prevent member accounts from disabling logging. 
    - You can also centrally aggregate data for rules that you’ve defined using AWS Config, enabling you to audit your workloads for compliance and react quickly to changes. 
    - AWS CloudFormation StackSets allow you to centrally manage AWS CloudFormation stacks across accounts and OUs in your organization. 
    - This allows you to automatically provision a new account to meet your security requirements.       
    
## Identity and Access Management
To use AWS services, you must grant your users and applications access to resources
in your AWS accounts. As you run more workloads on AWS, you need robust identity
management and permissions in place to ensure that the right people have access to
the right resources under the right conditions. AWS offers a large selection of
capabilities to help you manage your human and machine identities and their
permissions. The best practices for these capabilities fall into two main areas:
• Identity management
• Permissions management
Identity Management
There are two types of identities you need to manage when approaching operating
secure AWS workloads.
• Human Identities: The administrators, developers, operators, and consumers of
your applications require an identity to access your AWS environments and
applications. These can be members of your organization, or external users with
whom you collaborate, and who interact with your AWS resources via a web
browser, client application, mobile app, or interactive command-line tools.
• Machine Identities: Your workload applications, operational tools, and
components require an identity to make requests to AWS services, for example,
to read data. These identities include machines running in your AWS
environment, such as Amazon EC2 instances or AWS Lambda functions. You
can also manage machine identities for external parties who need access.
Additionally, you might also have machines outside of AWS that need access to
your AWS environment.
Rely on a centralized identity provider: For workforce identities, rely on an identity
provider that enables you to manage identities in a centralized place. This makes it
easier to manage access across multiple applications and services, because you are
creating, managing, and revoking access from a single location. For example, if
someone leaves your organization, you can revoke access for all applications and
services (including AWS) from one location. This reduces the need for multiple
credentials and provides an opportunity to integrate with existing human resources (HR)
processes.     
    