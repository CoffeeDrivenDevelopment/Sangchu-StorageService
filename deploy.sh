#!/bin/sh

##########
# ENV
##########

export STORAGE_SERVICE_IMAGE_NAME="cdd/storage-service"
export STORAGE_SERVICE_TAG_NAME="0.0.1"

##########
# Build Api Gateway Image
##########

echo "\n🗑 Start Delete Docker Files"

if docker image inspect $STORAGE_SERVICE_IMAGE_NAME:$STORAGE_SERVICE_TAG_NAME &> /dev/null; then
    docker image rm -f $STORAGE_SERVICE_IMAGE_NAME:$STORAGE_SERVICE_TAG_NAME
fi

echo "\n🔨 Start Build Docker Image"

docker build \
-t $STORAGE_SERVICE_IMAGE_NAME:$STORAGE_SERVICE_TAG_NAME .

##########
# Api Gateway Container Start
##########

if [ "$(docker ps -aq -f name=$STORAGE_SERVICE_NAME)" ]; then
    echo "🚫 Stop Docker Container : "
    docker rm -f $STORAGE_SERVICE_NAME
else
    echo "🚫 There is no running container named $STORAGE_SERVICE_NAME"
fi

echo "🚀 Docker $STORAGE_SERVICE_NAME Container Start! : "
docker run -d \
--name $STORAGE_SERVICE_NAME \
-p $STORAGE_SERVICE_PORT:$STORAGE_SERVICE_PORT \
-e PROFILE=$STORAGE_SERVICE_PROFILE \
$STORAGE_SERVICE_IMAGE_NAME:$STORAGE_SERVICE_TAG_NAME