import { inject } from '@angular/core';
import { CanActivateFn, Router, ActivatedRouteSnapshot } from '@angular/router';
import { map, catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { CartService } from 'src/app/services/cart/cart.service';

export const onlyCourseOwnerGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state) => {
  const cartService = inject(CartService);
  const router = inject(Router);
  
  const courseId = route.paramMap.get('id'); 

  if (!courseId) {
    router.navigate(['/not-found']);
    return of(false);
  }

  return cartService.hasBoughtCourse(parseInt(courseId)).pipe(
    map((hasBought: boolean) => {
      if (!hasBought) {
        router.navigate(['/not-authorized']);
      }
      return hasBought;
    }),
    catchError((error) => {
      if (error.status === 403) {
        router.navigate(['/not-authorized']);
      } else {
        router.navigate(['/error']); // You can navigate to a generic error page if needed.
      }
      return of(false);
    })
  );
};