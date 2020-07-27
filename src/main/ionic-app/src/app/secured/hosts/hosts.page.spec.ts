import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { HostsPage } from './hosts.page';

describe('HostsPage', () => {
  let component: HostsPage;
  let fixture: ComponentFixture<HostsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HostsPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(HostsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
