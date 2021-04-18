# play-jwt-example

#### Install and run App
```
$ git clone git@github.com:techmonad/play-jwt-example.git
$ cd play-jwt-example
$ sbt run
```

#### APIs curl example:

##### Get Token
```
$ curl  -XPOST 'localhost:9000/getToken' -H 'Content-Type: application/json' -d '{"email": "bob@gmail.com"}'

Response => {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTg4MzMwNTEsImlhdCI6MTYxODc0NjY1MSwKICAiaWQiIDogMSwKICAibmFtZSIgOiAiQm9iIiwKICAiZW1haWwiIDogImJvYkBnbWFpbC5jb20iCn0.fXIaofFCqpk7Jj-SLgsuPYK__r8mZ1nXweuHmy6DtiA"}
```

##### Get  Users
```
$ curl -XGET 'localhost:9000/getUsers' -H 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTg4MzMwNTEsImlhdCI6MTYxODc0NjY1MSwKICAiaWQiIDogMSwKICAibmFtZSIgOiAiQm9iIiwKICAiZW1haWwiIDogImJvYkBnbWFpbC5jb20iCn0.fXIaofFCqpk7Jj-SLgsuPYK__r8mZ1nXweuHmy6DtiA'

Reponse => [{"id":1,"name":"Bob","email":"bob@gmail.com"}]
```