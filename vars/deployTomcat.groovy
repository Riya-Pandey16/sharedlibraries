def call(String server, String warName) {
    sh """
    echo "Copying WAR to remote server"
    scp target/${warName} ${server}:/opt/deploy/

    echo "Deploying application"
    ssh ${server} '
        sudo systemctl stop tomcat10
        sudo rm -rf /var/lib/tomcat10/webapps/${warName.replace(".war","")}*
        sudo mv /opt/deploy/${warName} /var/lib/tomcat10/webapps/
        sudo systemctl start tomcat10
    '
    """
}
