import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

// console.log("developement mode")
enableProdMode(); 
if (environment.production) {
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
