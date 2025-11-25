pipeline {
    agent any

    environment {
        APP_NAME      = "stapp"
        IMAGE_NAME    = "kruthim/stapp"
        IMAGE_TAG     = "${BUILD_NUMBER}"
        APP_PORT      = "8100"
        NODE_PORT     = "30082"
        REPLICA_COUNT = "2"
        KUBECONFIG    = "c:\\users\\test\\.kube\\config"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Srikruthi-M/stapp.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} -f Dockerfile ."
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat "docker login -u %DOCKER_USER% -p %DOCKER_PASS%"
                    bat "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${IMAGE_NAME}:latest"
                    bat "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                    bat "docker push ${IMAGE_NAME}:latest"
                }
            }
        }

        stage('Update Kubernetes Deployment') {
            steps {
                script {
                    withEnv(["KUBECONFIG=${KUBECONFIG}"]) {
                        bat "envsubst < deployment.yaml > deployment-final.yaml"
                        bat "envsubst < service.yaml > service-final.yaml"
                        bat "kubectl apply -f deployment-final.yaml"
                        bat "kubectl apply -f service-final.yaml"
                    }
                }
            }
        }
    }

    post {
        success {
            echo "🚀 CI/CD Completed Successfully — Docker image pushed & Kubernetes updated!"
        }
        failure {
            echo "❌ Pipeline Failed — Check logs"
        }
    }
}
