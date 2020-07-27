import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Host } from 'src/app/models/host';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/models/category';
import { HostService } from 'src/app/services/host-service';

@Component({
  selector: 'app-edit-hosts',
  templateUrl: './edit-hosts.page.html',
  styleUrls: ['./edit-hosts.page.scss'],
})
export class EditHostsPage implements OnInit {

  host: Host;
  categories: Category[];
  hostForm: FormGroup;
  isSubmitted = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder,
    private hostService: HostService
  ) { }

  ngOnInit() {
    this.host = this.activatedRoute.snapshot.data['host'];
    this.categories = this.activatedRoute.snapshot.data['categories'];
    if(this.host.id == -1) {
      this.host.category = this.categories[0];
      this.host.secureHttp = false;
    }
    this.hostForm = this.formBuilder.group({
      id: [this.host.id, []],
      name: [this.host.name, [Validators.required]],
      hostName: [this.host.hostName, [Validators.required, Validators.minLength(6)]],
      port: [this.host.port, [Validators.min(80), Validators.pattern('^[0-9]+$')]],
      category: [this.host.category, Validators.required],
      secureHttp: [this.host.secureHttp, []]
    });
  }

  get errorControl() {
    return this.hostForm.controls;
  }

  submitAction() {
    this.isSubmitted = true;
    if (!this.hostForm.valid) {
      console.log('Please provide all the required values!')
      return false;
    } else {
      this.hostService.save(this.hostForm.value).subscribe(() => {
         this.router.navigate(['/secured/hosts/list']);
      });
    }    
  }

  compareCategory(c1: Category, c2: Category): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

  list() {
    this.router.navigate(['/secured/hosts/list']);
  }

}
