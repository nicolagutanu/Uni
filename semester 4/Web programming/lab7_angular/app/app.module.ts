import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddPageComponent } from './add-page/add-page.component';
import { FilterPageComponent } from './filter-page/filter-page.component';
import { DeletePageComponent } from './delete-page/delete-page.component';
import { UpdatePageComponent } from './update-page/update-page.component';

@NgModule({
  declarations: [
    AppComponent,
    AddPageComponent,
    FilterPageComponent,
    DeletePageComponent,
    UpdatePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
