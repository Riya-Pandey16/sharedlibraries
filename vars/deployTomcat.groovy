// vars/deployTomcat.groovy
def call(String warFilePath, String pemKeyPath, String remoteHost, String remoteUser = "ubuntu", String remoteDir = "/opt/deploy") {
    echo "Starting deployment of WAR: ${warFilePath} to ${remoteHost}:${remoteDir}"

    // Temporary directory on remote server
    def tempDir = "/home/${remoteUser}"

    // Verify WAR exists
    sh """
        if [ ! -f ${warFilePath} ]; then
            echo "WAR file not found at ${warFilePath}"
            exit 1
        fi
    """

    // Copy WAR to remote server
    sh """
        scp -i ${pemKeyPath} -o StrictHostKeyChecking=no ${warFilePath} ${remoteUser}@${remoteHost}:${tempDir}/ || { echo 'SCP failed!'; exit 1; }
    """

    // Move WAR to final destination with sudo
    sh """
        ssh -i ${pemKeyPath} -o StrictHostKeyChecking=no ${remoteUser}@${remoteHost} \\
        "sudo mv ${tempDir}/\$(basename ${warFilePath}) ${remoteDir}/ && sudo chown ${remoteUser}:${remoteUser} ${remoteDir}/\$(basename ${warFilePath})" || { echo 'Remote move failed!'; exit 1; }
    """

    echo "Deployment completed successfully!"
}
