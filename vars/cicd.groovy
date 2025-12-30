def gitDownload(repo)
{
  git "https://github.com/Riya-Pandey16/${repo}.git"
}
def buildArtifacts()
{
  sh 'mvn package'
}
