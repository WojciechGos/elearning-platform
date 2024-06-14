import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { map } from 'rxjs';
import { PermissionService } from 'src/app/services/permission/permission.service';

export const onlyAdminGuard: CanActivateFn = (route, state) => {
  const permissionService = inject(PermissionService);
  const router = inject(Router);

  return permissionService.checkUserPermission('ROLE_ADMIN').pipe(
    map((hasAdminRole: boolean) => {
      if (!hasAdminRole) {

        router.navigate(['/not-authorized']);
      }
      return hasAdminRole;
    }));
};
