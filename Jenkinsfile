pipeline {
    agent any
    stages {
        
        stage('Package Stage') {
              steps {
                 sh 'mvn package'
             }    
        }

        stage('Build Docker Image'){
             steps
             {
          sh 'docker build -t sanketjaiswal12345/spring-boot-apache-derby-docker2.0.0 .'
             }
        }
      
         stage('Push Docker Image'){
           steps
           {
            withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
            sh "docker login -u sanketjaiswal12345 -p ${dockerHubPwd}"
         }
           sh 'docker push sanketjaiswal12345/spring-boot-apache-derby-docker2.0.0'
           }
      }

    stage('Run Container on Dev Server')
    {  
       def dockerRun = 'dokcer run -p 8080:8080 -d -name my-app spring-boot-apache-derby-docker2.0.0'
      steps
         {
           sshagent(['dev-server']) {
           sh "ssh -o StrictHostKeyChecking=no ec2-user@172.31.39.108 ${dockerRun}"
         }
     }
    }
          
    }
    post {
        always {
           echo 'done job'
        }
        
        success {
            echo 'I am Success Done'
            
            junit 'target/**/*.xml'
            step([
              $class           : 'JacocoPublisher',
              execPattern      : 'target/jacoco.exec',
              sourcePattern    : '**/src/main/java'
           ])

           slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
            
        }
        
        unstable {
            echo 'I am unstable :/'
        }
        
        failure {
          slackSend (color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
        
        changed {
            echo 'Things were different before...'
        }
    }
}

