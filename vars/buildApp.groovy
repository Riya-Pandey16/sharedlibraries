def call() {
    echo "Building application using Maven"
    sh "mvn package"
}

stage('Verify WAR') {
    agent { label 'myslave' }
    steps {
        sh '''
            echo "Workspace:"
            pwd
            echo "Listing target directory:"
            ls -l target || echo "❌ target directory not found"
        '''
    }
}


