# Mobile Developer Internship Test
A test for those applying to the Mobile Developer Internship at HasGeek - hasjob.co/hasgeek.com/heomz

This is an Android based evaluation to test you ability and speed to pick up something relatively new.

This is a simple app to fetch the repositories of an organisation using the GitHub API with Retrofit and GSON and load it into a RecyclerView.
We will be using the simplest usecase of RxJava to achieve this.

I have setup the framework for the app already, you will need to fill out the parts where I am throwing a `RuntimeException` with a bit of a hint on what is needed there.

## Please submit a Pull Request to this repository with a working version of the app.

## Keep in mind

- Try and keep your commits messages clean, and leave comments explaining what you are doing wherever it makes sense. Also try and use meaningful variable/function names, and maintain indentation and code style.
- This may seem some difficult to some of you, `RxJava`, `Retrofit` and `GSON` are all non-standard Android libraries, but that is part of the test - this is really simple example, and a quick search online should give you the code snippets to fill these out. You don't need to know everything about `RxJava` to solve this. I'm trying to gauge whether you can understand (roughly) how these things fit together.
- There isn't a single solution to this problem, you are welcome to solve it in whatever way you see fit. Just leave a comment above your implementation explaining what you are doing.
- You will have to remove the empty constructor of the `Repository` class once you've filled out the fields - GSON will throw an error otherwise