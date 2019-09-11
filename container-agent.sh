docker --version
docker run -it -v /var/run/docker.sock:/var/run/docker.sock bibinwilson/jenkins-slave sh -c "apt-get update ; apt-get install docker.io -y ; bash"
