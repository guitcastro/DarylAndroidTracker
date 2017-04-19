# DarylAndroidTracker [![codecov](https://codecov.io/gh/guitcastro/DarylAndroidTracker/branch/master/graph/badge.svg)](https://codecov.io/gh/guitcastro/DarylAndroidTracker)
Use multiple analytics and other trackers in your Application easily 

## Installing 

Just added the necessary SDKs to your gradle file:

```
def darylVersion = '0.0.40'

compile "com.appprova.daryl:daryl:$darylVersion"
compile "com.appprova.daryl:googleanalytics:$darylVersion"
compile "com.appprova.daryl:crashlytics:$darylVersion"
compile "com.appprova.daryl:firebase:$darylVersion"
compile "com.appprova.daryl:facebook:$darylVersion"
compile "com.appprova.daryl:dito:$darylVersion"
```

## Usages instructions

### Registering your trackers

#### Google Analytics

```
GoogleAnalytics googleAnalytics = GoogleAnalytics
                .getInstance(app.getApplicationContext());
Tracker googleTracker = googleAnalytics.newTracker(R.xml.app_tracker);
googleTracker.enableAdvertisingIdCollection(true);

TrackerAdapter googleAnalyticsTracker = new GoogleAnalyticsTracker(googleTracker);
```


#### Firebase

```
TrackerAdapter firebaseTracker = new FirebaseTracker(FirebaseAnalytics.getInstance(context.getApplicationContext()));
```

#### CrashLytics Log

```
TrackerAdapter crashlyticsLogTracker = new CrashlyticsLogTracker();
```

#### Facebook Log

```
TrackerAdapter facebookTracker = new FacebookTracker(AppEventsLogger.newLogger(context.getApplicationContext()));
```

### Registering multiple trackers

```
MultipleTrackerAdapter trackerAdapter = new MultipleTrackerAdapter();
trackerAdapter.addTracker(googleAnalyticsTracker);
        trackerAdapter.addTracker(ditoTracker);
        trackerAdapter.addTracker(firebaseTracker);
        trackerAdapter.addTracker(facebookTracker);

        if (!BuildConfig.DEBUG) {
            trackerAdapter.addTracker(crashlyticsLogTracker);
        }

```

### Starting Tracking 

After all trackers are registered you can just use the multiple tracker to track all events at once

#### Tracking event

```
trackAdaper.logEvent(new EventBuilder("event name")
                .setCategory("event category")
                .setAction("event action")
                .setProperty("custom property", "property value")         
                .get());
```

#### Tracking page view

```
trackAdaper.logPageView("page name");
```

#### Tracking exceptions

Avaibles for : Firebase, CrashlyticsLog and Google Analytics

```
trackAdaper.logException(new Exeption("somthing went wrong"));
```

#### Logging user properties

Avaibles for : Firebase, CrashlyticsLog

```
trackAdaper.setUserProperty(Constants.USER_PROPERTY_EMAIL, "example@gmail.com");
```

## Creating custom tracker

In oder to create a custom tracker you just need to implement the `TrackerAdapter` interface.

