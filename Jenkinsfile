pipeline {
    agent any
    stages {

        stage('Clone and Clean Repo') {
            steps {
                bat '''
                    IF EXIST tp_test_unitaire rmdir /S /Q tp_test_unitaire
                    git clone https://github.com/adrienfranto/tp_test_unitaire
                '''
                bat 'mvn clean -f tp_test_unitaire\\pom.xml'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test -f tp_test_unitaire\\pom.xml'
            }
        }

        stage('Build & SonarQube Analysis') {
            steps {
                bat 'mvn package -f tp_test_unitaire\\pom.xml'
                bat 'mvn deploy -f tp_test_unitaire\\pom.xml'
                
                withCredentials([string(credentialsId: 'demoic_token', variable: 'SONAR_TOKEN')]) {
                    bat """
                        mvn sonar:sonar -f tp_test_unitaire\\pom.xml ^
                        -Dsonar.projectKey=tp_test_unitaire ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.login=%SONAR_TOKEN%
                    """
                }
            }
        }

    }
}
