def call(String server, String warPattern) {
    sh """
    echo "Copying WAR to remote server"
    ls -l target

    scp -o StrictHostKeyChecking=no target/${warPattern} ${server}:/opt/deploy/

    echo "Deploying application"
    ssh -o StrictHostKeyChecking=no ${server} '
        sudo systemctl stop tomcat10
        sudo rm -rf /var/lib/tomcat10/webapps/*
        sudo mv /opt/deploy/*.war /var/lib/tomcat10/webapps/
        sudo systemctl start tomcat10
    '
    """
}
