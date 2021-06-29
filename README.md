# CI/CD using Jenkins and Ktor Project
Learn CI/CD using jenkins with ktor project using jenkins with docker.

## Jenkins
First we need to build our jenkins container. 
I have make my own jenkins image that using **host mechine** docker as the agent. 
After that we will configure our jenkinsfile to do the job for pulling the code from the repository and build it.
After the image has been built we will continue with pushing the image to the **Docker Hub**.

## Dockerfile
We defined the dockerfile here to do multistage build so we can have smaller and efective image that it need only the important things to run the application.

