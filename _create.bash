P=gcp-hive-upstream
gcloud beta container --project "$P" \
	clusters create "cluster-1" --zone "us-central1-c" \
	--no-enable-basic-auth --cluster-version "1.14.10-gke.27" \
	--machine-type "n1-standard-4" --image-type "COS" \
	--disk-type "pd-standard" --disk-size "100" \
	--node-labels type=core --metadata disable-legacy-endpoints=true \
	--scopes "https://www.googleapis.com/auth/cloud-platform" \
	--num-nodes "1" --enable-stackdriver-kubernetes \
	--enable-ip-alias --network "projects/$P/global/networks/default" \
	--subnetwork "projects/$P/regions/us-central1/subnetworks/default" \
	--default-max-pods-per-node "110" --no-enable-master-authorized-networks \
	--addons HorizontalPodAutoscaling,HttpLoadBalancing --enable-autoupgrade --enable-autorepair

