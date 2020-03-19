all:
	git merge master
	docker build -t kgyrtkirk/htk-jenkins htk-jenkins
	kubectl apply -f k8s/jenkins-deployment.yaml
