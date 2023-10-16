# TO-DO List

A simple TO-DO list project using Java, Spring Boot, Spring Data JPA, H2, Lombok, BCrypt and Docker.
The project is live on [Render](https://render.com/) at https://java-todolist-4zew.onrender.com

## Usage

**Creating an user:**
```
curl --request POST \
--url https://java-todolist-4zew.onrender.com/users/ \
--header 'Content-Type: application/json' \
--header 'User-Agent: insomnia/8.2.0' \
--data '{
"name": "John Wick",
"username": "wickjohn",
"password": "password$123#"
}'
```

**Creating a task:**

To create a task, is necessary to do a basic auth before accordingly with the user created before, I've used [Insomnia](https://insomnia.rest/) for testing. 
```
curl --request POST \
  --url https://java-todolist-4zew.onrender.com/tasks/ \
  --header 'Authorization: Basic bGV0aWNpYTpNdHMkMTIzIw==' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/8.2.0' \
  --data '{
	"description":"Some description goes here.",
	"title":"Some title goes here.",
	"priority":"High",
	"startAt":"2023-12-11T13:30:00",
	"endAt":"2024-10-11T13:30:00",
	"idUser":"01ba70a2-e625-4941-886a-d6b80a676708"
}'
```

## Next steps

Do a minimal Frontend to make it more user friendly.