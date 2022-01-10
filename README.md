Android Study Jams
# Library-Manager
## **Problem Statement**

Librarians or some monitors face difficulties while keeping track of books that are present in the library. Sometimes during electricity issues the computer software does not work resulting in delaying the process of updating or issuing of books to students. This is common in rural areas. The advent of android applications has meant these issues can be solved very easily. This is where the Library Manager app plays its part as everyone today has a smartphone and this app can be accessed very easily.

## **Solution**

Library Manager is an android application that can be used to store data regarding books in the library.
It helps us to update name, price and quantity of the books present in the library.
It also has the option to update the quantity if the book is issued to someone.
Live updated data can be visible in android studio using the roomdatabase.

## **About App**
The app has 3 fragments.
The main fragment has the list of books followed by their price and quantity.
The next fragment has the option to update the information regarding the books.
The last fragment can be used to update the quantity if the book is being issued to someone.
## **Screenshots**

![Main Screen](https://user-images.githubusercontent.com/88738245/148697919-0d9fac15-d30b-4144-b8f8-81f9f305f012.jpg)
![Edit Book Screen](https://user-images.githubusercontent.com/88738245/148697940-dc5c5d3a-bebb-41ac-9f40-80747bfb0d9a.jpg)
![Book Detail Screen](https://user-images.githubusercontent.com/88738245/148697952-bef942a1-c40b-4290-a9ee-d05be34babc3.jpg)


## **Functionality & Concepts used**

The app was made using layouts, fragments, livedata, viewmodels, navigation and roomdatabase.
The App has a very simple and interactive interface which helps the librariand/monitors enter the data of books.
1. Constraint Layout : Most of the activities in the app uses a flexible constraint layout, which is easy to handle for different screen sizes.
2. Fragments: The use of fragments has ensured that the app follows latest components of android. Also the backtracking and navigation of the app has been possible due to fragments.
3. Navigation: Use of navigation has made changing screens very smooth and error free.
4. LiveData & Room Database : The list of updated books can be visible in android studio which helps the data safe and secure.

## **Architecture**

![structure](https://user-images.githubusercontent.com/88738245/148697997-e1a1c511-3a0f-4000-9a65-7b1990be3032.jpg)

Application link - https://github.com/PushkarAdhyapak/Library-Manager/tree/master

## **Future Scope** 
We plan to add login and register feature in the app so different colleges can use it respective to their needs.
We also plan to add the issue date and sumbission date feature in the app.












