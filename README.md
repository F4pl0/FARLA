[![](https://jitpack.io/v/F4pl0/FARLA.svg)](https://jitpack.io/#F4pl0/FARLA)
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
