node{
  stage('Git Checkout'){
      git url: 'https://github.com/upretiramesh78/rameshdemo2020.git',
          branch:'master'
  }
  stage('MVN Package'){
    def mvnHome = tool name: 'maven3', type: 'maven'
    bat "${mvnHome}/bin/mvn clean package"
  }
  stage('Build Docker Image'){
    sh 'docker build -t krishnaramesh/demo-0.0.1-SNAPSHOT:0.0.1 .'
  }

  stage('Upload Image to DockerHub'){
    withCredentials([string(credentialsId: 'docker-hub', variable: 'password')]) {
      sh "docker login -u krishnaramesh -p ${password}"
    }
    sh 'docker push krishnaramesh/demo-0.0.1-SNAPSHOT:0.0.1'
  }
  }
