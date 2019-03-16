# SpringHibernate

## Building the container
` $ docker build -f DockerFile -t $DOCKER_USER_ID/bp-springhibernate . `

## Running the container
` $ docker run -d -p 9090:8080 $DOCKER_USER_ID/bp-springhibernate `

## Push the container
` $ docker push $DOCKER_USER_ID/bp-springhibernate `
