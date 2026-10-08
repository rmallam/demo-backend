# demo-backend

Quarkus golden-path backend with Jenkins, Helm, and OpenAPI

Backend for system **acme-demo**. Provides API `demo-backend-api` (`GET /api`). Create the Node.js website template next and pick this Component + API.

## Pipeline

Jenkinsfile stages: **Unit tests → SonarQube → Helm lint**.

Point a Jenkins job named `demo/demo-backend` at this repo. Set credential `SONAR_TOKEN` to run the scanner against `http://sonarqube.sonarqube.svc:9000`.

## Deploy

North-south is **OpenShift Route → Gateway API Gateway → HTTPRoute → app**. AuthZ in this chart is **deny-all** plus ALLOW from the ingress gateway. **STRICT mTLS** is namespace-wide (Kyverno on enroll).

```bash
oc label namespace demo-dev acme.io/mesh-enroll=true istio.io/dataplane-mode=ambient --overwrite
helm upgrade --install demo-backend chart -n demo-dev
```

Ingress: `https://demo-backend-demo-dev.apps.rosa.rosa-89s85.bhg0.p3.openshiftapps.com`

The chart labels pods `backstage.io/kubernetes-id=demo-backend` for Topology.

## Dev Spaces

https://devspaces.apps.rosa.rosa-89s85.bhg0.p3.openshiftapps.com#https://github.com/rmallam/demo-backend?new&devfilePath=devfile.yaml
