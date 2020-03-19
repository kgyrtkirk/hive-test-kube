
create kubernetes cluster

```
gcloud beta container --project "hive-tests" clusters create "durian" --zone "us-central1-c" --no-enable-basic-auth --cluster-version "1.15.9-gke.8" --machine-type "n2-standard-2" --image-type "COS" --disk-type "pd-ssd" --disk-size "100" --node-labels type=base --metadata disable-legacy-endpoints=true --scopes "https://www.googleapis.com/auth/devstorage.read_only","https://www.googleapis.com/auth/logging.write","https://www.googleapis.com/auth/monitoring","https://www.googleapis.com/auth/servicecontrol","https://www.googleapis.com/auth/service.management.readonly","https://www.googleapis.com/auth/trace.append" --max-pods-per-node "8" --num-nodes "1" --enable-stackdriver-kubernetes --enable-ip-alias --network "projects/hive-tests/global/networks/default" --subnetwork "projects/hive-tests/regions/us-central1/subnetworks/default" --default-max-pods-per-node "16" --no-enable-master-authorized-networks --addons HorizontalPodAutoscaling,HttpLoadBalancing --enable-autoupgrade --enable-autorepair && gcloud beta container --project "hive-tests" node-pools create "executors" --cluster "durian" --zone "us-central1-c" --node-version "1.15.9-gke.8" --machine-type "n2-standard-8" --image-type "COS" --disk-type "pd-ssd" --disk-size "100" --node-labels type=executor --metadata disable-legacy-endpoints=true --scopes "https://www.googleapis.com/auth/devstorage.read_only","https://www.googleapis.com/auth/logging.write","https://www.googleapis.com/auth/monitoring","https://www.googleapis.com/auth/servicecontrol","https://www.googleapis.com/auth/service.management.readonly","https://www.googleapis.com/auth/trace.append" --num-nodes "4" --enable-autoscaling --min-nodes "0" --max-nodes "4" --enable-autoupgrade --enable-autorepair --max-pods-per-node "16"
```


same in json:
```
POST https://container.googleapis.com/v1beta1/projects/hive-tests/zones/us-central1-c/clusters
{
  "cluster": {
    "name": "durian",
    "masterAuth": {
      "clientCertificateConfig": {}
    },
    "network": "projects/hive-tests/global/networks/default",
    "addonsConfig": {
      "httpLoadBalancing": {},
      "horizontalPodAutoscaling": {},
      "kubernetesDashboard": {
        "disabled": true
      },
      "istioConfig": {
        "disabled": true
      },
      "dnsCacheConfig": {}
    },
    "subnetwork": "projects/hive-tests/regions/us-central1/subnetworks/default",
    "nodePools": [
      {
        "name": "base-services",
        "config": {
          "machineType": "n2-standard-2",
          "diskSizeGb": 100,
          "oauthScopes": [
            "https://www.googleapis.com/auth/devstorage.read_only",
            "https://www.googleapis.com/auth/logging.write",
            "https://www.googleapis.com/auth/monitoring",
            "https://www.googleapis.com/auth/servicecontrol",
            "https://www.googleapis.com/auth/service.management.readonly",
            "https://www.googleapis.com/auth/trace.append"
          ],
          "metadata": {
            "disable-legacy-endpoints": "true"
          },
          "imageType": "COS",
          "labels": {
            "type": "base"
          },
          "diskType": "pd-ssd"
        },
        "initialNodeCount": 1,
        "autoscaling": {},
        "management": {
          "autoUpgrade": true,
          "autoRepair": true
        },
        "maxPodsConstraint": {
          "maxPodsPerNode": "8"
        },
        "version": "1.15.9-gke.8"
      },
      {
        "name": "executors",
        "config": {
          "machineType": "n2-standard-8",
          "diskSizeGb": 100,
          "oauthScopes": [
            "https://www.googleapis.com/auth/devstorage.read_only",
            "https://www.googleapis.com/auth/logging.write",
            "https://www.googleapis.com/auth/monitoring",
            "https://www.googleapis.com/auth/servicecontrol",
            "https://www.googleapis.com/auth/service.management.readonly",
            "https://www.googleapis.com/auth/trace.append"
          ],
          "metadata": {
            "disable-legacy-endpoints": "true"
          },
          "imageType": "COS",
          "labels": {
            "type": "executor"
          },
          "diskType": "pd-ssd"
        },
        "initialNodeCount": 4,
        "autoscaling": {
          "enabled": true,
          "maxNodeCount": 4
        },
        "management": {
          "autoUpgrade": true,
          "autoRepair": true
        },
        "maxPodsConstraint": {
          "maxPodsPerNode": "16"
        },
        "version": "1.15.9-gke.8"
      }
    ],
    "networkPolicy": {},
    "ipAllocationPolicy": {
      "useIpAliases": true
    },
    "masterAuthorizedNetworksConfig": {},
    "defaultMaxPodsConstraint": {
      "maxPodsPerNode": "16"
    },
    "authenticatorGroupsConfig": {},
    "privateClusterConfig": {},
    "databaseEncryption": {
      "state": "DECRYPTED"
    },
    "clusterTelemetry": {
      "type": "ENABLED"
    },
    "initialClusterVersion": "1.15.9-gke.8",
    "location": "us-central1-c"
  }
}
```