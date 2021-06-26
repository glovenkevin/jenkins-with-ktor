FROM jenkins/jenkins:2.289.1-lts-slim

ARG user=jenkins
ARG group=jenkins
ARG JAVA_XMX=512m
ARG JAVA_XMS=64m
ARG groupid=1001

USER root

RUN apt update \
    && apt install -y build-essential curl wget dpkg \
    apt-transport-https ca-certificates lsb-release gnupg \
    && mkdir /var/log/jenkins

RUN chown -R jenkins:jenkins /var/log/jenkins

# Install docker cli
RUN curl -fsSL https://download.docker.com/linux/debian/gpg | gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg \
    && echo "deb [arch=amd64 signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/debian $(lsb_release -cs) stable" | tee /etc/apt/sources.list.d/docker.list > /dev/null \
    && apt update \
    && apt install -y docker-ce-cli \
    && rm -rf /var/lib/apt/lists/* \
    && groupadd -g ${groupid} docker \
    && usermod -aG docker jenkins

USER $user

ENV JAVA_OPTS="-Xmx${JAVA_XMX} -Xms${JAVA_XMS}"
ENV JENKINS_OPTS="--logfile=/var/log/jenkins/jenkins.log"

VOLUME "/var/run/docker.sock"
EXPOSE 8000
