import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustViewRecProdComponent } from './custviewrecprod.component';

describe('CustviewrecprodComponent', () => {
  let component: CustViewRecProdComponent;
  let fixture: ComponentFixture<CustViewRecProdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustViewRecProdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustViewRecProdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
