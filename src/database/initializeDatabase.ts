
import {type SQLiteDatabase} from 'expo-sqlite'

export async function initializeDatabase(database: SQLiteDatabase){

    await database.execAsync(`
        CREATE TABLE IF NOT EXISTS equipo (
            id TEXT PRIMARY KEY,
            fecha TEXT
        );

        

    `)
}