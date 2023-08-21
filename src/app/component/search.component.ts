import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { SearchService } from '../service/search.service';
import { Subscription } from 'rxjs';
import { Search } from '../search';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit{

  form! : FormGroup;
  input! : string;
  sub$!: Subscription;
  url!: Search[];

  constructor(private fb: FormBuilder, private searchSvc: SearchService) {}

ngOnInit(): void {
  this.form = this.initialiseForm();
}

initialiseForm() : FormGroup {
  return this.fb.group({
    input: new FormControl('')
  });
}

search() {
  // console.log(this.form.value.input)
  this.sub$ = this.searchSvc.getGif(this.form.value.input).subscribe((data : any) => {
    console.log(data);
    this.url = data;
  })
}

}
