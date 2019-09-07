[![](https://jitpack.io/v/F4pl0/FARLA.svg)](https://jitpack.io/#F4pl0/FARLA)
# FARLA - F4pl0's Awesome Request Library for Android

## Features
* Lightweight
> *So it can fit in every project you want*
* Native
> *Supports Java and Kotlin*
* Performance
> *Only bottleneck is your connection and server location*
* Simple
> *It just couldn't be simpler*
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
        implementation 'com.github.F4pl0:FARLA:0.4.0'
  }
```

## Usage

### GET Request
```java
new FarlaGetRequest(this)
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

### POST Request
```java
new FarlaPostRequest(this)
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

### PUT Request
```java
new FarlaPutRequest(this)
                .setURL("https://example.com/put")
                .setListener(new FarlaPutRequest.onPutRequestListener() {
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

### DELETE Request
```java
new FarlaDeleteRequest(this)
                .setURL("https://example.com/delete")
                .setListener(new FarlaDeleteRequest.onDeleteRequestListener() {
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
