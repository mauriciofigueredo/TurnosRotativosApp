import { useSQLiteContext } from "expo-sqlite";
import { useState } from "react";

export type EquiposRedes = {
    id: string;
    fecha: string;
}

export function useDataBase() {
    const database = useSQLiteContext();
    
    const [equipos, setEquipos] = useState(
    )
    
    async function create(id:string, fecha:string){
        const statement = await database.prepareAsync(
            "INSERT INTO equipo (id, fecha) VALUES ($id, $fecha)"
            )

        try {
             await statement.executeAsync({
                $id: id,
                $fecha: fecha
            })  
        } catch (error) {
            console.log(error)
        }
        
        finally{
            await statement.finalizeAsync()
        }
    }
        

    async function updateDate(clave: string, nueva_fecha:string){
        
            try {
                await database.runAsync('UPDATE equipo SET fecha = ? WHERE id = ?', nueva_fecha, clave)

        } catch (error) {
          throw(error)  
        }
        finally{
            // database.closeSync();
        }
    }


    function getAll() {
        try {
            const query = "SELECT * FROM equipo"

            const response = database.getAllAsync<EquiposRedes>(query)
            
            return response;
        } catch (error) {
            console.log(error)
        }

        
    }

    return { create, getAll, updateDate }
}

