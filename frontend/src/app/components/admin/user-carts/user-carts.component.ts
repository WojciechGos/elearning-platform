import { Component, OnInit, ViewChild  } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';
import { DatePipe } from '@angular/common';

export interface Cart {
  id: string;
  status: string;
  createdOn: string;
}

@Component({
  selector: 'app-user-carts',
  templateUrl: './user-carts.component.html',
  styleUrls: ['./user-carts.component.css'],
  providers: [DatePipe]
})
export class UserCartsComponent implements OnInit {
  displayedColumns: string[] = ['id',  'createdOn', 'status'];
  dataSource!: MatTableDataSource<Cart>;
  userEmail: string = '';

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private route: ActivatedRoute,
    private cartService: CartService,
    private datePipe: DatePipe 
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.userEmail = params['email'];
      this.loadCarts();
    });
  }

  loadCarts(): void {
    this.cartService.getAllCartsByUser().subscribe((carts: Cart[]) => {
      carts.forEach((cart: Cart) => { 
        cart.createdOn = this.datePipe.transform(
          cart.createdOn,
          'yyyy-MM-dd HH:mm:ss'
        )!;
      });
      this.dataSource = new MatTableDataSource(carts);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      console.log(carts);
    });
  }
  

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
