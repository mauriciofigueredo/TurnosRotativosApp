import React, { useEffect, useState } from 'react'
import { Button, Layout } from '@ui-kitten/components'
import { FlatList, Pressable, StyleSheet, Text, View } from 'react-native'

import { router, useFocusEffect } from 'expo-router';
import Ionicons from '@expo/vector-icons/Ionicons';
import { useDateSelector } from '@/hooks/useDateSelector';
import DateTimePickerModal from "react-native-modal-datetime-picker";


import {calcularTurnos} from '@/container/calculoTurnos';

const HomeScreen = () => {
  const [turnosEquipos,setTurnosEquipos] = useState([{}])

  const { equiposFechas } = calcularTurnos() 
    
  const {date, handleConfirm, hideDatePicker, showDatePicker, isDatePickerVisible} = useDateSelector()


// if(getAll.length === 0){
//   // create('A1',date.toLocaleDateString())
//   // create('A3',date.toLocaleDateString())
//   // create('A5',date.toLocaleDateString())
//   // create('A8',date.toLocaleDateString())
//   // create('A16',date.toLocaleDateString())
//   console.log('Aca entra siempre por lo visto')
//   console.log(getAll.length)

// }
// console.log('cantidad elementos')
//   console.log(getAll.length)
async function list () {
  try {
    
      setTurnosEquipos(await equiposFechas(date)) 
      
  } catch (error) {
      console.log(error)
  }
}


    useEffect(() => {
        list()

    }, [date])

    
    return (

    <Layout style={styles.container} >
       <View style={styles.dateSelector}>
            
            <Text style={styles.fecha}
            >Fecha: { date.toLocaleDateString() } </Text>
            
          <Button style={{...styles.buton, marginBottom: 20}} appearance='outline' status='primary' onPress={showDatePicker} >Seleccionar fecha</Button>
          
          <DateTimePickerModal
            isVisible={isDatePickerVisible}
            mode="date"
            onConfirm={handleConfirm}
            onCancel={hideDatePicker}
          />
        </View>
      
      
      

<FlatList
          data={turnosEquipos}
          keyExtractor={(item)=>item.id}
          renderItem={({item})=>
            <View>
              <View style={{borderBottomColor:'#30655b', borderWidth:0.3}}/>  
              
              <View style={{
                    flexDirection: 'row',
                    justifyContent:'center',
                    alignContent:'center'}}>
                <Text style={{
                    ...styles.turno,
                    marginVertical:20}} 
                  >Equipo: {item.id} - Dia: {item.dia} - {item.turno}
                  </Text>

                <Pressable 
                  onPress={()=>router.push({
                    pathname: 'update',
                    params: {id: item.id}
                  })}>
                  <Ionicons 
                    style={{marginLeft:130, 
                    marginTop:15}} 
                    color="#32217b" 
                    name="create" size={30} 
                    />
                </Pressable>
              </View>
        </View>
          }
        /> 
      
      <View style={{flexDirection:'row-reverse', gap:15}}>
        <Button 
          appearance='outline'
          status='primary' 
          style={{marginTop:50, width:80}} 
          onPress={()=>router.push('edit')}>Ver BD</Button>
          <Button 
          appearance='outline'
          status='primary' 
          style={{marginTop:50, width:80}} 
          onPress={()=>router.push('edit')}>act</Button>
      </View>
        
      
    </Layout>
       
  )
}

export default HomeScreen

const styles = StyleSheet.create({
    container: {
        flex:1,
        padding:20,
        backgroundColor: '#0a1732'

    },
    title: {
     
        fontWeight: 'bold',
        marginBottom:8
    },
    fecha: {
      alignSelf: "center",
      fontFamily:'sans-serif-condensed',
      fontSize: 18,
      fontWeight: 'bold',
      marginBottom:20,
      color:'#d6cef5'
  },
  dateSelector: {
      marginVertical: 8
  },
  buton: {
      borderRadius: 20,
      backgroundColor: '#32217b',
  },
  turno: {
    fontSize: 18,
    fontWeight: '300',
    color: '#d6cef5'
  }

})