import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ListHostsPage } from './list-hosts.page';

describe('ListHostsPage', () => {
  let component: ListHostsPage;
  let fixture: ComponentFixture<ListHostsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListHostsPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(ListHostsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
