pipeline {
  
  /*Jenkins Slave is Docker Container*/
  agent {
        docker 
        { 
          image 'sanketjaiswal12345/new-slaves-master2' 
          args  '--privileged -v /var/run/docker.sock:/var/run/docker.sock'  
        }
    }  

    stages {

        /*Compile stage*/
        stage('Compile stage')
        {
          steps
          {
            sh 'mvn clean compile'
          }
        }

        /*Package stage*/  
        stage('Package Stage') 
        {
            steps 
            {
           sh 'mvn package'
            }   
        }
        
        /*Build Dcoker Image*/ 
        stage('Build Docker Image')
        {
             steps
             {
           sh "docker build -t localhost:5000/spring-boot-apache-derby-docker2.0.0${env.BUILD_NUMBER} ."
             }
        }

        /*Push Docker images in Private-Docker-Registry */
        stage('Push Docker Images'){
           steps
           {
           sh "docker push localhost:5000/spring-boot-apache-derby-docker2.0.0${env.BUILD_NUMBER}"
           }
         }

         stage('Publish Jacoca')
         {
           steps
           {
              /*Publish Jacoca Report in Jenkins Dashboard */
            junit 'target/**/*.xml'
            step([
              $class           : 'JacocoPublisher',
              execPattern      : 'target/jacoco.exec',
              sourcePattern    : '**/src/main/java'
           ])
           }
         }

       /* Run Image in Dev Server*/
        stage('Run Container on Dev Server')
       {  
        steps
           {
          sh "docker run -p 8085:8085 localhost:5000/spring-boot-apache-derby-docker2.0.0${env.BUILD_NUMBER}"
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
            
           /*slack Notification Incomming Webhook*/
           slackSend baseUrl: 'https://hooks.slack.com/services/', channel: 'build', color: 'good', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})", tokenCredentialId: 'slack-integration'
            
        }
        
        unstable {

            echo 'I am unstable :/'

        }
        
        failure {

          /*slack Notification Incomming Webhook*/
          slackSend baseUrl: 'https://hooks.slack.com/services/', channel: 'build', color: 'bad', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})", tokenCredentialId: 'slack-integration'
       
        }
        
        changed {

            echo 'Things were different before...'

        }

    }
}


