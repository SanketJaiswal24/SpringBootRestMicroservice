
pipeline {
    agent any
    stages {
        stage('Compile Stage') {
             withMaven(maven : 'maven_3_6_1') {
                    sh 'mvn clean compile'
                }
        }
   
         stage('Package Stage') {
             withMaven(maven : 'maven_3_6_1') {
                    sh 'mvn package'
                }
        }
    
    }
    post {
        always {
          slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
        success {
            echo 'I succeeeded!'
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

