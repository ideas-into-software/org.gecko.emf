pipeline  {
    agent any

    tools {
        jdk 'OpenJDK11'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
        skipDefaultCheckout()
    }

    stages {
    	stage('Clean Workspace') {
            steps {
                // Cleanup before starting the stage
                cleanWs()
            }
        }
    	stage('Checkout') {
            steps {
                // Checkout the repository
                checkout scm 
            }
        }
        stage('Main branch release') {
            when { 
                branch 'main' 
            }
            steps {
                echo "I am building on ${env.BRANCH_NAME}"
                sh "./gradlew testOSGi release -Drelease.dir=$JENKINS_HOME/repo.gecko/release/org.gecko.emf --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
            }
        }
        stage('Snapshot branch release') {
            when { 
                branch 'snapshot'
            }
            steps  {
                echo "I am building on ${env.JOB_NAME}"
                sh "./gradlew testOSGi release --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
                sh "mkdir -p $JENKINS_HOME/repo.gecko/snapshot/org.gecko.emf"
                sh "rm -rf $JENKINS_HOME/repo.gecko/snapshot/org.gecko.emf/*"
                sh "cp -r cnf/release/* $JENKINS_HOME/repo.gecko/snapshot/org.gecko.emf"
            }
        }
        stage('Other branch') {
            when {
            	allOf {
            		not {
	                	branch 'snapshot'
	            	}
            		not {
	                	branch 'main'
	            	}
            		not {
	                	branch 'Version_4_x_release'
	            	}
            		not {
	                	branch 'Version_4_x'
	            	}
            	}
            }
            steps  {
                echo "I am building on ${env.JOB_NAME}"
                sh "./gradlew testOSGi --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
            }
        }
        stage('4.x release') {
            when { 
                branch 'Version_4_x_release' 
            }
            steps {
                echo "I am building on ${env.BRANCH_NAME}"
                sh "./gradlew testOSGi release -Drelease.dir=$JENKINS_HOME/repo.gecko/release/org.gecko.emf --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
            }
        }
        stage('4.x snapshot') {
            when { 
                branch 'Version_4_x'
            }
            steps  {
                echo "I am building on ${env.JOB_NAME}"
                sh "./gradlew testOSGi release --info --stacktrace -Dmaven.repo.local=${WORKSPACE}/.m2"
                sh "mkdir -p $JENKINS_HOME/repo.gecko/snapshot/org.gecko.emf_4.6"
                sh "rm -rf $JENKINS_HOME/repo.gecko/snapshot/org.gecko.emf_4.6/*"
                sh "cp -r cnf/release/* $JENKINS_HOME/repo.gecko/snapshot/org.gecko.emf_4.6"
            }
        }
    }

}
