import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { EditHostsPage } from './edit-hosts.page';

describe('EditHostsPage', () => {
  let component: EditHostsPage;
  let fixture: ComponentFixture<EditHostsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditHostsPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(EditHostsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
