# ReminderDemo

### Installation

### To get a Git project into your build:

* Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```Java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
  
* Step 2. Add the dependency
```Java
dependencies {
	        implementation 'com.github.mohanbright:ReminderDemo:v1.0'
	}
```

### Setup
1. Add the following dependency in your app level build.gradle file :
 ```Java
buildFeatures{
        dataBinding=true
    }
    compileOptions {
        sourceCompatibility = '1.8'
        targetCompatibility = '1.8'
    }
    
    //Constraint layout
    implementation 'androidx.constraintlayout:constraintlayout:version'

    //AndroidX lifecycle
    implementation "androidx.lifecycle:lifecycle-extensions:vaersion"
    implementation "androidx.lifecycle:lifecycle-common-java8:version"

    //timber
    implementation 'com.jakewharton.timber:timber:version'
    
```

###### add the receiver in your manifeast file
```xml
<receiver android:name="app.dev.reminder.receiver.ScheduleEventReceiver">
            <intent-filter>
                <action android:name="app.dev.reminder.SCHEDULED_EVENT"/>
            </intent-filter>
        </receiver>
```
