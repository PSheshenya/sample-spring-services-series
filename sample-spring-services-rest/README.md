# sample-spring-services-rest

## Overview
Simple project shows how to build a REST services to consume and produce JSON content.

Use the Spring @Controller and @RestController Annotations (@RestController a convenience annotation that combines @Controller and @ResponseBody )
The JSON REST services use default Jackson.

### Examples

* POST Create entity
```
POST http://localhost:8080/foos/
Cache-Control: no-cache
Content-Type: application/json;charset=UTF-8

{"id":5,"name":"Petr","email":"Sh"}
```
Response
```
HTTP/1.1 201 
Location: http://localhost:8080/foos/4
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 16 Apr 2019 10:11:10 GMT

{
  "id": 4,
  "name": "Petr",
  "email": "Sh"
}

Response code: 201
```
The header also automatically contains the Location header, which is the address we can use to access the newly created entity.

* GET
```
GET http://localhost:8080/foos/

HTTP/1.1 200 
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 16 Apr 2019 11:29:32 GMT

[
  {
    "id": 1,
    "name": "Alan",
    "email": "Turing"
  },
  {
    "id": 2,
    "name": "Sebastian",
    "email": "Bach"
  },
  {
    "id": 3,
    "name": "Pablo",
    "email": "Picasso"
  },
  {
    "id": 4,
    "name": "Petr",
    "email": "Sh"
  }
]

Response code: 200
```

