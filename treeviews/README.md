# treeviews

Shows two ways of making a treeview. 

Run either from an IDE or with
````
mvn exec:java -Dexec.mainClass=no.tornado.fxsample.treeviews.TreeViewAppKt
````

The left treeview is made using a Parent-child pattern often used with relational databases. The java version is

````
public Class Group {
   private List<Group> children;
   private String name;
   
   // getters and setters here
}
````

The right treeview uses filtering and mapping to build a treeview based on the content of a class.
