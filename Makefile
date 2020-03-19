all:
	git merge master
	make doit

doit:
	docker build -t kgyrtkirk/htk-jenkins htk-jenkins
	kubectl apply -f k8s/jenkins-deployment.yaml
	kubectl apply -f k8s/jenkins-service.yaml
