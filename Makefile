all:
	git merge master
	make doit


doit:
	docker build -t kgyrtkirk/htk-jenkins htk-jenkins
	kubectl delete deployment.apps/jenkins
	kubectl apply -f k8s/service-account.yaml
	kubectl apply -f k8s/jenkins-deployment.yaml
	kubectl apply -f k8s/jenkins-service.yaml
	kubectl get all
