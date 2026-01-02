def call(String server, String warPath) {
    sh """
    echo "Verifying WAR location"
    ls -l webapp/target

    echo "Copying WAR to remote server"
    scp -i /home/ubuntu/cicd.pem -o StrictHostKeyChecking=no webapp/target/webapp.war ubuntu@172.31.77.87:/home/ubuntu/


    echo "Deploying application"
   ssh -i /home/ubuntu/cicd.pem ubuntu@172.31.77.87 "sudo mv /home/ubuntu/webapp.war /opt/deploy/"
