# Docker Container created for uService platform


FROM dockerfile/java:oracle-java8
MAINTAINER Qiang Han <qiang.han@machool.com>

RUN apt-get update
RUN apt-get install -y unzip
RUN pwd
RUN wget --progress=dot:giga http://downloads.typesafe.com/typesafe-activator/1.2.7/typesafe-activator-1.2.7.zip && \
    mv typesafe-activator*.zip /home/ && \
    cd /home && \
    unzip typesafe*.zip && \
    rm typesafe*.zip && \
    ln -s /home/activator-1.2.7 /home/activator-latest
RUN cd /home/activator-latest/

# Configure Activator
ENV HOME /home
WORKDIR /home
RUN ls -al ~/
RUN mkdir ~/.activator
RUN echo '-Dhttp.nonProxyHost="localhost|127.0.0.1"' >> ~/.activator/activatorconfig.txt
# Run activator
RUN ls -al
#RUN export PATH=$PATH:/home/activator-latest
ENV PATH $PATH:/home/activator-latest


VOLUME ["/home/activator-latest"]
EXPOSE 8888 9000

ADD . /code/src/
WORKDIR /code/src/

CMD ["activator",  "run 9000"]

#CMD ["/home/activator-latest/activator","ui","-Dhttp.address=0.0.0.0","-Dorg.slf4j.simpleLogger.defaultLogLevel=trace"]
