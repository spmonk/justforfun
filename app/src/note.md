android 环境分离（版本分离）
1. App项目的环境粗略可以分为：测试环境和开发环境
2. 在没有Gradle的时候，一个App的唯一标识是App的包名(定义在AndroidMainfest.xml上)
   有了Gradle后，一个App的唯一标识是applicaionId,这也就为多版本的实现提供了条件
3. Android有了Gradle，可以设置多个不同的Flavors，每个Flavor都有一个applicationId属性，其实就是App的包名。
4. 注意不同版本的在相同路径下的资源文件名是相同的，但java class所在的文件夹不能和main下的文件夹一致。
5. 对于每一个Build Type都会自动创建一个匹配的sourceSet。默认的路径为：src/<buildtypename>/。这意味着BuildType名称不能是main或者androidTest（因为这两个是由plugin强制实现的）
   ，并且他们互相之间都必须是唯一的。

1.defaultConfig是指的是当前module的默认配置，
  每个Flavors和buildType的包默认都是继承defaultConfig的
  所以在Flavors和buildType中设置不同的配置时，将会覆盖掉defaultConfig的设置

2. 为了让资源文件能够替换，在src下的文件名要和Flavors和buildType中设置的后缀一致。

3.需要明确的是无论是哪个版本
  对于资源文件：默认会引用main目录的资源文件，只有在自己目录下的资源会覆盖main目录相应位置的资源
  对于class文件：默认会引用main目录的class文件，里定义的java类并不会覆盖main目录里相应的java类。两个目录的java类只能有一份，否则会出现类重复的错误。
  如果beta和production版本需要有同个页面不同的实现，在beta和production都有一份拷贝，代码实现可以不同，但main目录下则不能有该类。
  对于valus和androidMainfest.xml文件：gradle将会把这些资源连同main里面的资源一起合并（有相同的属性将会覆盖），而不是文件级别的覆盖
