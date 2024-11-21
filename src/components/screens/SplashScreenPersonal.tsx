import { View, Text } from 'react-native'
import React, { useState } from 'react'
import {calcularTurnos} from '@/container/calculoTurnos';
import HomeScreen from './HomeScreen';
import { router } from 'expo-router';

const SplashScreenPersonal = () => {
  const [equipos, setEquipos] = useState()
  const { equiposFechas } = calcularTurnos() 
  let response = calcularTurnos()
 setEquipos( response )
  
  return (
    {
      setTimeout(() => {
        router.push('home')
      }, 2000);
      
    }
  )
}

export default SplashScreenPersonal