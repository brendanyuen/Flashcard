# My Personal Project

## Task 2

My project will be a **flash card application**. It will allow users to write 
questions on one side of the card and the answer on the other. This 
application is targeted for students in preparation for exams to help them 
study. I wanted to make this because I may be able to study for exams by 
using this application.

## User Stories

- As a user, I want to be able to add questions a list of questions
- As a user, I want to be able to add answers a list of answers
- As a user, I want to be able to view my questions and answers
- As a user, I want to be able to see if I answered the question correctly
- As a user, I want to be able to remove questions/ answers
- As a user, I want to be able to save my flashcards
- As a user, I want to be able to load a previously saved flashcard deck

## **Instructions for Grader**

- You can generate the first required event related to adding Xs to a Y by after typing a question and an answer click 
Next card to add a question to a list of questions and answer to list of answers
- You can generate the second required event related to adding Xs to a Y by if there is a list of a size greater than 0 
  then you may click remove which allows the user to remove a card with a question and answer from the list of question and from list of answers
- You can locate my visual component by when an answer is correct after pressing the quiz me button a check mark should
appear and a sad face when it is incorrect
- You can save the state of my application by clicking in the top left corner which says file then click save
- You can reload the state of my application by clicking in the top left corner which says file then click load


## **Phase 4: Task 2**
When the user adds a question "1" and answer "2" then clicks remove card and removes that
question and answer, the console should be:

Thu Nov 24 17:23:11 PST 2022
Question was added to list: 1


Thu Nov 24 17:23:11 PST 2022
Answer was added to list: 2


Thu Nov 24 17:23:13 PST 2022
Question was removed: 1


Thu Nov 24 17:23:13 PST 2022
Answer was removed: 2

Also, if the user keeps the card instead of removing it the console should print out that the question and answer
were added because they use addQuestion/ addAnswer method to add the question/answer back into the list


## **Phase 4: Task 3** ##
I would probably refactor a hierarchy in the UI package. When I was coding my project, I noticed that there was a lot of 
code duplication and repeated methods. For example, most of my UI classes have a JText area or all have a save menu. So if I were to refactor my project, I would include a super class or an interface
that would really help cut down on come code duplication.

Another piece I would refactor would be a class that handles QuestionList/ AnswerList so each class in my UI package don't
have to keep passing the list as a parameter. Instead, I would refactor it so there is one class that allows other classes to access the lists.