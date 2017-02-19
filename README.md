[![](https://jitpack.io/v/matecode/Snacky.svg)](https://jitpack.io/#matecode/Snacky)

# Snacky

Snacky is a small library to help you adding a [Snackbar](https://developer.android.com/reference/android/support/design/widget/Snackbar.html) to your layout with ease. It was created because of my own needs and is inspired by [Toasty](https://github.com/GrenderG/Toasty). Snacky uses an easy builder pattern to build a Snackbar and gives you some template designs like ERROR, WARNING, INFO and SUCCESS as well as some customization options. See code samples below

## Usage

Using Snacky is really simple. 

#### Initialisation

Start with `Snacky.builder()` to get a Builder. 

It is necessary to set an activity or View for the snackbar.

`.setView(View view)` will use the view for the snackbar to show, or you choose

`.setActivity(Activity activity)` which will automatically find the root view of the activity

#### Customization

most of them are self-explaining

`.setBackgroundColor()`

`.setText()` Charset or IntRes for text

`.setTextColor()` Color int of text

`.setTextSize()` 

`.setMaxLines()`

`.centerText()` centers the text

`.setActionText()`

`.setActionTextColor()`

`.setActionClickListener(View.OnClickListener)`

`.setDuration(Snacky.DURATION)`

`.setIcon()` Drawable to be shown, in my opinion ist best to use small drawables with 24dp size

#### Building

`.build()` gives you the Snackbar, but you can also use some predefined templates:

`.success()`

`.error()`

`.info()`

`.warning()`

All give you a snackbar object, but some set predefined values if you didn't customize them before.

#### Using the snackbar

After that you can handle the snackbar as you know it:

`.addCallback(SnackBar.Callback)` adds a callback to the snackbar

`.show()` shows the snackbar


## Installation

Snacky is published via Jitpack. Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Add this to your module's `build.gradle` file (make sure the version matches the JitPack badge above):

```gradle
dependencies {
    ...
    compile 'com.github.matecode:Snacky:1.0.1'
}
```

## Licence

Copyright 2017 Mate Siede

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.