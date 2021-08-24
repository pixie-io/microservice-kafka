FROM python:3.6

# Install locust
RUN pip install pyzmq locust faker
ADD locustfile.py /config/locustfile.py
EXPOSE 8089