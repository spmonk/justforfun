app\build.gradle是整个项目的主要配置文件

主要有三个节点
1.apply plugin：用于Gradle构建过程的Android插件,同时也可以表明这个项目类型,android,library还是其他
2.android：
3.dependencies：
  1.compile fileTree();//本地库依赖，编译 libs 目录下的所有 jar 包
  2.compile xxx;远程库依赖，格式为group:name:version
  3.compile project();一般是公司内部的项目，或者改第三方库的源码，同时本地又没有搭建私有仓库，才会选择这种方式