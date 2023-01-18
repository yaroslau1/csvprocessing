# Coding task
## Purpose
Assess candidate coding skills and style
## Short description
Design and implement REST interface to consume data-snapshots from one client, validate and persist data in storage,
distribute persisted data to other clients via REST interface.

### Technological stack and limitations
- Language: Java
- Build tools: Maven/Gradle
- Frameworks: no restrictions
### Requirements
- REQ-01: As a client I want to upload CSV file via HTTP request
  1. CSV file contains header with following attributes:
     * PRIMARY_KEY
     * NAME
     * DESCRIPTION
     * UPDATED_TIMESTAMP
  2. The last line of file is always empty
  3. "PRIMARY_KEY" attribute must be non-blank string
  4. "UPDATED_TIMESTAMP" attribute if present must be a ISO8601 timestamp string
- REQ-02: As client I want get persisted data by PRIMARY_KEY attribute via HTTP request. Request parameter(s) should
be passed in the request URL.
- REQ-03: As service owner I want to remove record from storage by PRIMARY_KEY attribute via HTTP request
- REQ-04: As service owner I want no invalid records from uploaded CSV file to be saved
### Non-functional requirements
- Authentication: not required
- Logging: standard for web services
- Error reporting and monitoring: standard for web services
- Reliability: standard for web services
- Deployment: standard java-based application deployment options
- Performance:
  * file upload time less than 60 minutes
  * get record time less than 1sec
  * remove record time no restrictions