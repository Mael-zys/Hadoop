a.txt 是我们存slave名单的文件，其他txt文件以及prince.zip和test_100000.zip都是测试文件

Base.java (Base.jar)是sequential那部分的
运行：java -jar Base.jar test.txt

Master.java, Slave1.java, MyThread.java, MyThread2.java和DistriThread.java (Master.jar, Slave.jar)是hadoop那部分的
运行：java -jar Master.jar test.txt

repeat.java是我们自己写的用来复制txt文件1000000遍，测试的时候可以用这个来生成大文件，记得及时停止，不然100k的文件会变成100G左右。
运行：java   repeat  test.txt（要复制的文件）test5.txt（要生成的文件）
                            