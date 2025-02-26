pipeline {
     agent {
        docker {
            image 'maven:3.8.6-amazoncorretto-17' // Usa la versión de Maven y JDK que necesites
            label 'maven-agent' // Etiqueta para el agente (opcional)
        }
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm 
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package' 
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test' 
            }
        }

        stage('Deploy') {
            steps {
                sh 'mvn deploy' 
            }
        }
    }
}
