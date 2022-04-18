## Major creative work

Your major creative work is worth 60% of your unit total and is made up of four components:

  * Milestone 1: 8%
  * Milestone 2: 10%
  * __Milestone 3: 12%__
  * Viva Exam: 30%

## Milestone 3
### Note: due date is Monday 25th of October 2021, at 10:00am (Week 12)
### Note: some edits to provide clarity

### Milestone 2 solution
<iframe height="780" width="1280" allowfullscreen frameborder=0 src="https://echo360.net.au/media/ff48b650-06fb-4c0d-a135-c79870d3d439/public?autoplay=false&automute=false"></iframe>

### Milestone 3 preparation
<iframe height="780" width="1280" allowfullscreen frameborder=0 src="https://echo360.net.au/media/aa24d5d3-c3ec-4805-86b7-301b91858f78/public?autoplay=false&automute=false"></iframe>

You will need to start from the code published in the ["milestone3" branch of the github repository](https://github.com/mq-soft-tech/COMP2000_2021/tree/milestone3/).  **Do not** use the code that you submit for milestone2.

Your game has gone out for testing and the unanimous response from testers was that the turn-taking was frustrating.  The testers have suggested the following alternative model for turn-taking:

  * An Actor can make a move up to once every two seconds.
    * Once the Actor moves, their colour gradually changes to a different hue to indicate how long until their next move.
      * If you are unable to implement a gradual colour change, you can choose to change the colour in a single step but you will receive less marks.
    * Blue Actors change to green immediately after moving, and transition to blue.
    * Red Actors change to yellow immediately after moving, and transition to red.
  * In the following two seconds, all the other characters make their moves "in parallel".  Actors still have a variable number of moves, those moves are made in the time available (i.e. faster if there are more moves to be made).
  * One that two seconds is up, the Actor is able to move again and their colour changes back to normal
  * The game waits for the (human) player to make their move.

**Note**: the key points that you will be assessed on with the new gameplay are that you've used Threads for your Actors, that your code is thread-safe, and that the colour transitions are graduated.

Of course, nothing really runs in parallel in a computer, you will need to use multithreading of some variety to make this work.  How hard this is to implement will depend almost entirely on the design you choose so we recommend you spend a significant amount of time investigating possible designs.  For this reason,  25% of the marks available are set aside for the quality of your discussion.  Note, bad designs with good discussions will still get high marks, full marks are reserved for those who discuss not only their actual solution but at least one other possible design as well.

In addition to the above, your starting code includes new actor type Rogue provided in a jar file (hence no source code), there seems to be a randomly occurring bug with this code that causes the whole game to freeze.

## Tasks

  * Make a change so that the game no longer randomly freezes whenever a Rogue moves.
  * You will need to explicitly choose concurrency patterns in order to implement the new style of game play.
   * 25% of the available marks are set aside for your explanation of which concurrency pattern you have used, why you have chosen it, and how it works in context (global lock, latch, pool, monitor, fork/join, read/write lock, re-entrant lock, atomics, etc).

## Marking
  * (10 marks) `Bug.md` which contains your explanations of what causes the `Rogue` bug.
  * (10 marks) Implement a fix for the `Rogue` bug.
  * (10 marks) `Rogue.md` which contains a description of your solution to the `Rogue` bug.
  * (25 marks) Implement the new game play functionality using whatever approach you think is best.
  * (25 marks) `Threading.md` which contains an explanation of your solution.  Your explanation should include; why you chose that solution, and any problems you encountered in your solution.
  * (20 marks) The remaining marks are for improving the game in some creative way. Implement your changes and write a report explaining all the interesting things you implemented. Include this report in a file called `Improvements.md`.

**Note:** all the `.md` files should include a description of where in the source files you have made changes, additions, corrections, or deletions so that your marker can find your implementation changes easily. Failure to include this information will incur a penalty for each relevant marking item.

## Submission

You must submit a zipped VSCode project.  Your marker will download your zip file, open it in VSCode and run it from there.  Please don't submit unnecessary temporary files (such as class files) in your zip file. (note that rar files will not be accepted).

Your submission file should be named with your student number **only**, and *strictly* follow the following structure (in this example, substitute your student number in place of 43215678):
```
43215678
|  Bug.md
|  Rogue.md
|  Threading.md
|  Improvements.md
+--  data
|    |  stage1.rvb
|    |  stage2.rvb
|    |  ...
|    |  stage4.rvb
|
+--  doc
|    |  milestone3.md
|    |  ...
|    |  style.md
|
+--  src
     |  Actor.java
     |  Cell.java
     |  ...
     |  Water.java

```

Your zip file should not contain any `.class` files, `.git` directories, `.vscode` directories, or anything else apart from `.java`, `.md`,  and`.rvb` files, and any resource *required* to compile and run your program (eg. `rogue.jar` and `full_pack_2025.ttf`).

Failure to follow these requirements may result in your submission not being accepted.

**Note:** some zip utilities, such as the Microsoft Windows desktop will refuse to add some file types such as fonts and executables into a zip file. If that happens don't worry, your marker will put a copy of the relevant file into place when marking your submission.
