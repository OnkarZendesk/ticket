# Ticket-Viewer
This repository is basically a local server that is used to fetch and push changes using Zendesk API. Then this information is sent to the front end application "sample-app" where it is displayed.
This project can be run individually and the result can be viewed on POSTMAN.
To run this project- Start the project by using the main method situated in file TicketApplication.java.


To do GET call simply run below command in POSTMAN
### http://localhost:3001/api/getData

To do POST call simply run below command in POSTMAN
### http://localhost:3001/api/postData
The mandatory fields to give via post call are subject and description in the below format. The credentials have not been uploaded. So please contact me for any credential related queries.

{
"ticket": {
"subject": "Zendesk API test",
"description": "marcie!!!"
}
}
