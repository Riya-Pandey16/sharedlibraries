def call(String warFilePath,
         String pemKeyPath,
         String remoteHost,
         String remoteUser = "ubuntu",
         String remoteDir = "/opt/deploy") {

    echo "Deploying WAR to ${remoteHost}"

    sh """
        if [ ! -f ${warFilePath} ]; then
            echo "WAR file not found: ${warFilePath}"
            exit 1
        fi
    """

    sh """
        scp -i ${pemKeyPath} -o StrictHostKeyChecking=no \
        ${warFilePath} ${remoteUser}@${remoteHost}:/home/${remoteUser}/
    """

    sh """
        ssh -i ${pemKeyPath} -o StrictHostKeyChecking=no \
        ${remoteUser}@${remoteHost} \
        "sudo mv /home/${remoteUser}/\$(basename ${warFilePath}) ${remoteDir}/"
    """

    echo "Deployment completed"
}
