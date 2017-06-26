#!/usr/bin/env groovy
def mvn( String goals ) {
    sh "./mvnw -s configuration/settings.xml --show-version --batch-mode ${goals}"
}

node {

    stage('Checkout') {
        echo: 'Checkout'
        // Get code from a GitHub repository
        git 'https://github.com/chrispbyrneham/SimpleWeb.git'

    }
    stage('Build') {
        echo: 'Build'
        // M3 is the name of the Maven installation in
        // Jenkins, Manage Jenkins, Global Tool Configuration,
        // Maven, Maven Installations, Name
        mvnHome = tool 'M3'
        sh "'${mvnHome}/bin/mvn' -N io.takari:maven:wrapper -Dmaven=3.3.9 -s configuration/settings.xml"
        mvn '--version'
        mvn 'install -DskipTests'

    }
    stage('Results') {
        echo: 'Results'
        junit '**/target/surefire-reports/TEST-*.xml'
        //archive 'target/*.jar'
    }
}
