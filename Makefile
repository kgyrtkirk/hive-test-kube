all:
	git merge master
	docker build -t htk-jenkins htk-jenkins
	
