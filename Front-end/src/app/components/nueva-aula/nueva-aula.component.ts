import { Component, OnInit } from '@angular/core';
import { Global } from '../../Services/global'
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../Services/ProjectServices';
import { HttpParams } from '@angular/common/http';
import { FormArray, FormControl, FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-nueva-aula',
  templateUrl: './nueva-aula.component.html',
  styleUrls: ['./nueva-aula.component.css'],  
  providers: [ProjectService]
})
export class NuevaAulaComponent implements OnInit {
  public miFormulario: FormGroup;

  public colum:boolean;
  public campus: string;
  public listEdificios:any;
  public listNombresEdificios:string[];
  public listPlantas:number[];
  public itemEdificio:string;
  public itemPlanta:number;
  public filas:number;
  public asientosInhabilitados:number;
  public array:string[];
  public rotos:string[];
  public id: string;
  public ordinales:string[];
  public campusId:string;
  public clase:any;

  constructor(
    private _projectService: ProjectService,
    private _route: ActivatedRoute,
    private fb: FormBuilder 
  ) {
    console.log("CONSTRUCTOR")
    this.listEdificios=[];
    this.listNombresEdificios=[];
    this.listPlantas=[];
    this.itemEdificio='';
    this.itemPlanta=1;
    this.array=[];
    this.rotos=[];
    this.colum=false;
    this.clase={};
    this.ordinales=['-','primera','segunda','tercera','cuarta','quinta','sexta','septima','octava','novena','decima',
                    'undecima','duodecima','decimotercera','decimocuarta','decimoquinta','decimosexta','decimoseptima',
                    'decimooctava','decimonovena','vigesima'];
   }
   
  ngOnInit(): void {
    this._route.params.subscribe(params => {
      this.campusId=params.campus;
      this.getCampus(params.campus);
    });
    this.miFormulario=this.fb.group({
      edificio: 'Aulario 1',
      planta: '1',
      filas: [''],
      columnas: [''],
      listaFilas: this.fb.array([this.fb.group({fila: ['']})]),
      rotos: [''],
      listaRotos: this.fb.array([this.fb.group({fila: [''],
                                                columna: [''], 
                                                posicion: ['']})])
    });
  }

  rellenarFilas(lista, limiteArray):number[]{
    var nuevaLista:number[] = []
    
    lista.forEach(element => {
      nuevaLista.push(element.fila);  
    });

    if(nuevaLista.length < limiteArray)
      for(var i=nuevaLista.length; i<limiteArray;i++){
        nuevaLista.push(0);
      }

    return nuevaLista;
  }

  onSubmit(formValue){ 
    // El 5 marca el numero de filas que contempla la APP
    var listaFilasAux=this.rellenarFilas(this.cleanArray(formValue.listaFilas),5);
    var listaFilasRotos=this.cleanArray(formValue.listaRotos);

    this.clase={
      edificio:formValue.edificio,
      fila:formValue.filas,
      numColumnas:formValue.columnas,
      planta:formValue.planta,
      numAsientosRotos:formValue.rotos,
      listaFilas:listaFilasAux,
      estructura: 1,
      listaRotos:listaFilasRotos
    }

    this._projectService.crearClase(this.clase, this.id).subscribe(
      response => {
        console.log("Servicio completado correctamnete y la respuesta del servicio es -> "+response);
      },
      error => {
        console.log(<any>error);
      }
    );
      this.miFormulario.reset();
  }

  get getColumn(){
    return this.miFormulario.get('listaFilas') as FormArray;
  }

  cleanArray(lista){
    let newArray=[];
    for(var i = 0, j = lista.length; i < j; i++){
        if (lista[i].fila != "" && lista[i].fila != null){
          newArray[i]=lista[i];
      }
    }
    return newArray;
  }



  getColumnas(id):any[]{
    this.array=[];
    for(let i=0;i<id;i++){
      this.array.push(this.ordinales[i+1]);
      this.addFilas()
    }
    return this.array;
  }
  getRotos(id):any[]{
    this.array=[];
    for(let i=0;i<id;i++){
      this.array.push(this.ordinales[i+1]);
      this.addRotos()
    }
    return this.array;
  }

  getCampus(id){
    this.campus=Global.campus[id];
    this.id=id;
    this._projectService.getEdificiobyCampus(id).subscribe(
      result => {
          this.listEdificios=result;
          this.listEdificios.forEach(element => {
            this.listNombresEdificios.push(element.nombre);
          });
          this.listPlantas=this.getPlantas(this.listEdificios[0].plantas);
          this.itemEdificio=this.listNombresEdificios[0];
        },
      error =>{
        console.log('error');
        console.log(error);
      }
    );
  }

  getPlantas(plantas):any[]{
    let aux:number[]=[];
    for (let index = 1; index <= plantas; index++) 
      aux.push(index);
    return aux;
  }

  getPrueba(edificio){
    for (let index = 0; index <= this.listEdificios.length; index++){
      if (this.listEdificios[index].nombre == edificio){
        this.listPlantas=this.getPlantas(this.listEdificios[index].plantas);
        break;
      }
    }
    this.miFormulario.value.planta='1';
  }

  addFilas(){
    const control = <FormArray> this.miFormulario.controls['listaFilas'];
    control.push(this.fb.group({fila: ['']}))
  }

  addRotos(){
    const control = <FormArray> this.miFormulario.controls['listaRotos'];
    control.push(this.fb.group({fila: [''],columna: [''],posicion: ['']}))
  }

  getEdificio(id):string{
    return this.listNombresEdificios[id];
  }

}