# Integration Testing in Spring Boot

This is a placeholder.  I'll put a better description here Soon™... probably around the same time I clean this code up.  It's a dog's breakfast, but proves that integration testing AND custom repository functionality works.

This is basically a two part example:
#### 1) Creating Custom Repository Functionality
Sometimes you want to access your DB in a very specific way, do very specific things to your data, or even stop letting Spring take the wheel in deciding how you make your queries.  Some of that can be accomplished using the `@Query` annotation, but other times you need a *Custom Repository Implementation* to really dig in.  We explore that here.  I needed an example for work, but also wanted to post examples for the world to benefit.

#### 2) Creating End-to-End Integration Tests
That's really why I'm here.  These tests stand up the whole system, front to back, run the migrations against an H2 database, and save/return real results, right down to actually serializing the data into JSON.  This is more impressive in a big system with lots of moving parts, but suffice to say it's pretty darn awesome.

As an added bonus, you can inject new Configurations so that you can effectively mock slices of the system under test.

>Expect an update in the coming days.  
>
>I'm back in the saddle again, as I've recently finished my side contract and have that "Free time" thing again!!
