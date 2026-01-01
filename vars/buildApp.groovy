def call() {
    echo "Building application using Maven"
    sh "mvn clean package"
}
