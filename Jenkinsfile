#!groovy

pipeline {
  agent any

  triggers {
    githubPush()
  }

  tools {
    jdk 'AdoptOpenJDK 11 (LTS)'
  }

  environment {
    AUTHOR = 'Insung Choi'
    VERSION = "${GIT_BRANCH}_${GIT_COMMIT}"
  }

  stages {
    stage('git status') {
      steps {
        echo "GIT_BRANCH=${GIT_BRANCH}"
        echo "GIT_COMMIT=${GIT_COMMIT}"
        echo "VERSION=${VERSION}"
      }
    }

    stage('spring jar build') {
      steps {
        sh 'mvn clean package spring-boot:repackage'

        echo "spring jar build complete"
      }
    }

    stage('build docker image') {
      steps {

        def username = "${USERNAME}"
        def password = "${PASSWORD}"

        def apiService = 'api-service'
        def appkeyService = 'appkey-service'
        def configServer = 'config-server'
        def gateway = 'gateway'

        docker_build_push projectname: apiService, username:username, password:password
        docker_build_push projectname: appkeyService, username:username, password:password
        docker_build_push projectname: configServer, username:username, password:password
        docker_build_push projectname: gateway, username:username, password:password

        echo "docker image build and push complete"
      }
    }

  }
}

void docker_build_push(buildinfo) {
  echo "buildinfo ${buildinfo} "
  sh "mvn -pl ${buildinfo.projectname} docker:build docker:push \"-Ddocker.username=${buildinfo.username}\" \"-Ddocker.password=${buildinfo.password}\""

  echo "docker images ${buildinfo.projectname} pushed"
}
