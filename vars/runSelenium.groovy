def call() {
    echo "Running Selenium Test Suite"
    dir('FunctionalTesting') {
        sh "mvn test"
    }
}
