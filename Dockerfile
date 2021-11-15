# Using an Alpine Linux based JDK image
FROM ubuntu:21.04


RUN apt-get update -y

RUN apt install -y curl

RUN apt install -y openjdk-11-jdk

RUN curl -sL https://deb.nodesource.com/setup_17.x | bash - \
  && apt-get install -y nodejs \
  && curl -L https://www.npmjs.com/install.sh | sh

RUN npm install -g readability-cli

RUN mkdir /data

COPY target/pack /srv/myapp

ENTRYPOINT ["sh", "-c", "./srv/myapp/bin/hello"]

