// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

import { url } from 'inspector';

export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080',
  stripe:
    'pk_test_51P9trUP5ITi9Xqx4Hv8qs4rUvBJJ4x7TxZMw9yRBYOVHDJgk8OF7r2bGLPhAWY1jkWKf1nPWEnsxdLgUJcBEkKNS00mMwokXfo',
  googleClientId:
    '659439241514-h8n75fq8ospqergqnuf67na0b27fec5k.apps.googleusercontent.com',
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
