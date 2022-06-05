import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpanViewComponent } from './span-view.component';

describe('SpanViewComponent', () => {
  let component: SpanViewComponent;
  let fixture: ComponentFixture<SpanViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SpanViewComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SpanViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
