package com.notificationandroid;

import android.app.Activity;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseException;
import com.parse.SaveCallback;

import android.util.Log;

public class NotificationAndroidModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext mReactContext;
  private final Activity mMainActivity;

  private Callback mCallback;

  public NotificationAndroidModule(ReactApplicationContext reactContext, Activity mainActivity) {
    super(reactContext);
    mReactContext = reactContext;
    mMainActivity = mainActivity;
  }

  @Override
  public String getName() {
    return "NotificationAndroidManager";
  }
  
  /**
   * Authenticate this client as belonging to your application, should be done 
   * before everything
   * 
   * @param callback
   */
  @ReactMethod
  public void authenticate(Callback errorCallback) {
    try {
      Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
      Parse.initialize(mMainActivity,"GLiHpbYIdOyKigbNeWrzpOSvrZZYwPfBfXpZrVcN", "Jyeeo69eykzLjMTk0iAZlmJ4Qi0k48zN0WMyyCKZ" );
    } catch(Exception ex) {
      Log.e("ReactNative", "ERROR AUTHENTICATING");
    }

    try {
      Log.e("ReactNative", "INITTING INSTALL");
      final ParseInstallation parseInstallation = ParseInstallation.getCurrentInstallation();
      parseInstallation.saveInBackground(new SaveCallback() {
       public void done(ParseException ex) {
         if (ex == null) {
          //pass
         } else {
          Log.e("ReactNative", "ERROR SAVING INSTALL");
         }
       }
     });
    } catch(Exception ex) {
      Log.e("ReactNative", "ERROR SAVING INSTALL");
    }
  }
  
  /**
   * Adds 'channel' to the 'channels' list in the current ParseInstallation
   * and saves it in a background thread.
   * 
   * @param channel         the channel you whant your user to suscribe
   * @param callback
   */
  @ReactMethod
  public void subscribeToChannel(final String channel, Callback errorCallback) {
    try {
      Log.e("ReactNative", "SUBSCRIBING TO : " + channel);
      ParsePush.subscribeInBackground(channel);
    } catch(Exception ex) {
      Log.e("ReactNative", ex.getMessage());
    }
  }

  /**
   * Removes 'channel' to the 'channels' list in the current ParseInstallation
   * and saves it in a background thread.
   * 
   * @param channel         the channel you whant your user to unsuscribe
   * @param callback
   */
  @ReactMethod
  public void unsubscribeToChannel(final String channel, final Callback callback) {
      ParsePush.unsubscribeInBackground(channel, new SaveCallback() {
        @Override
        public void done(ParseException e) {
            if (e == null) {
                callback.invoke();
            } else {
                callback.invoke(e.getMessage());
            }
        }
    });
  }

  @ReactMethod
  public void getParseInstallationObjectId(final Callback callback) {
    callback.invoke((String) ParseInstallation.getCurrentInstallation().getObjectId());
  }
  
  /**
   * This retrieves the current value of field as a string
   * 
   * @param field         field to retrieve
   * @param callback
   */
  @ReactMethod
  public void getString(final String field, final Callback callback) {
    callback.invoke(null, (String) ParseInstallation.getCurrentInstallation().get(field));
  }
}
