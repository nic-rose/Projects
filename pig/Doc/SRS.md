# QuiqStiq SRS

# Contents
* Overview
* Definitions
* Requirements
* Non-goals
* Deliverables
* Scenarios
* Navigation
* Activities
* Package Collection
* Future Versions

# Overview
This project is a tool that will help all people memorize some chunk of text.  It will be able to track the users' process of putting the text into long-term memory.

This spec is *not* all inclusive and there are many specific details that will be figured out later.

# Definitions
## Source
The original body of text that is to be memorized.
## Line
The smallest chunk that the source can be broken into.
## Set
A collection of lines.
## Subset
A smaller set wholly contained in a set.
## User
A person who has registered an account with the software.
## Activities
An enjoyable method to assist the user in memorizing text.

# Requirements
1. The application supports any chunk of text.
2. Improves retention of the text in the user's long-term memory
3. Multiple user activities.
4. The software doesn't have the user type out full words.
5. Has an interactive UX.
6. The software supports an android version.
7. The software shows proficiency level on lines and sets.
    * Proficiency increments with success.
    * Proficiency decrements with failure or time.

# Non-Goals
This version will *not* support the following features, although they will be discussed in the "Future Versions" section: 
- Backend Server
- Complete Texts
- Gamification
- User Settings

# Deliverables
Throughout the project we will deliver the following:
- Android App
- Design Document
- Source Code
- SRS
- UML Diagram
- Use Case Diagram

# Scenarios 
## Scenario 1: Stephan.
Stephan is a busy computer science professor that is always helping his students develop their skills in problem solves and coding.  With this time-intensive job he finds that he does not have the time it takes to memorize the verses he desires.  When he does find the time to sit down and try and memorize verses he finds the process slow and frustrating. With this application, Stephan will be able to spend less time to memorize more verses than he would be able to using conventional methods.  

## Scenario 2: Josie 
Josie is a nine-year-old girl who has gone to church her entire life. Her  "homework" for Sunday School was to memorize John 3:17.  Her mother has used KwiqStiq a little and decided it would be a good replacement to Temple Run Two as the go-to game for her daughter to play on her phone. The application allows Josie to memorize her memory verses for Sunday school in a fun way.

# Navigation
* The home page will show verse packages will be shown with a progress bar of the entire package. 
* Clicking on one of these packages shows the list of verse references. 
* Clicking on one of the verses will give the text and the three activity options and a "Random Selection" button. 
* Each activity will have a progress bar for the individual verse.
* There will be a back button to return to the home page on the verse screens and during the activities.
* After the activity is played, the app will return to the home screen.

# Activities
## Proficiency Levels
We will give a proficiency level of each verse on a scale of 1-100. The difficulty will be modified based on the user's proficiency.  The maximum increase and decrease of proficiency level per one round of an activity are 10. A user cannot go above 100 or below 0.

Regardless of difficulty level, if the user plays an activity perfectly, their proficiency increases by 10. If they get everything wrong, they are decremented by 10. Anything in between will be based off a calculation.

## Activity List
### Letter Selection
Words in the selected verse will be removed and replaced with underlines. The user can choose from a list of four letters, in alphabetical order, which letter the first blank begins with. The word will be filled in when the correct letter is chosen. If the incorrect letter is chosen, the letter will turn red and the user can choose again.

The amount of given words will be relative to the user's proficiency level. The underline lengths will be relative to that of the word. The passage with previously filled blanks will be on the top of the screen.

### Word Selection
Words in the selected verse will be removed and replaced with underlines. The player is given two choices: the correct word and another word from the verse. If the right word is selected the game moves onto the next choice of two words. If the wrong choice is selected the button will show an "X", then the user can try again.

The amount of given words will be relative to the user's proficiency level. The underlines length will be relative to that of the word. The passage with previously filled blanks will be on the top of the screen.

### Lily Pads
The screen will display three rows of two sets of buttons, each with a different word from the text: one the correct word and one another word from the verse. After the correct word is selected, the button will remain green. If the user selects the incorrect word, the button turns red and the user gets to choose the correct word. 

After the user finishes the third row, the next three will be shown. The number of rows will be the same as the number of words in the verse. The last three words of the verse on previous lily pads will be shown on the bottom of the screen. 

# Package Selection
## Version
The King James Version will be used for this version with select verses for six pre-made packages.
## Packages
### Test Package
This package is to test the capability of the app with various verse lengths and punctuation. 
* Esther 8:9
* Psalm 117:1-2
* Luke 12:53
* John 11:35.
### Passages from Psalms
* Psalm 23:1-6
* Psalm 46:1
* Psalm 117:1-2
* Psalm 139:1-6.
### Wisdom from Proverbs
* Proverbs 1:7
* Proverbs 3:5-6
* Proverbs 4:23 
* Proverbs 22:6 
* Proverbs 31:10
### The Romans Road
* Romans 1:20-21
* Romans 3:23
* Romans 5:8
* Romans 6:23
* Romans 10:9-10
* Romans 10:13
* Romans 11:36
### Psalm 23
### Encouragement
* Joshua1:9
* Psalm 46:1
* Isaiah 40:31
* Isaiah 41:10
* Romans 8:38-39
* Philippians 4:6
* Philippians 4:12-13
* 2 Timothy 1:7

# Future Goals
## Backend Server
### Multiple Users
Multiple users will be allowed for any device.
### Recommended Activity
The app will be able to take a users data and recommend an activity to help their memorization based on their last performance.
### Memorization Research
Extended research will be available on which activities and intervals are most efficient for memorization.

## Complete Texts
### Bible
Various complete versions of the Bible will be available, as well as different languages and a speech-to-text option.
### Other Texts
Other books and documents will be available for memorization, such as the "Declaration of Independence." 

## Gamification
### Badges
#### Streaks
There will be a badge given for streaks of one week, one month, three months, six months, one year, then every half year.
#### Verses 
There will be a badge for one verse, five verses, 10 verses, 25 verses, 50 verses, 75 verses, and 100 verses. After this, there will be badges for every 50 verses.
#### Chapters
There will be a badge for one chapter, five chapters, and ten chapters, 15 chapters, and 25 chapters. After this, there will be badges for every 25 chapters
#### Packages
There will be a badge for one package, five packages, 10 packages, 15 packages, and 25 packages. After this, there will be badges for every 25 packages.
#### Books
There will be a badge for every book memorized. There will also be a badge for one book, five books, and every five books after that.
### Leaderboard
A global leaderboard will be in place, showing the number of badges achieved.
### Experience 
There will be five different levels of experience: Beginner, Intermediate, Skilled, Advanced and Expert. Beginners will be awarded after they have five badges. Intermediate users will have 10 badges memorized, skilled 15, advanced 20, and expert 25.

## User Settings
### User Profile
The user will be able to create a username and password, as well as customizing their profile, such as Bible version and language.
### Difficulty Level
The user will be able to select their difficulty for the activities.
### Custom and Group Packages
The user will be able to connect with others and share custom made packages.