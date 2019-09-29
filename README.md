# Goal: to remove a set of elements in a json array by it's id, email.
A record is a deuplicate if it has same id or emailId

Steps to run the project:
1. Usind command prompt cd to the folder DeDuplicateJson
Steps 2 to 4 are optional
2. Change input file location (leads.json) from DeDuplicateJson\input to the path you wish and update the input file location in App.java (line 29).
3. Change output file location (result.json) from DeDuplicateJson\output to the path you wish and update the output file location in App.java (line 93).
4. Change log file location (logging.json) from DeDuplicateJson\output to the path you wish and update the log file location in log4j.properties (line 5).
5. use maven to compile (mnv complie) and execute (mvn exec:java -Dexec.mainClass=DeDuplicateJson.DeDuplicateJson.App) and verify the results of result.json, logging.log in the location you have configured.
