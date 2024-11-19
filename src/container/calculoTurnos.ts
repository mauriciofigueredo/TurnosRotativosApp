
import { EquiposRedes, useDataBase } from '@/database/useDataBase';

import { useState } from 'react';
//Obtener el array de los turnos

//[idEquipo, fechaIni]


export interface Equipo {
    id: string;
    dia: number;
    turno: string;
}


export function calcularTurnos () { 
    
    
    const { getAll } = useDataBase(); 
    const [equipos, setEquipos] = useState<EquiposRedes[]>()
    
    const turnosEquipos = [{
        id: '',
        dia: '',
        turno: ''}];
    
    
    async function equiposFechas (fecha:Date) {
        
        // convierte el formato de la fecha al usado por moment
            try {
              
                const response = await getAll()  //obtiene los registro de la base
                
                setEquipos(response)
                equipos?.map(equipo => {
                    
                    const fechaEquipo = new Date(equipo.fecha)
                   
                    let diff = fecha.getTime() - fechaEquipo.getTime()  
        
                    let cantDias = Math.round((diff/(1000*60*60*24)))
                    
                    let cantTurnos = cantDias / 6
                    let dia = cantDias % 6
                    let turno = ''                    
                    dia += 1 
                    console.log(dia)
                    if(cantTurnos === 0){
                           turno = 'TM'
                    } else {
                        if( cantTurnos % 2 === 0){
                            turno = 'TM'
                        } else{
                            turno = 'TT'
                        }
                    }
                    turnosEquipos.push( { id:equipo.id, dia:dia.toString(), turno:turno} )
                    // console.log(turnosEquipos)
                //    setTurnosEquipos(turnosEquipos.concat({ id:equipo.id, dia:dia.toString(), turno:turno}))
                })
                                
            } catch (error) {
                console.log(error)
            }
            turnosEquipos.shift()
            return turnosEquipos
        } 
       


return {equiposFechas}
}

