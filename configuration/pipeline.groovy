#!/usr/bin/env groovy
def mvn( String goals ) {
    sh "./mvnw -s configuration/settings.xml --show-version --batch-mode ${goals}"
}


String get_application_name() {
    pom = readMavenPom file: 'pom.xml'
    pom.artifactId
}

String get_application_version() {
    pom = readMavenPom file: 'pom.xml'
    pom.version
}





node {
    def mvnHome

    stage('Preparation') { // for display purposes
        System.out.println("Preparation");
        // Get some code from a GitHub repository
        git 'https://github.com/jglick/simple-maven-project-with-tests.git'
        // Get the Maven tool.
        // ** NOTE: This 'M3' Maven tool must be configured
        // **       in the global configuration.
        mvnHome = tool 'M3'
    }
    stage('Build') {
        System.out.println("Build");
        // Run the maven build
        if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
        } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
        }
    }
    stage('Results') {
        System.out.println("Results");
        junit '**/target/surefire-reports/TEST-*.xml'
        archive 'target/*.jar'
    }
}