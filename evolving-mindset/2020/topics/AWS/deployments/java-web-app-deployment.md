## Java Web App Deployment 
- Amazon Web Services(AWS) is a cloud service from Amazon, 
    - which provides services in the form of building blocks, 
    - these building blocks can be used to create and 
    - deploy any type of application in the cloud.
    - Building Blocks   
        - Compute
        - Storage
        - Database
        - Migration
        - Network and Content Delivery
        - Management Tools
        - Security & Identity Compliance
        - Messaging
### How to deploy Java Web Application in AWS?
- General Best Practices
    - The size and installation complexity of web applications can vary greatly, 
        - therefore there is rarely a one-size-fits-all solution for deploying and hosting Java applications. 
        - However, there are some universal best practices to consider when deploying any web application:
            - Understand the deployment, installation, and configuration characteristics of the application.
            - Understand application expectations from initial deployment to future scalability, availability, and backup and recovery requirements.
            - Use automation whenever possible for deployment and other tasks where consistency is important.
            - Leverage source code or application repositories to protect your application.
### AWS Elastic Beanstalk
#### Deploy this application on AWS. 
- Go to the AWS homepage. 
- Click on Services -> Compute -> Elastic Beanstalk. 
- Now click on create a new application. 
- Enter the name of the application and create a new environment for it. 
- Now select Web Server Environment. 
- Now in Base Configuration, Select Tomcat in the Preconfigured Platform. 
- In the Application Code select the WAR file that we created in the previous step. 
- Now click on Upload.  
- ![](images/AWS%20Deployment.png)    
- Uploading the WAR file will take a few minutes. 
    - Once it is done you will see the following page. 
    - Here you can see the URL. 
    - Click on the URL and you will see a JSP with your text message in it.
- ![](images/AWS%20Deployment%2002.png)          