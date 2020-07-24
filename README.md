
* current GKE cluster setup:
  * master pool
    * 2 small nodes (n1-highmem-2 (2cpu/13G)
    * executes basic services
      * jenkins
      * artifactory
      * rsync
  * executor pool
    * autoscale up to 8 nodes (e2-custom-12-65536 (12cpu/64G))
    * each node can run around 5 worker pods
    * in total it gives a total of 40 worker pod capacity


