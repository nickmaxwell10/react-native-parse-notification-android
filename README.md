# react-native-parse-notification-android
A wraper around parse to manage android devices

## Prerequisite
1. Create an account on https://www.parse.com/
2. Create an app with your account

## Install
see the [Installation doc](./INSTALL.md)

## Methods

### Authenticate
```javascript
  // Authenticate this device as belonging to your application (add it to your Installation Class)
  authenticate((err) => {
    // if no error appears error will be null
  });
```

### SubscribeToChannel
```javascript
  // Add channel to channels field for the current device (in Installation Class)
  subscribeToChannel(channel, (err) => {
    // if no error appears error will be null
  });
```  

### UnsubscribeToChannel
```javascript
  // Remove channel to channels field for the current device (in Installation Class)
  unsubscribeToChannel(channel, (err) => {
    // if no error appears error will be null
  });
```  

### GetId
```javascript
  // Get the ObjectId field for the current device (in Installation Class)
  getId((id) => {
    // if id not found or error appears id will be null
  });
```

### GetString
```javascript
  // Get value from field for the current device (in Installation Class) and get
  // it as a String
  getString(field, (err, value) => {
    // if no error appears error will be null
    // if value not found or error appears value will be null or undefined
  });
```