import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';

import { NgSwitcheryModule } from "angular-switchery-ios";

import { Routes, RouterModule } from "@angular/router";

import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { HeaderComponent } from './header/header.component';
import { LandingComponent } from './landing/landing.component';
import { AuthComponent } from './auth/auth.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { ResetPasswordComponent } from './auth/reset-password/reset-password.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MyGroupsComponent } from './my-groups/my-groups.component';
import { SingleGroupComponent } from './my-groups/single-group/single-group.component';
import { CreateGroupComponent } from './my-groups/create-group/create-group.component';
import { MyAccountComponent } from './my-account/my-account.component';
import { InvitesComponent } from './my-account/invites/invites.component';
import { MyDatasetsComponent } from './my-datasets/my-datasets.component';
import { SingleDatasetComponent } from './my-datasets/single-dataset/single-dataset.component';
import { UploadDatasetComponent } from './my-datasets/upload-dataset/upload-dataset.component';
import { ErrorComponent } from './error/error.component';
import { ErrorRouteComponent } from './error/error-route/error-route.component';
import { GroupMembersComponent } from './my-groups/single-group/group-members/group-members.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { UserModelComponent } from "./models/user/user.model";
import { DatasetService } from "./services/dataset/dataset.service";
import { UploadComponent } from './my-datasets/upload-dataset/upload/upload.component';
import { GraphComponent } from './my-datasets/upload-dataset/graph/graph.component';
import { GraphSelectionComponent } from './my-datasets/upload-dataset/graph-selection/graph-selection.component';
import { TablePreviewComponent } from './my-datasets/upload-dataset/table-preview/table-preview.component';
import { Protected } from './auth/gaurd/protected';
import { Authentication } from './auth/gaurd/authentication';
import { ChartComponent } from './my-datasets/single-dataset/chart/chart.component';
import { AdminComponent } from './admin/admin.component';
import { from } from 'rxjs';
import { AddMembersComponent } from './my-groups/single-group/add-members/add-members.component';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

const routes: Routes = [
  // Landing route
  { path: "", component: LandingComponent },

  // Authetication routes
  { path: "auth/login", component: LoginComponent, canActivate: [Authentication] },
  { path: "auth/register", component: RegisterComponent, canActivate: [Authentication] },
  { path: "auth/reset", component: ResetPasswordComponent, canActivate: [Authentication] },

  // Dashboard route
  { path: "dashboard", component: DashboardComponent, data: { animation: 'Dashboard' }, canActivate: [Protected] },

  // Group routes
  { path: "groups", component: MyGroupsComponent, data: { animation: 'Groups' }, canActivate: [Protected] },
  { path: "groups/single/:id", component: SingleGroupComponent, data: { animation: 'GroupsSingle' }, canActivate: [Protected] },
  { path: "groups/create", component: CreateGroupComponent, canActivate: [Protected] },

  // Account routes
  { path: "account", component: MyAccountComponent, data: { animation: 'Account' }, canActivate: [Protected] },
  { path: "invites", component: InvitesComponent, canActivate: [Protected] },

  // Dataset routes
  { path: "datasets", component: MyDatasetsComponent, data: { animation: 'Datasets' }, canActivate: [Protected] },
  { path: "datasets/single/:id", component: SingleDatasetComponent, data: { animation: 'DatasetsSingle' }, canActivate: [Protected] },
  { path: "datasets/create", component: UploadDatasetComponent, canActivate: [Protected] },
  { path: "datasets/create/upload", component: UploadComponent, data: { animation: 'Upload' }, canActivate: [Protected] },
  { path: "datasets/create/graph", component: GraphComponent, canActivate: [Protected] },
  { path: "datasets/create/graphs", component: GraphSelectionComponent, data: { animation: 'Graphs' }, canActivate: [Protected] },
  { path: "datasets/create/table", component: TablePreviewComponent, data: { animation: 'Table' }, canActivate: [Protected] },

  //admin 
  { path: "admin", component: AdminComponent, data: { animation: 'Admin' }, canActivate: [Protected] },

  // Error route
  { path: "**", component: ErrorRouteComponent }
];

@NgModule({
  declarations: [
    // Navigation components
    NavComponent,
    HeaderComponent,

    // Container
    AppComponent,

    // Landing page
    LandingComponent,

    // Authentication components
    AuthComponent,
    LoginComponent,
    RegisterComponent,
    ResetPasswordComponent,

    // Dashboard
    DashboardComponent,

    // Group components
    MyGroupsComponent,
    SingleGroupComponent,
    CreateGroupComponent,

    // Account components
    MyAccountComponent,
    InvitesComponent,

    // Dataset components
    MyDatasetsComponent,
    SingleDatasetComponent,
    UploadDatasetComponent,

    // Error components
    ErrorComponent,
    ErrorRouteComponent,
    GroupMembersComponent,
    UploadComponent,
    GraphComponent,
    GraphSelectionComponent,
    TablePreviewComponent,
    AdminComponent,
    ChartComponent,
    AddMembersComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule.forRoot(),
    BrowserModule,
    NgSwitcheryModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  
  providers: [
    DatasetService,
    Protected,
    Authentication,
    { provide: String, useValue: "'dummy" },
    { provide: Number, useValue: "0" }
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
