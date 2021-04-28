import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../Services/ProjectServices';
import { Global } from '../../Services/global';

@Component({
  selector: 'app-pintar-clase',
  templateUrl: './pintar-clase.component.html',
  styleUrls: ['./pintar-clase.component.css'],
  providers: [ProjectService]
})
export class PintarClaseComponent implements OnInit {

  public campus: string;
  public clase: string;
  public distribucion: any[];
  public rotos: any[];
  public rotosFila:any[];
  
  constructor(
    private _route: ActivatedRoute,
    private _projectService: ProjectService
  ) {
    this.rotosFila=[];
  }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
    this.campus=Global.campus[params.campus];
    this.clase=params.clase;
    this.distribucion=this.dameAula(this.campus, this.clase);
    console.log(typeof this.distribucion);
    this.rotos=this._projectService.getRotos(this.campus, this.clase);
    
    /* PRUEBA DE QUE LEE BIEN LAS COLUMNAS */
    /*
    console.log("Fila 9");
    console.log(this.getPintar(10,9));
    console.log("Fila 10");
    console.log(this.getPintar(10,10));
    */
    });
  }

  dameAula(campus,aula){
    return this._projectService.pintarAula(campus,aula);
  }

  getPintar(nFilas, i):any[]{
    this.rotosFila=this.getRotos(i,[]);
    if(this.rotosFila.length == 0)
      return this.getArray(nFilas, false, []);
    else
      return this.getArray(nFilas, true, []);
  }

  getArray(nFilas, rotos, array):any[]{
    if(!rotos)
      for (let index = 1; index <= nFilas; index++) {
        array.push(1);
      }
    else
      for (let index = 1; index <= nFilas; index++) 
        if(this.rotosFila.includes(index))
          array.push(2);
        else
          array.push(1);
          
    console.log("Fila");
    console.log(array);
    return array;
  }

  getRotos(i,rotosFila):any[]{
    console.log("BIEN-ROTOS");
    this.rotos.forEach(element => {
      if(element[0]==i)
        rotosFila.push(element[1]);
    });
    return rotosFila;
  }

}
