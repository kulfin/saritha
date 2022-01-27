1. Please run the following command to compile the code, run unit tests and create executable jar file. Command , should be run in the root directory of the project:

mvn package

alternatively you can use eclipse plugin to maven compile as well.

2. Generated jar will be in /target folder. Name will be geektrust.jar

3. invoke the jar using the following format :

java -jar geektrust.jar <input file full path>


for example: 

java -jar geektrust.jar /Users/apple/eclipse-workspace/Family.Problem/src/main/resources/input.txt

