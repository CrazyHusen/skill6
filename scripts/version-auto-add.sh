#!/bin/bash
set -ev
#if [ "${TRAVIS_PULL_REQUEST}" = "false" ]; then
    #version add prepare
	mvn install:install-file -DgroupId=cn.skill6.plugin -DartifactId=vautoadd-maven-plugin -Dversion=1.0.0 -Dpackaging=jar -Dfile=../plugins/vautoadd-maven-plugin-1.0.0.jar
	#global setting
	git config --global user.name "Travis Bot"
	git config --global user.email "husen@hemingsheng.cn"
	ssh-keygen -t rsa -f /home/travis/.ssh/id_rsa -q -N ""
	cd .ssh
	cat id_rsa.pub
	ssh -T git@github.com
	#push to remote
	mvn vautoadd:vadd
	git add .
	git commit -m "Updated Version By One By Travis"
	git push -u origin master
#fi

mvn test -B
exit 0