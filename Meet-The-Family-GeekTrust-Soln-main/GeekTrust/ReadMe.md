Steps to Run Application
1. Navigate to GeekTrust/target
2. Open CMD/GIT Bash/PowerShell in this folder
3. Type command java -jar geektrust.jar "Path of the Query Text file"  <- I have placed a sample query.text file in the zip file.
4. Hit Enter and your results will be displayed on the screen.

To compile Source code
1. Navigate to GeekTrust folder 
2. Type mvn clean compile assembly:single
3. Hit Enter
4. Your new jar will be created in target folder

To Run all the test cases
1. Navigate to GeekTrust folder 
2. Type mvn clean install
3. Hit Enter