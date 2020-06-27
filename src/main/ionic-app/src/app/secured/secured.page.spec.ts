import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { SecuredPage } from './secured.page';

describe('SecuredPage', () => {
  let component: SecuredPage;
  let fixture: ComponentFixture<SecuredPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SecuredPage ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(SecuredPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
