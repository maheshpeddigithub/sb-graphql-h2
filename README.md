# Spring Boot + GraphQL + H2 example

## Run Spring Boot application
```
mvn spring-boot:run
```


Docker: <br>
------- <br>
docker build -t sb-graphql-h2 . <br>
docker tag sb-graphql-h2 cloudmahesh/sb-graphql-h2:tag1 <br>
docker push cloudmahesh/sb-graphql-h2:tag1 <br>
docker rmi $(docker images -q) <br>

Minikube: <br>
--------- <br>
minikube start <br>
minikube tunnel <br>
minikube stop <br>

minikube image load cloudmahesh/sb-graphql-h2:tag1 <br>
minikube image ls <br>
minikube image rm cloudmahesh/sb-graphql-h2:tag1 <br>

K8S: <br>
---- <br>
kubectl get nodes <br>
kubectl create deployment sb-graphql --image=cloudmahesh/sb-graphql-h2:tag1 <br>
kubectl get deployments <br>
kubectl expose deployment sb-graphql --type LoadBalancer --port 8080 --target-port 8080 <br>
kubectl get pods <br>
kubectl get service sb-graphql-h2 <br>
kubectl get services <br>

K8S-Manifest: <br>
------------- <br>
kubectl apply -f k8s-sb-graphql-h2.yml <br>
kubectl delete -f k8s-sb-graphql-h2.yml <br>

GraphQL PostMan collection: <br>
----------------------------- <br>
curl --location --request POST '10.109.68.162:8080/apis/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"{\n  findAllAuthors{\n    id\n    name\n    age\n  }\n}","variables":{}}'

curl --location --request POST '10.109.68.162:8080/apis/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"mutation {\n  createAuthor(\n    name: \"John\"\n    age: 40) {\n      id\n      name\n      age\n  }\n}","variables":{}}'

curl --location --request POST '10.109.68.162:8080/apis/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"{\n  countAuthors\n}","variables":{}}'

curl --location --request POST '10.109.68.162:8080/apis/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"{\n  findAllTutorials{\n\tid\n\ttitle\n\tdescription\n\tauthor {\n        id\n        name\n        age\n    }\n  }\n}","variables":{}}'

curl --location --request POST '10.109.68.162:8080/apis/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"mutation {\n  createTutorial(\n\ttitle: \"t1\"\n\tdescription: \"d1\"\n\tauthor: 1) {\n      id\n      title\n\t  description\n\t  author {\n          id\n        name\n        age\n      }\n  }\n}","variables":{}}'

curl --location --request POST '10.109.68.162:8080/apis/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"{\n  countTutorials\n}","variables":{}}'

curl --location --request POST '10.109.68.162:8080/apis/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"mutation {\n  updateTutorial(\n      id: 2\n\ttitle: \"t11\"\n\tdescription: \"d11\") {\n      id\n      title\n\t  description\n\t  author {\n          id\n        name\n        age\n      }\n  }\n}","variables":{}}'

curl --location --request POST '10.109.68.162:8080/apis/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"mutation {\n  deleteTutorial(id: 2) \n}","variables":{}}'