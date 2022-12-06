# -How to run BobbyHood-

To run the game you need to install:
Java JDK version 18 or later.
JavaFX SDK version 19 or later.
Locate the JavaFX lib directory.
```
File -> Project Structure -> Libraries + click the "+" -> %path%
```
Next you need to set up your run configurations.
```
Run -> Edit Configurations -> "+" -> New Application -> Modify Options -> Add VM Options
```
In the VM Options you need to enter the following options, 
which is a long string of words.
```
--module-path "/path/to/lib/" --add-modules=javafx.controls,javafx.media,javafx.fxml
```
Make sure you copy the whole thing, else the program won't run.
Choose the main class, which in this game should be:
```
BobbyHood.GUI.BobbyGUI
```
Now you should be able to run the BobbyGUI application.
To run the CLI version of the game you won't need JavaFX.
All you have to do is locate the textUI folder and run the class:
```
BobbyHoodApp
```
If further instruction is needed go to the following website:
https://javabook.bloomu.edu/setupjavafx.html

Although you have to make sure that you use the VM Options pointed out earlier
since it has the javafx.media option included.