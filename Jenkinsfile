pipeline {
    agent any
    stages {
        
        stage ('Compile Stage') {
            steps {
                withMaven(maven : 'maven_3_6_1') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage ('Package Stage') {
            steps {
                withMaven(maven : 'maven_3_6_1') {
                    sh 'mvn package'
                }
            }
        }

     post {
        always {
           slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
        failure {
           slackSend (color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
        }
    } 
    }
}
