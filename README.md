# clinicalrelevance
POC Clinical Relevance API using Spring Boot

* Runs against local Redis using the `default` profile
* Runs against a hard-coded external Redis instance using the `sandbox` profile
* Half-baked attempt at Hexagonal architecture (maybe more of a triangle?) 
* Builds a docker container with jib - `deployment.yaml` exists but hasn't been tested.