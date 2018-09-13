# Android Camera
This application using Image Cropping Library for Android, optimized for Camera and Gallery.

### Screenshots
<pre>
<img src="Screenshot/Screenshot_20180913-154741_Android Camera.jpg" width="250" height="444">         <img src="Screenshot/Screenshot_20180913-154814_Android System.jpg" width="250" height="444">         <img src="Screenshot/Screenshot_20180913-154844_Android Camera.jpg" width="250" height="444">         <img src="Screenshot/Screenshot_20180913-154851_Android Camera.jpg" width="250" height="444">
</pre>

### Petunjuk menjalankan source code aplikasi
Untuk menjalankan source code aplikasi ini, memasukkan repositories ***maven {}*** dan ***mavenCentral()*** ke dalam file ***build.gradle*** project

```
allprojects {
    repositories {
        mavenCentral()
        .....
        .....
        maven { url 'https://jitpack.io' }
    }
}
```

Import library pada file ***build.gradle*** Module: app didalam **dependencies** 

```
dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    ....
    //add liblary
    implementation 'com.jakewharton:butterknife:8.8.1'

    implementation 'com.theartofdev.edmodo:android-image-cropper:2.7.+'
    implementation 'com.github.jkwiecien:EasyImage:1.3.1'
    implementation 'com.github.bumptech.glide:glide:4.7.1'

    annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.7.1'
}
```
Kemudian tambahkan permission pada file ***AndroidManifest.xml***

```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```

Terakhir, tambahkan activity ***CropImageActivity*** pada file ***AndroidManifest.xml*** didalam tag **application**

```
<application
       ......
        android:theme="@style/AppTheme">
        ......
        <activity android:name="com.theartofdev.edmodo.cropper.CropImageActivity"
            android:theme="@style/Base.Theme.AppCompat"/>
            
    </application>
```

## Author

* **R Rifa Fauzi Komara**

Jangan lupa untuk follow dan ★

