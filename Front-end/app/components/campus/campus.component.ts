import { Component, OnInit } from '@angular/core';
import { Global } from '../../Services/global';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../Services/ProjectServices';


@Component({
  selector: 'app-campus',
  templateUrl: './campus.component.html',
  styleUrls: ['./campus.component.css'], 
  providers: [ProjectService]
})
export class CampusComponent implements OnInit {

  public campusId: number;
  public campus:String;
  public clases: any[];
  public mostrarAulas: boolean;
  public nombreEdificios: any[]
  

  constructor(
    private _projectService: ProjectService,
    private _route: ActivatedRoute
  ) {
    this.clases=[];
    this.mostrarAulas=false;
  }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
      this.getCampus(params.id);
      this.getClases(params.id);
    });
  }

  getCampus(id){
    //this.campus=Global.campus[id];
    this._projectService.getCampus(id).subscribe(
      response => {
        this.campus=response.nombre;
      },
      error => {
        console.log(<any>error);
      }
    );
    this.campusId=id;
    this.mostrarAulas=false;
  }

  getClases(id){
    this._projectService.getClases(id).subscribe(
      result => {
        this.clases=this._projectService.dameClases(result);
      },
      error => {
        console.log(<any>error);
      }
    );
  }

  getMostrarAula(cond){
    this.mostrarAulas=cond;
    console.log("getMostrarAula() -> "+this.clases);
    let claseCompleta: any;
    let claseCompletaLista: any;
    this.clases.forEach(element => {
      console.log("-");
      console.log(element);

    });
    if(cond)
      this.getClases;
  }

}
