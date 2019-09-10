pipeline {
  agent {
        docker { image 'bibinwilson/jenkins-slave' }
    } 
    stages {

        stage('compile stage')
        {
          steps
          {
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

   /*  stage('Run Container on Dev Server')
    {  
        steps
           {
            withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
            sh "docker login -u sanketjaiswal12345 -p ${dockerHubPwd}"
           }
          sh 'docker run -p 8085:8085 sanketjaiswal12345/spring-boot-apache-derby-docker2.0.0'
       }
      }       */     
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


