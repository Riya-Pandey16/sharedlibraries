def call(
    String warFilePath,
    String pemKeyPath,
    String remoteHost,
    String remoteUser = "ubuntu",
    String remoteDir = "/opt/deploy"
) {

    echo "🚀 Deploying WAR to ${remoteHost}"

    // Check if WAR file exists
    sh """
        if [ ! -f "${warFilePath}" ]; then
            echo "❌ WAR file not found: ${warFilePath}"
            exit 1
        fi
    """

    // Copy WAR file to remote server using SCP
    sh """
        scp -i "${pemKeyPath}" \
        -o StrictHostKeyChecking=no \
        "${warFilePath}" \
        "${remoteUser}@${remoteHost}:/home/${remoteUser}/"
    """

    // Move WAR file to deployment directory using SSH
    sh """
        ssh -i "${pemKeyPath}" \
        -o StrictHostKeyChecking=no \
        "${remoteUser}@${remoteHost}" \
        "sudo mv /home/${remoteUser}/\$(basename '${warFilePath}') '${remoteDir}/'"
    """

    echo "✅ Deployment completed successfully"
}
