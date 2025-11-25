pipeline {
    agent any

    environment {
        IMAGE_NAME = "kruthim/stapp"
        IMAGE_TAG  = "${BUILD_NUMBER}"          // every pipeline run creates new version
        KUBECONFIG = "c:\\users\\test\\.kube\\config"
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

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    withEnv(["KUBECONFIG=${KUBECONFIG}"]) {
                        // Apply YAML only once (they always reference :latest)
                        bat "kubectl apply -f deployment.yaml"
                        bat "kubectl apply -f service.yaml"

                        // Force pods to pull new :latest image
                        bat "kubectl rollout restart deployment stapp-deployment"
                    }
                }
            }
        }
    }

    post {
        success {
            echo "🚀 CI/CD Success — Docker image pushed & Kubernetes updated!"
        }
        failure {
            echo "❌ Pipeline Failed — Check logs"
        }
    }
}
