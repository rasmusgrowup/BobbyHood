# Repository for the adventure game Bobby Hood

This is the repository for the adventure game Booby Hood
— a clone of the incredibly boring game World Of Zuul.

Bobby Hood is a so-called Facer Simulator.
In the game, you're playing the character
Bobby Hood, who's job is to talk to strangers
and try to persuade them into donating money
for a good cause.

To complete the game, you must find and talk
to all NPCs in the game, return safely to your
boss at UNICEF and return the collected items.

## Workflow

It is recommended to create a new branch to work on,
instead of working on the main branch. Here's how you
would do it:

Remember that you should be in the root directory of your project

### Creating a new branch
Before creating a new branch, pull the changes from upstream.
Your master needs to be up-to-date.

```
$ git pull origin
```

Create the branch on your local machine and switch to this branch :
```
$ git checkout -b [name_of_your_new_branch] origin/main
```
Don't include the brackets. The command could be:
$ git checkout -b awesome_branch origin/main

### Pushing your changes
Push the branch on GitHub :

```
$ git push -u origin [name_of_your_new_branch]
```
The command would then be: $ git push -u origin awesome_branch

After you've pushed your changes to GitHub, you are prompted to
create a pull request. Copy the url from the terminal, and follow
the instructions.

Your changes can then be merged into the main branch by the repo owner.

### Watch out for changes in the main repository

When you come back to your branch after some time away, you should pull
the latest changes:

```
$ git pull
```

### Switching branches

You can switch branches with this command:

```
$ git checkout [name_of_your_branch]
```

Or display a list of existing branches: 

```
$ git branch -a
```

And finally, you can delete a branch:

```
$ git branch -d [name_of_the_branch_you_wish_to_delete]
```