# Build source

```
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
cd src
./mvnw clean package -Dmaven.test.skip=true
cd ..
```

# Build containers

```
cd src/apache
docker build -t gcr.io/pl-dev-infra/demos/microservice-kafka/apache:1.0 .
cd ../..

cd src/microservice-kafka-invoicing
docker build -t gcr.io/pl-dev-infra/demos/microservice-kafka/invoicing:1.0 .
cd ../..

cd src/microservice-kafka-load-test
docker build -t gcr.io/pl-dev-infra/demos/microservice-kafka/load-test:1.0 .
cd ../..

cd src/microservice-kafka-order
docker build -t gcr.io/pl-dev-infra/demos/microservice-kafka/order:1.0 .
cd ../..

cd src/microservice-kafka-shipping
docker build -t gcr.io/pl-dev-infra/demos/microservice-kafka/shipping:1.0 .
cd ../..

cd src/postgres
docker build -t gcr.io/pl-dev-infra/demos/microservice-kafka/postgres:1.0 .
cd ../..
```

# Push containers

```
docker push gcr.io/pl-dev-infra/demos/microservice-kafka/apache:1.0
docker push gcr.io/pl-dev-infra/demos/microservice-kafka/invoicing:1.0
docker push gcr.io/pl-dev-infra/demos/microservice-kafka/load-test:1.0
docker push gcr.io/pl-dev-infra/demos/microservice-kafka/order:1.0
docker push gcr.io/pl-dev-infra/demos/microservice-kafka/shipping:1.0
docker push gcr.io/pl-dev-infra/demos/microservice-kafka/postgres:1.0
```

# Deploy

To run the demo app:

```
kubectl create namespace px-kafka
cd kubernetes
kubectl apply -f . -n px-kafka
kubectl get services -n px-kafka
cd ..
```

It may take a few minutes for the demo to fully spin up, and some pod restarts along the way are normal.

Visit the apache external IP (with port) to visit the site.
Visit the load-test's external IP (with port) to start a load test.

# Update `px demos` yaml file

1. Build a single yaml file for the demo:

```
kustomize build . >  kafka.yaml
```

2. Copy the yaml file to `pixie/demos/kafka`.
