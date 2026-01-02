def call(String status) {
    emailext(
        subject: "Jenkins Pipeline Status: ${status}",
        body: "Build ${status}. Job: ${env.JOB_NAME}, Build: ${env.BUILD_NUMBER}",
        to: "your-email@gmail.com"
    )
}
