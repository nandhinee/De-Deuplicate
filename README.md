# Goal: to remove a set of elements in a json array by its id, email.
A record is a deuplicate if it has same id or emailId

Steps to run the project:
1. Using command prompt cd to the folder DeDuplicateJson
Steps 2 to 4 are optional
2. Change input file location (leads.json) from DeDuplicateJson\input to the path you wish and update the input file location in App.java (line 10).
3. Change output file location (result.json) from DeDuplicateJson\output to the path you wish and update the output file location in App.java (line 11).
4. Change log file location (logging.json) from DeDuplicateJson\output to the path you wish and update the log file location in src\main\resources\log4j.properties (line 5).
5. Use maven to compile (mnv complie) and execute (mvn exec:java -Dexec.mainClass=DeDuplicateJson.DeDuplicateJson.App) and verify the results of result.json, logging.log in the location you have configured. Resolve maven dependencies if required (mvn dependency:resolve)
Step 6 is optional
6. Update the path of test files in DuplicatesRemoverTest (lines: 14,22,30) before running the tests
7. use mvn test to execute tests

Assumptions:
Emails and dates in the input json are always in the right format
firstName, lastName, address can have null values.

Maven libraries used:
jackson - to parse json
log4j - for logging purpose

Notes:
A lead with null id or emailId or entry date is not processed.

