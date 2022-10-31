# Repository for the adventure game Bobby Hood

This is the repository for the adventure game Booby Hood
— a clone of the incredibly boring game World Of Zuul.

Bobby Hood is a so-called Facer Simulator.
In the game, you're playing the character
Bobby Hood, who's job is to talk to strangers
and try to persuade them into donating money
for a good cause. If they're not willing to 
donate money, you can try to persuade them
into joining your adventure, by becoming
a volunteer at UNICEF.

## Workflow

Before creating a new branch, pull the changes from upstream.
Your master needs to be up to date.

```
$ git pull
```

Create the branch on your local machine and switch to this branch :
```
$ git checkout -b [name_of_your_new_branch]
```
Don't include the brackets. The command could be:
$ git checkout -b awesome_branch

Push the branch on github :

```
$ git push -u origin [name_of_your_new_branch]
```
The command would then be: $ git push -u origin awesome_branch