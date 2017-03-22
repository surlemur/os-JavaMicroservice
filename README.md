# Java Microservice for OpenShift

## OpenShift Build/Deploy

For the OpenShift build/deploy using the S2I Redhat OpenJDK 8 Profile, the following build Environment Variables have to be set:

```
JAVA_MAIN_CLASS     com.zuehlke.doa.MyMain
ARTIFACT_COPY_ARGS  *.jar dependency/*
MAVEN_ARGS          package dependency:copy-dependencies -DskipTests -Dcom.redhat.xpaas.repo.redhatga
```
