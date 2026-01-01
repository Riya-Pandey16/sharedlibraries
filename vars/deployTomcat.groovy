def call(String server, String warPath) {
    echo "Deploying WAR to ${server}"
    sh """
    ssh ${server} '
        rm -rf /opt/tomcat/webapps/*.war
        rm -rf /opt/tomcat/webapps/*
    '
    scp ${warPath} ${server}:/opt/tomcat/webapps/
    ssh ${server} '/opt/tomcat/bin/startup.sh'
    """
}
