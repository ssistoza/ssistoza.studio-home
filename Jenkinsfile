node {

  def host
  def ip
  def artifact
  def channel = "#pipeline-test"
  def port = "80"


  try {

    stage('Prepare') {
      echo "Preparation: Tool Check"
      sh "java -version"
      sh "mvn -version"
      sh "node --version"
      sh "npm --version"

      echo "Preparation: Setting Vars"
      ip = "localhost"
      host = "shane@${ip}"
      artifact = "ssistoza.studio-home-0.0.1-SNAPSHOT.jar"
      channel = "#home-studio-pipeline"

      echo "Preparation: Clear Workspace"
      cleanWs()

      echo "Preparation: Send Slack Notification"
      slackSend (color: '#007BFF', channel: "${channel}", message: "STARTED: Project: ${env.JOB_NAME} [${env.BUILD_NUMBER}] \nJenkins URL: (${env.JENKINS_URL})")
    }
    stage('Git') {
      echo "Git: Cloning Repository"
      git branch: 'master', url: 'https://github.com/ssistoza/ssistoza.studio-home'
    }
    stage('Npm') {
      echo "Install all node modules"
      sh "npm install"

      echo "Build Static Files"
      sh "npm run build"
    }
    stage('Code Analysis') {
      echo "SonarCloud Scanner Analysis"
      withCredentials([string(credentialsId: 'sonar-login', variable: 'TOKEN')]) { 
        sh "sonar-scanner -Dsonar.projectKey=ssistoza.studio-home -Dsonar.organization=ssistoza-github -Dsonar.sources=src -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=${TOKEN}"
      }
    }
    stage('Mvn') {
      echo "Prepping into Spring Boot"
      sh "cp -a dist/. spring-boot-server/src/main/resources/static"
      
      echo "Building Artifact"
      sh "mvn -f spring-boot-server/pom.xml -Dmaven.test.failure.ignore clean package"
    }
    stage('Docker') {
      
      withCredentials([sshUserPrivateKey(credentialsId: 'jenkins-server', keyFileVariable: 'KEY')]) {
        
        echo "Docker Phase Step 1: Move Dockerfile"
        sh "scp -i ${KEY} Dockerfile ${host}:docker/home"

        echo "Docker Phase Step 2: Move Artifact"
        sh "scp -i ${KEY} spring-boot-server/target/${artifact} ${host}:docker/home"

        echo "Docker Phase Step 3: Kill previous deployment."
        sh "ssh -i ${KEY} ${host} docker stop home"
        sh "ssh -i ${KEY} ${host} docker container rm home"
        sh "ssh -i ${KEY} ${host} docker rmi home"

        echo "Docker Phase Step 4: Build Image."
        sh "ssh -i ${KEY} ${host} docker build --rm -t home docker/home"

        echo "Deploying Phase Step 5: Deploy new spring boot artifact."
        sh "ssh -i ${KEY} ${host} docker run --name home -p ${port}:${port} -d home"        
      }

      // Notify Successful
      slackSend (color: '#28A745', channel: "${channel}", message: "SUCCESSFUL: Project '${env.JOB_NAME} [${env.BUILD_NUMBER}]' \nDeploy URL: (http://ssistoza.studio)")
    } 
  }
  catch(e) {
    // Notify Failed
    slackSend (color: '#DC3545', channel: "${channel}", message: "FAILED: Project '${env.JOB_NAME} [${env.BUILD_NUMBER}]' \nJenkins URL: (${env.JENKINS_URL})")
  }
}