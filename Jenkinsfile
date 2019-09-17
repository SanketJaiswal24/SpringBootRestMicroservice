pipeline {
  
  agent {
        docker 
        { 
          image 'sanketjaiswal12345/new-slaves-master2' 
          args  '--privileged -v /var/run/docker.sock:/var/run/docker.sock'  
        }
    }  
    
    stages {
        stage('Compile stage')
        {
          steps
          {
            sh 'mvn clean compile'
          }
        }
        
        stage('Package Stage') 
        {
            steps 
            {
                 sh 'mvn package'
            }   
        }

        stage('Build Docker Image')
        {
             steps
             {
             sh "docker build -t localhost:5000/spring-boot-apache-derby-docker2.0.0${env.BUILD_NUMBER} ."
             }
        }

        /*Push Docker images in Docker-Hub */
        stage('Push Docker Images'){
           steps
           {
           sh "docker push localhost:5000/spring-boot-apache-derby-docker2.0.0${env.BUILD_NUMBER}"
           }
         }

       /* Run Image in Dev Server*/
    /*    stage('Run Container on Dev Server')
       {  
          options 
          { 
            timeout(time: 15, unit: 'SECONDS') 
          }
        steps
           {
          sh 'docker run -p 8085:8085 localhost:5000/spring-boot-apache-derby-docker2.0.0'
       }
      }  */          

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
           //slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
           slackSend baseUrl: 'https://hooks.slack.com/services/', channel: 'build', color: 'good', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})", tokenCredentialId: 'slack-integration'
            
        }
        
        unstable {

            echo 'I am unstable :/'

        }
        
        failure {

          /* slack Notification */
          //slackSend (color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
          slackSend baseUrl: 'https://hooks.slack.com/services/', channel: 'build', color: 'bad', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})", tokenCredentialId: 'slack-integration'
        }
        
        changed {
            echo 'Things were different before...'
        }

    }
}


