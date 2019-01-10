#!/usr/bin/env bash

echo -n "123456" > password

kubectl create secret generic --from-file=password password
