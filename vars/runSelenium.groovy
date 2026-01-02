def call() {
    echo "Running Selenium Tests from JAR"

    dir('FunctionalTesting') {
        sh '''
            if [ ! -f testing.jar ]; then
                echo "testing.jar not found!"
                exit 1
            fi

            java -jar testing.jar
        '''
    }
}
