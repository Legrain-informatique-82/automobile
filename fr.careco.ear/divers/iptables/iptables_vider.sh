#!/bin/bash
iptables -t filter -F
iptables -t filter -X
iptables -t nat -F
iptables -t nat -X
