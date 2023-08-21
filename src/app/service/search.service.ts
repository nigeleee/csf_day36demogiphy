import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Search } from '../search';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private apiUrl : string ="http://localhost:8080/api/search";

  constructor(private http: HttpClient) { }

  getGif(q: string) : Observable<Search> {
    const params = new HttpParams()
    .set('q', q);
    const headers = new HttpHeaders()
    .set('content-type', 'application/json')

    return this.http.get<Search>(this.apiUrl, {params: params, headers:headers});
  }
}
