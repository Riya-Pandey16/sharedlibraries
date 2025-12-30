def gitDownload(repo)
{
  git "https://github.com/Riya-Pandey16/${repo}.git"
}
def buildArtifacts()
{
  sh 'mvn package'
}
def deployTomcat(jobname,ip,context)
{
   sh "scp /var/lib/jenkins/workspace/${jobname}/webapp/target/webapp.war ubuntu@${ip}:/var/lib/tomcat10/webapps/${context}.war"
}
def executeSelenium(jobname)
{
  sh "java -jar /var/lib/jenkins/workspace/${jobname}/testing.jar"
}
