[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![](https://jitpack.io/v/F4pl0/FARLA.svg)](https://jitpack.io/#F4pl0/FARLA)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-FARLA-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/7862)
# FARLA - F4pl0's Awesome Request Library for Android

## Features
* Lightweight
> *So it can fit in every project you want, compressed to <50KB*
* Native
> *Supports Java and Kotlin*
* Performance
> *Only bottleneck is your connection and server location*
* Simple
> *Implements in seconds, period*
* GET, POST, PUT and DELETE Requests

## Installation

Add Jitpack.io repository to your build.gradle file:
```javascript
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add FARLA to your dependencies

```javascript
dependencies {
        implementation 'com.github.F4pl0:FARLA:0.5.0'
  }
```
Add `USES-INTERNET` permission to your Android Manifest:
```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
	  ...
	  >
	
    	<uses-permission android:name="android.permission.INTERNET" />
	
	...
	
</manifest>
```

## Usage

### GET Request (Same for DELETE Request)
```java
new FarlaGetRequest(this)              // For DELETE Request change to FarlaDeleteRequest
                .setURL("https://example.com/get")
                .setListener(new FarlaGetRequest.onGetRequestListener() {
                    @Override
                    public void onSuccess(String response) {
                        //Handle the response
                    }

                    @Override
                    public void onFailure(int error) {
                        //Handle the failure
                    }
                }).execute();
```

### POST Request (Same for PUT Request)
```java
new FarlaPostRequest(this)             // For PUT Request change to FarlaPutRequest
                .setURL("https://example.com/post")
                .setListener(new FarlaPostRequest.onPostRequestListener() {
                    @Override
                    public void onSuccess(String response) {
                        //Handle the response
                    }

                    @Override
                    public void onFailure(int error) {
                        //Handle the failure
                    }
                })
                .addParam("key", "value")
                .execute();
```
### Error Handling
```java
@Override
	public void onFailure(int error) {
		switch(error){
			case Constants.NO_CONNECTION:
				// Insert code for handling NO_CONNECTION
				// Device has no connection to internet
				break;
			
			case Constants.AUTH_FAILURE:
				// Insert code for handling AUTH_FAILURE
				// Server returned 401, request is not authenticated
				// Mabye forgot authentification header?
				break;
				
			case Constants.SERVER_ERROR:
				// Insert code for handling SERVER_ERROR
				// Something happened with the server
				// - Check the server-sided code
				// - Check the input provided to the server via headers or params
				break;
			
			case Constants.NETWORK_ERROR:
				// Insert code for handling NETWORK_ERROR
				// Request got lost somewhere in the dark woods of the internet
				// Mabye network change?
				break;
			
			case Constants.PARSE_ERROR:
				// Insert code for handling PARSE_ERROR
				// - Check URL provided
				// - Check Headers provided
				// - Check params provided
				break;
		}
	}
```

### Headers
You can add headers with `.addHeader(String, String)` call:
```java
new FarlaPostRequest(this)             // Example
                .setURL("https://example.com/post")
                .addHeader("Content-Type", "multipart/form-data")
                .execute();
```

## NOTE
**From Android 9.0+ Google disallows HTTP traffic, only allows secure HTTPS**  
**You will get NO_CONNECTION error on HTTP requests**  
To allow HTTP Traffic to some servers, you must put them in a *network_security_config.xml* file:
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">f4pl0.github.io</domain>
    </domain-config>
</network-security-config>
```
And then include it in the Android Manifest:
```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest ... >
    <application android:networkSecurityConfig="@xml/network_security_config"
                    ... >
        ...
    </application>
</manifest>
```
And you should be allowed to make HTTP requests to the server listed.
