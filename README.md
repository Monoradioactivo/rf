# Robinfood Practice Test

## Requirements

- [Maven](https://maven.apache.org/download.cgi)
- [JDK 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)

## Running the application
- You can run the application executing the `main` method in the `mx.simio.robinfood.RobinfoodApplication` class from your IDE
- Running the application from command line: `mvn spring-boot:run`
- Application will start on port `8085`
- To verify that the application started correctly you can ping this endpoint: `/GET http://localhost:8085/health`
  If everything is ok you will see a response like this:
```json
{
    "status": "UP"
}
```

## Endpoints
- `/GET http://localhost:8085/health` This endpoint will give you the health of the application
- `/POST http://localhost:8085/api/v1/surveys` This endpoint will create a new survey.
  This is an example on how to create a new survey:
```json
{
    "name": "The Survey - Test Two",
    "description": "This is a survey two",
    "questions": [
        {
            "question": "This is a single question",
            "answers": [
                {
                    "value": "Only one answer"
                }
            ]
        }, 
        {
            "question": "This is a single question",
            "answers": [
                {
                    "value": "1 answer"
                },
                {
                    "value": "Multiple answers"
                }
            ]
        }
    ]
}
```
- `/GET http://localhost:8085/api/v1/surveys/{surveyId}` This endpoint will return a survey given an ID.
  E.g.
```json
{
    "id": 1,
    "name": "The Survey - Test Two",
    "description": "This is a survey two",
    "questions": [
        {
            "question": "This is a single question",
            "answers": [
                {
                    "value": "Only one answer"
                }
            ]
        },
        {
            "question": "This is a single question",
            "answers": [
                {
                    "value": "1 answer"
                },
                {
                    "value": "Multiple answers"
                }
            ]
        }
    ]
}
```



## Resources
- You can find the Entity - Relationship diagram under `src/main/resources` folder.

## Developer
[Adrian Moreno](mailto:i13120427@gmail.com)