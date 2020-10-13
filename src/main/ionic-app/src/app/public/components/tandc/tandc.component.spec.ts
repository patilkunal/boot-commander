import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { TandcComponent } from './tandc.component';

describe('TandcComponent', () => {
  let component: TandcComponent;
  let fixture: ComponentFixture<TandcComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TandcComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(TandcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
