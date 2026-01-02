def call(String server, String warPath) {
    sh """
    echo "Verifying WAR location"
    ls -l webapp/target

    echo "Copying WAR to remote server"
    scp -o StrictHostKeyChecking=no ${warPath} ${server}:/opt/deploy/

    echo "Deploying application"
    ssh -o StrictHostKeyChecking=no ${server} '
        sudo systemctl stop tomcat10
        sudo rm -rf /var/lib/tomcat10/webapps/*
        sudo mv /opt/deploy/*.war /var/lib/tomcat10/webapps/
        sudo systemctl start tomcat10
    '
    """
}
