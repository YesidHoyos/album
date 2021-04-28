FROM openjdk:8-jre-alpine
RUN mkdir -p /usr/src/apps/woloxgram/album
COPY target/album-0.0.1-SNAPSHOT.jar /usr/src/apps/woloxgram/album
WORKDIR /usr/src/apps/woloxgram/album
CMD ["nohup", "java", "-jar", "album-0.0.1-SNAPSHOT.jar", "&"]