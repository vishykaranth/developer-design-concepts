## AWS Lambda With Java
### Introduction
- AWS Lambda is a serverless computing service provided by Amazon 
    - to reduce the configuration of servers, OS, Scalability, etc. 
    - AWS Lambda is capable of executing code on AWS Cloud.
- It runs in response to events on different AWS resources, 
    - which triggers AWS Lambda functions. 
    - Pricing is pay-as-you-go which means we won't shell our money on idle lambda functions.
### Maven Dependencies
- To enable AWS lambda, we need the following dependency in our project:
~~~xml
<dependency>
    <groupId>com.amazonaws</groupId>
    <artifactId>aws-lambda-java-core</artifactId>
    <version>1.1.0</version>
</dependency>

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <version>2.4.3</version>
    <configuration>
        <createDependencyReducedPom>false</createDependencyReducedPom>
    </configuration>
    <executions>
        <execution>
            <phase>package</phase>
	        <goals>
                <goal>shade</goal>
            </goals>
        </execution>
    </executions>
</plugin>
~~~    

### Create Handler
- To invoke a lambda function, we need to specify a handler; there are 3 ways of creating a handler:
    - Creating a custom MethodHandler
    - Implementing the RequestHandler interface
    - Implementing the RequestStreamHandler interface

#### Custom MethodHandler
- We'll create a handler method that will be the entry point for incoming requests. 
    - We can use JSON format or primitive data types as input values.
- Also, the optional Context object will allow us to access useful information available within the Lambda execution environment:
~~~java
public class LambdaMethodHandler {
    public String handleRequest(String input, Context context) {
        context.getLogger().log("Input: " + input);
        return "Hello World - " + input;
    }
}
~~~

#### RequestHandler Interface
- We can also implement the RequestHandler into our class and override the handleRequest method which will be our entry point for requests:
~~~java
public class LambdaRequestHandler
  implements RequestHandler<String, String> {
    public String handleRequest(String input, Context context) {
        context.getLogger().log("Input: " + input);
        return "Hello World - " + input;
    }
}
~~~

#### RequestStreamHandler Interface
- We can also implement RequestStreamHandler in our class and simply override the handleRequest method.
- The difference is that InputStream, ObjectStream and Context objects are being passed as parameters:
~~~java
public class LambdaRequestStreamHandler
  implements RequestStreamHandler {
    public void handleRequest(InputStream inputStream, 
      OutputStream outputStream, Context context) {
        String input = IOUtils.toString(inputStream, "UTF-8");
        outputStream.write(("Hello World - " + input).getBytes());
    }
}
~~~

### Build Deployment File
- With everything configured, we can create the deployment file by simply running:
    - mvn clean package shade:shade
- The jar file will be created under the target folder.

### Create Lambda Function via Management Console
- Sign in to AWS Amazon and then click on Lambda under services. 
    - This page will show the lambda functions list, which is already created.
- Here are the steps required to create our lambda:
    - “Select blueprint” and then select “Blank Function”
    - “Configure triggers” (in our case we don't have any triggers or events)
    - “Configure function”:
        - Name: Provide MethodHandlerLambda,
        - Description: Anything that describes our lambda function
        - Runtime: Select java8
        - Code Entry Type and Function Package: 
            - Select “Upload a .ZIP and Jar file” and click on “Upload” button. 
            - Select the file which contains lambda code.
        - Under Lambda function handler and role:
            - Handler name: Provide lambda function handler name com.MethodHandlerLambda::handleRequest
            - Role name: If any other AWS resources are used in lambda function, 
                - then provide access by creating/using existing role 
                - and also define the policy template.
        - Under Advanced settings:
            - Memory: Provide memory that will be used by our lambda function.
            - Timeout: Select a time for execution of lambda function for each request.
    - Once you are done with all inputs, click “Next” which will show you to review the configuration.
    - Once a review is completed, click on “Create Function”.
    
### Invoke the Function
- Once the AWS lambda function is created, we'll test it by passing in some data:
    - Click on your lambda function from lists and then click on “Test” button
    - A popup window will appear which contains dummy value for sending data. Override the data with “Baeldung”
    - Click on “Save and test” button
- On the screen, you can see the Execution result section with successfully returned output as:
    - "Hello World"    