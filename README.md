# Android数据存储之SharedPreferences总结
## 一.将数据存入SharedPreferences中
使用SharedPreferences类存储时，首先需要调用getSharedPreferences（String name, int mode)方法获取实例对象。
该方法两个参数，

- String name ：表示文件名
- int mode ：表示文件操作格式[^1]

[^1]:  文件的操作格式都有：
MODE_PRIVATE：默认操作模式，和直接传0效果相同，表示只有当前应用程序才可以对这个SharedPreferences文件进行读写
MODE_WORLD_READABLE：指定此SharedPreferences对其他程序只读且无法修改。
MODE_WORLD_WRITEABLE：指定此SharedPreferences能被其他程序读写。
MODE_MULTI_PROCESS：Android2.3之后已经弃之不用了。

由于该对象本身只能获取数据，不能对数据进行存储和修改，因此需要调用SharedPreferences累的edit()方法获取课编辑的Editor对象，最后通过该对象的putXxx()方法存储数据。
例：
```
 SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
 SharedPreferences.Editor editor=sp.edit();    //获取编辑器
 editor.putString("name","小陈");      //存入String类型数据
 editor.putInt("age","20");            //存入int类型数据
 editor.commit();        //提交修改
```
由上述代码可以，Editor对象是以 Key/value的形式保存数据的，并且根据数据类型的不同，会调用不同的方法，需要注意的是，操作完数据后。
==一定要调用commit（）方法进行数据提交，否则所有操作不生效==

## 二.读取与删除SharedPreferences中的数据
### 1.读取SharedPreferences中的数据
只需要获取SharedPreferences对象，然后通过该对象的getXXX()方法根据相应Key值获取到value的值即可。
例：
```
SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
String data=sp.getString("name","");     //获取用户名
```
主要注意的是，getXXX()方法的第二个参数为缺省值，如果sp中不存在该Key，将返回缺省值。
### 2.删除SharedPreferences中的数据
如果需要删除SharedPreferences中的数据，则需要调用Editor对象的remove（String Key）方法或者clear()方法即可。
例：
```
editor.remove("name");    //删除一条数据
editor.clear();               //删除所有数据
```
