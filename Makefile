all:
	git merge master
	make doit


doit:
	docker build -t kgyrtkirk/htk-jenkins htk-jenkins
	#docker build -t kgyrtkirk/htk-artifactory htk-artifactory
	docker push kgyrtkirk/htk-jenkins:latest
	exit 1
	#kubectl delete deployment.apps/jenkins
	#kubectl delete deployment.apps/artifactory
	#kubectl apply -f k8s/service-account.yaml
	#kubectl apply -f k8s/jenkins-deployment.yaml
	#kubectl apply -f k8s/jenkins-service.yaml
	#kubectl apply -f k8s/archiva.yaml
	#kubectl apply -f k8s/archiva-service.yaml
	kubectl get all
