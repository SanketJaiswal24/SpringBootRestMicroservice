pipeline {
  
  agent {
        docker 
        { 
          image 'jekins-docker-slave' 
          args  '--privileged -v /var/run/docker.sock:/var/run/docker.sock'  
        }
    }  
    
    stages {

        stage('Compile stage')
        {
          steps
          {
            sh 'docker images'
            sh 'mvn clean compile'
          }
        }
        
        stage('Package Stage') {
              steps {
                 sh 'mvn package'
             }    
        }

        stage('Build Docker Image'){
             steps
             {
             sh 'sudo usermod -a -G docker $USER'
             sh 'docker images'
             sh 'java -version'
             sh 'docker --version'
             sh 'ls'
             sh 'pwd'
             sh 'docker images'  
             sh 'mkdir DockerCon'
             sh 'mv /var/lib/jenkins/workspace/Devops-Demo/dockerfile DockerImages'
             sh 'cd DockerCon'
             sh 'docker build -t sanketjaiswal12345/spring-boot-apache-derby-docker2.0.0 .'
             }
        }

        /*Push Docker images in Docker-Hub */
        stage('Push Docker Image'){
           steps
           {
            withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
            sh "docker login -u sanketjaiswal12345 -p ${dockerHubPwd}"
           }
           sh 'docker push sanketjaiswal12345/spring-boot-apache-derby-docker2.0.0'
           }
         }

       /* Run Image in Dev Server*/
       stage('Run Container on Dev Server')
       {  
          options 
          { 
            timeout(time: 15, unit: 'SECONDS') 
          }
        
        steps
           {
          sh 'docker run -p 8085:8085 sanketjaiswal12345/spring-boot-apache-derby-docker2.0.0'
       }
      }           

    }
    
    /*Post Decleration*/
    post {

        always {

           echo 'Post action running'

        }
        
        success {
            
            echo 'I am Success Done'
            
            /*Publish Jacoca Report in Jenkins Dashboard */
            junit 'target/**/*.xml'
            step([
              $class           : 'JacocoPublisher',
              execPattern      : 'target/jacoco.exec',
              sourcePattern    : '**/src/main/java'
           ])

           /* slack Notification */
           slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
            
        }
        
        unstable {

            echo 'I am unstable :/'

        }
        
        failure {

          /* slack Notification */
          slackSend (color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")

        }
        
        changed {
            echo 'Things were different before...'
        }

    }
}


