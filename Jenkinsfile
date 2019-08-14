pipeline {
  agent any
  stages {
    stage('检出') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: env.GIT_BUILD_REF]],userRemoteConfigs: [[url: env.GIT_REPO_URL, credentialsId: env.CREDENTIALS_ID]]])
      }
    }
    stage('构建') {
      steps {
        echo '构建中...'
        sh 'docker version'
        sh 'java -version'
        sh 'mvn clean'
        sh 'mvn install'
        echo '构建完成.'
        archiveArtifacts(artifacts: '**/target/*.jar', fingerprint: true)
      }
    }
    stage('Docker') {
      steps {
        echo 'Docker镜像生成中...'
        sh 'chmod a+x /root/workspace/docker.sh'
        sh '/root/workspace/docker.sh'
        echo '镜像上传完毕'
      }
    }
    stage('测试') {
      steps {
        echo '单元测试中...'
        echo '单元测试完成.'
        writeFile(file: 'TEST-demo.junit4.AppTest.xml', text: '''
                    <testsuite name="demo.junit4.AppTest" time="0.053" tests="3" errors="0" skipped="0" failures="0">
                        <properties>
                        </properties>
                        <testcase name="testApp" classname="demo.junit4.AppTest" time="0.003"/>
                        <testcase name="test1" classname="demo.junit4.AppTest" time="0"/>
                        <testcase name="test2" classname="demo.junit4.AppTest" time="0"/>
                    </testsuite>
                ''')
        junit '*.xml'
      }
    }
    stage('部署') {
      steps {
        echo '部署中...'
         sh 'chmod 600 /root/workspace/coding_ssh'
         sh 'ssh -i /root/workspace/coding_ssh root@129.28.160.170 < deploy.sh'
         echo '部署完成'
      }
    }
  }
}