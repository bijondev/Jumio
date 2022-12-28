# Jumio
Android Jumio implementation

Jumio is a company that provides a range of identity verification and document scanning services, including an SDK for Android that allows developers to integrate these services into their apps. Here is an example of how to implement the Jumio Android SDK in an app:

    Add the Jumio SDK as a dependency in your app's build.gradle file:

implementation 'com.jumio.mobile.sdk:mobile-sdk-android:6.29.0'

    Add the Jumio API key and API secret to your app's AndroidManifest.xml file:

<meta-data
    android:name="com.jumio.api.key"
    android:value="YOUR_API_KEY"/>
<meta-data
    android:name="com.jumio.api.secret"
    android:value="YOUR_API_SECRET"/>

    In your activity, create an instance of the JumioMobileSDK object and configure it with the desired parameters:

JumioMobileSDK jumioMobileSDK = new JumioMobileSDK();

jumioMobileSDK.setCustomization("customization_name", "customization_version");
jumioMobileSDK.setRequireVerification(true);
jumioMobileSDK.setRequireIdentityVerification(true);
jumioMobileSDK.setRequireIdentityVerificationWithFaceMatch(true);
jumioMobileSDK.setPreselectedCountry("US");
jumioMobileSDK.setMerchantScanReference("scan_reference");
jumioMobileSDK.setCustomerId("customer_id");
jumioMobileSDK.setCallbackUrl("callback_url");

    Start the SDK by calling the initiate method:

jumioMobileSDK.initiate(this, REQUEST_CODE);

The Jumio SDK will then guide the user through the process of capturing and verifying their identity documents. When the process is complete, the results will be returned to the app through the onActivityResult method.

I hope this helps give you an idea of how to implement the Jumio Android SDK in your app. Let me know if you have any questions.
