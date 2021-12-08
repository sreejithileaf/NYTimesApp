# NYTimes App

A simple app to hit the NY Times Most Popular Articles API and show a list of articles,
The key features includes,

* Displays a list of most viewed articles
* Shows details when any of the list item is tapped. 
* Navigation drawer for easy navigation 
* The app uses the most viewed section of below API
  http://api.nytimes.com/svc/mostpopular/v2/viewed/{section}/{period}.json?api-key='sample-key'

### Architecture:
Developed using Kotlin programming language. The project follows MVVM design pattern and used Material UI and jetpack components


### Libraries Used

- [Material Design support libraries](https://material.io/develop/android/docs/getting-started) - A design system used build high-quality digital experiences for Android
- [Retrofit](https://square.github.io/retrofit/) - A Networking library provides a powerful framework for authenticating and interacting with APIs and sending network requests
- [Jetpack](https://developer.android.com/jetpack) - Jetpack is a suite of libraries to help developers follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices.
- [Picasso](https://square.github.io/picasso/) - A library which provides hassle-free image loading and caching
- [Koin](https://insert-koin.io/) - An efficient and lightweight dependency injection framework for kotlin
- [Coroutines](https://developer.android.com/kotlin/coroutines) - A tool for symplifying asychronous task which is light-weight and works without flaws with Jetpack.



## Installation

* Clone this project from the repository and then import the project in Android studio. File->new->import project
* Sync project with gradle files and then build the project.
* After successful build run the app by clicking on the 'run' button on top bar, or by pressing ctrl+R buttons.


### Running The Tests 

Follow the steps to get test case reports:
* Move to test packages in Android Studio (Java test packages)
* Select the package by right clicking, select more run option then select 'run Tests in package with coverage'
* Test results will be shown after the executions are finished.

# MIT License

Copyright 2021

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.