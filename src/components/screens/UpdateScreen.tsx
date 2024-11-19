import { StyleSheet, Alert, Text   } from 'react-native'
import React, { useState } from 'react'
import { EquiposRedes, useDataBase } from '@/database/useDataBase'
import { Layout, Button } from '@ui-kitten/components'
import DateTimePickerModal from "react-native-modal-datetime-picker";
import { useDateSelector } from '@/hooks/useDateSelector'
import moment from 'moment'
import { router, useLocalSearchParams } from 'expo-router';


const UpdateScreen = () => {
    

    const [fecha, setFecha] = useState('')

    const { updateDate } = useDataBase(); 
    

    const {date, hideDatePicker, showDatePicker, isDatePickerVisible} = useDateSelector()

    const {id} = useLocalSearchParams<string>();

    const handledPress = async (id:string, fecha:string) => { 
        
        try {
            await updateDate(id,fecha)
            Alert.alert("Guardado exitoso")
            router.back()
            
            setFecha('')
        } catch (error) {
            console.log(error)
        }
    }

    

    const handledDate = (value: Date) => { 
        setFecha(value.toDateString())
        hideDatePicker()
        console.log(fecha)

     }
    

  return (
    <Layout style={styles.container} >
        <Text style={styles.fecha}
            >Equipo: {id} </Text>
            <Text style={styles.fecha}
            >Fecha: {fecha} </Text>
             {/* style={styles.button} appearance='outline' status='primary' */}
        <Button style={styles.button} appearance='outline' status='primary' onPress={showDatePicker}>Seleccionar fecha</Button>
        <Button style={styles.button} appearance='outline' status='primary' onPress={()=>handledPress(id.toString(),fecha)}>Guardar</Button>
       

        <DateTimePickerModal
             isVisible={isDatePickerVisible}
             mode="date"
             onConfirm={(value)=>handledDate(value)}
             onCancel={hideDatePicker}
           />

    </Layout>
  )
}

export default UpdateScreen

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        padding:32,
        gap: 25,
        backgroundColor: '#0a1732'
    },
    button: {
        borderRadius: 8
    },
    fecha: {
        alignSelf: "center",
        fontFamily:'sans-serif-condensed',
        fontSize: 18,
        fontWeight: 'bold',
        marginBottom:10,
        color:'#d6cef5'
    },
})