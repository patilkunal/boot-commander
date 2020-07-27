```
ionic generate component public/components/start
ionic generate module public
ionic generate page public/login
ionic generate guard guards/secured
```

To run ionic application locally
`ionic serve`


### Add android platform to your project
`ionic capacitor add andriod`


### Add IOS platform to your project
`ionic capacitor add ios`
---
### Build project for prod and run it before build android/ios project
`ionic build --prod`


Then run following to copy the build to android/ios project and open in android studio or XCode
```
ionic capacitor copy andriod
ionic capacitor open andriod
```